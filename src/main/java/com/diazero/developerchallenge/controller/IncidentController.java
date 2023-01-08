package com.diazero.developerchallenge.controller;

import com.diazero.developerchallenge.model.Incident;
import com.diazero.developerchallenge.service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.create(incident), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncident(@PathVariable Long id) {
        Optional<Incident> incident = incidentService.findById(id);
        return incident.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllIncidents() {
        List<Incident> incidents = incidentService.findAll();
        if(!incidents.isEmpty()){
            return new ResponseEntity<>(incidents,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no registered incidents.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/top20")
    public ResponseEntity<?> getLast20Incidents() {
        List<Incident> incidents = incidentService.findLastTwenty();
        if(!incidents.isEmpty()){
            return new ResponseEntity<>(incidents,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no registered incidents.", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.update(id, incident), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Incident> patchManager(@PathVariable Long id, @RequestBody Map<String, String> incident) {
        Incident toBePatched = objectMapper.convertValue(incident, Incident.class);
        return new ResponseEntity<>(incidentService.patch(id, toBePatched), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long id) {
        incidentService.delete(id);
        return new ResponseEntity<>("INCIDENT "+id+" DELETADED.", HttpStatus.OK);
    }
}