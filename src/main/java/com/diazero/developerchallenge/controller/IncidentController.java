package com.diazero.developerchallenge.controller;

import com.diazero.developerchallenge.model.Incident;
import com.diazero.developerchallenge.service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    @Autowired
    IncidentService incidentService;

    @Autowired
    ObjectMapper objectMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.createIncident(incident), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{idIncident}")
    public ResponseEntity<Incident> getIncident(@PathVariable Long idIncident) {
        Incident incident = incidentService.findById(idIncident);
        return new ResponseEntity<>(incident, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("")
    public ResponseEntity<?> getAllIncidents() {
        List<Incident> incidents = incidentService.findAll();
        if(!incidents.isEmpty()){
            return new ResponseEntity<>(incidents,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no registered incidents.", HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/top20")
    public ResponseEntity<?> getLast20Incidents() {
        List<Incident> incidents = incidentService.findLastTwentyIncidents();
        if(!incidents.isEmpty()){
            return new ResponseEntity<>(incidents,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no registered incidents.", HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idIncident}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long idIncident, @RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.updateIncident(idIncident, incident), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{idIncident}")
    public ResponseEntity<Incident> patchManager(@PathVariable Long idIncident, @RequestBody Map<String, String> incident) {
        Incident toBePatched = objectMapper.convertValue(incident, Incident.class);
        return new ResponseEntity<>(incidentService.patchIncident(idIncident, toBePatched), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idIncident}/close")
    public ResponseEntity<Incident> closeIncident(@PathVariable Long idIncident) {
        return new ResponseEntity<>(incidentService.closeIncident(idIncident), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idIncident}/reopen")
    public ResponseEntity<Incident> reopenIncident(@PathVariable Long idIncident) {
        return new ResponseEntity<>(incidentService.reopenIncident(idIncident), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idIncident}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long idIncident) {
        incidentService.deleteIncident(idIncident);
        return new ResponseEntity<>("INCIDENT "+idIncident+" DELETADED.", HttpStatus.OK);
    }
}