package com.kiu.lims.controller;

import com.kiu.lims.entity.TimetableEvent;
import com.kiu.lims.service.TimetableEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lims/timetable-events")
@CrossOrigin
public class TimetableEventController {

    private final TimetableEventService eventService;

    @Autowired
    public TimetableEventController(TimetableEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<TimetableEvent>> getAllTimetableEvents() {
        List<TimetableEvent> events = eventService.getAllTimetableEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimetableEvent> getTimetableEventById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        TimetableEvent event = eventService.getTimetableEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimetableEvent> createTimetableEvent(@RequestBody TimetableEvent event) {

        TimetableEvent createdEvent = eventService.createTimetableEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimetableEvent> updateTimetableEvent(@PathVariable Long id, @RequestBody TimetableEvent event) throws ChangeSetPersister.NotFoundException {

        TimetableEvent updatedEvent = eventService.updateTimetableEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimetableEvent(@PathVariable Long id) {
        eventService.deleteTimetableEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
