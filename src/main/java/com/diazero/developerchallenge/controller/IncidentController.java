package com.diazero.developerchallenge.controller;

import com.diazero.developerchallenge.model.Incident;
import com.diazero.developerchallenge.service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @PostMapping("")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.createIncident(incident), HttpStatus.CREATED);
    }

    @GetMapping("/{idIncident}")
    public ResponseEntity<Incident> getIncident(@PathVariable Long idIncident) {
        Incident incident = incidentService.findById(idIncident);
        return new ResponseEntity<>(incident, HttpStatus.OK);
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
        List<Incident> incidents = incidentService.findLastTwentyIncidents();
        if(!incidents.isEmpty()){
            return new ResponseEntity<>(incidents,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There are no registered incidents.", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{idIncident}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long idIncident, @RequestBody Incident incident) {
        return new ResponseEntity<>(incidentService.updateIncident(idIncident, incident), HttpStatus.OK);
    }

    @PatchMapping("/{idIncident}")
    public ResponseEntity<Incident> patchManager(@PathVariable Long idIncident, @RequestBody Map<String, String> incident) {
        Incident toBePatched = objectMapper.convertValue(incident, Incident.class);
        return new ResponseEntity<>(incidentService.patchIncident(idIncident, toBePatched), HttpStatus.OK);
    }

    @PostMapping("/{idIncident}/close")
    public ResponseEntity<Incident> closeIncident(@PathVariable Long idIncident) {
        return new ResponseEntity<>(incidentService.closeIncident(idIncident), HttpStatus.OK);
    }

    @PostMapping("/{idIncident}/reopen")
    public ResponseEntity<Incident> reopenIncident(@PathVariable Long idIncident) {
        return new ResponseEntity<>(incidentService.reopenIncident(idIncident), HttpStatus.OK);
    }

    @DeleteMapping("/{idIncident}")
    public ResponseEntity<String> deleteIncident(@PathVariable Long idIncident) {
        incidentService.deleteIncident(idIncident);
        return new ResponseEntity<>("INCIDENT "+idIncident+" DELETADED.", HttpStatus.OK);
    }
}