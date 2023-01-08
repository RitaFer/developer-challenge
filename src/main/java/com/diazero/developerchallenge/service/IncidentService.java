package com.diazero.developerchallenge.service;

import com.diazero.developerchallenge.config.NullAwareBeanUtilsBean;
import com.diazero.developerchallenge.exception.GeneralException;
import com.diazero.developerchallenge.exception.NotFoundException;
import com.diazero.developerchallenge.model.Incident;
import com.diazero.developerchallenge.repository.IncidentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;

    @Autowired
    NullAwareBeanUtilsBean beanUtils;

    public Incident createIncident (Incident incidentRequest){
        try {
            Incident incident = new Incident();
            BeanUtils.copyProperties(incidentRequest, incident);
            incidentRepository.save(incident);
            return incident;
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public Incident findById (Long idIncident){
        Incident incident = incidentRepository.findById(idIncident).orElseThrow(() -> new NotFoundException("Incident not found. Please check if the ID is correct."));;
        return incident;
    }

    public List<Incident> findAll (){
        try {
            return incidentRepository.findAll();
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public List<Incident> findLastTwentyIncidents (){
        try {
            return incidentRepository.findTop20ByOrderByIdIncidentDesc();
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public Incident updateIncident(Long idIncident, Incident incidentToUpdate) {
        Incident incident = this.findById(idIncident);
        incidentToUpdate.setCreatedAt(incident.getCreatedAt());
        incidentToUpdate.setUpdateAt(LocalDateTime.now());
        return incidentRepository.save(incidentToUpdate);
    }

    public Incident patchIncident(Long idIncident, Incident toBePatched) {
        try {
            Incident incident = this.findById(idIncident);
            toBePatched.setCreatedAt(incident.getCreatedAt());
            toBePatched.setUpdateAt(LocalDateTime.now());
            beanUtils.copyProperties(incident, toBePatched);
            return this.updateIncident(incident.getIdIncident(), incident);
        } catch (Exception e){
            throw new GeneralException(e.getCause()+" --> "+e.getMessage());
        }
    }

    public Incident closeIncident(Long idIncident) {
        Incident incident = this.findById(idIncident);
        incident.setCloseAt(LocalDateTime.now());
        return incident;
    }

    public Incident reopenIncident(Long idIncident) {
        Incident incident = this.findById(idIncident);
        incident.setCloseAt(null);
        return incident;
    }

    public void deleteIncident(Long idIncident) {
        Incident incident = this.findById(idIncident);
        incidentRepository.deleteById(idIncident);
    }
}