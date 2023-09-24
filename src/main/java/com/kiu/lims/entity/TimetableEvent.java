package com.kiu.lims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class TimetableEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private LocalTime time;
    private LocalDate date;

    public TimetableEvent() {
        // Default constructor
    }

    public TimetableEvent(LocalTime time, LocalDate date) {
        this.time = time;
        this.date = date;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimetableEvent{" +
                "eventId=" + eventId +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
