package com.kiu.lims.controller;

import com.kiu.lims.model.CalendarEvent;
import com.kiu.lims.repository.CalendarEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lims/reservation")
public class CalendarEventController {
    private final CalendarEventRepository eventRepository;

    @Autowired
    public CalendarEventController(CalendarEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<CalendarEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarEvent> getEventById(@PathVariable Long id) {
        Optional<CalendarEvent> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> createEvent(@RequestBody CalendarEvent event) {
        CalendarEvent savedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarEvent> updateEvent(@PathVariable Long id, @RequestBody CalendarEvent updatedEvent) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedEvent.setId(id);
        CalendarEvent savedEvent = eventRepository.save(updatedEvent);
        return ResponseEntity.ok(savedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
