package com.diazero.developerchallenge.service;

import com.diazero.developerchallenge.config.NullAwareBeanUtilsBean;
import com.diazero.developerchallenge.model.Incident;
import com.diazero.developerchallenge.model.exception.*;
import com.diazero.developerchallenge.repository.IncidentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    NullAwareBeanUtilsBean beanUtils;

    public Incident create (Incident incidentRequest){
        try {
            Incident incident = new Incident();
            BeanUtils.copyProperties(incidentRequest, incident);
            incidentRepository.save(incident);
            return incident;
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public Optional<Incident> findById (Long idIncident){
        Optional<Incident> incident = incidentRepository.findById(idIncident);
        if(incident.isPresent()){
            return incident;
        } else {
            throw new NotFoundException("Incident not found. Please check if the ID is correct.");
        }
    }

    public List<Incident> findAll (){
        try {
            return incidentRepository.findAll();
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public List<Incident> findLastTwenty (){
        try {
            return incidentRepository.findTop20ByOrderByIdIncidentDesc();
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public Incident update(Long idIncident, Incident incidentUpdate) {
        Optional<Incident> incident = this.findById(idIncident);
        if(incident.isPresent()){
            incidentUpdate.setCreatedAt(incident.get().getCreatedAt());
            incidentUpdate.setUpdateAt(LocalDateTime.now());
            return incidentRepository.save(incidentUpdate);
        } else {
            throw new NotFoundException("Incident not found. Please check if the ID is correct.");
        }
    }

    public Incident patch(Long id, Incident toBePatched) {
        try {
            Optional<Incident> incident = this.findById(id);
            if (incident.isPresent()) {
                toBePatched.setCreatedAt(incident.get().getCreatedAt());
                toBePatched.setUpdateAt(LocalDateTime.now());
                beanUtils.copyProperties(incident.get(), toBePatched);
                return this.update(incident.get().getIdIncident(), incident.get());
            } else {
                throw new NotFoundException("Incident not found. Please check if the ID is correct.");
            }
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public void delete(Long idIncident) {
        Optional<Incident> incident = this.findById(idIncident);
        if(incident.isPresent()){
            incidentRepository.deleteById(idIncident);
        } else {
            throw new NotFoundException("Incident not found. Please check if the ID is correct.");
        }
    }
}