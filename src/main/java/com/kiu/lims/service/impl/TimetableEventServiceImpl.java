package com.kiu.lims.service.impl;

import com.kiu.lims.entity.TimetableEvent;
import com.kiu.lims.repository.TimetableEventRepository;
import com.kiu.lims.service.TimetableEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableEventServiceImpl implements TimetableEventService {

    private final TimetableEventRepository eventRepository;

    @Autowired
    public TimetableEventServiceImpl(TimetableEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<TimetableEvent> getAllTimetableEvents() {
        return eventRepository.findAll();
    }

    @Override
    public TimetableEvent getTimetableEventById(Long id) throws ChangeSetPersister.NotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public TimetableEvent createTimetableEvent(TimetableEvent event) {
        // Check for duplicates based on date, time, and venue
        if (eventRepository.existsByDateAndTimeAndVenue(event.getDate(), event.getTime(), event.getVenue())) {
            throw new DuplicateTimetableEventException("A duplicate event already exists for the given date, time, and venue.");
        }

        // If no duplicates found, save the event
        return eventRepository.save(event);
    }

    @Override
    public TimetableEvent updateTimetableEvent(Long id, TimetableEvent updatedEvent) throws ChangeSetPersister.NotFoundException {
        TimetableEvent existingEvent = getTimetableEventById(id);

        // Check for duplicates based on date, time, and venue when updating
        if (!existingEvent.getDate().equals(updatedEvent.getDate()) ||
                !existingEvent.getTime().equals(updatedEvent.getTime()) ||
                !existingEvent.getVenue().equals(updatedEvent.getVenue())) {
            if (eventRepository.existsByDateAndTimeAndVenue(updatedEvent.getDate(), updatedEvent.getTime(), updatedEvent.getVenue())) {
                throw new DuplicateTimetableEventException("A duplicate event already exists for the updated date, time, and venue.");
            }
        }

        // Update the event's properties
        existingEvent.setEventTitle(updatedEvent.getEventTitle());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setEmail(updatedEvent.getEmail());

        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteTimetableEvent(Long id) {
        eventRepository.deleteById(id);
    }
}

