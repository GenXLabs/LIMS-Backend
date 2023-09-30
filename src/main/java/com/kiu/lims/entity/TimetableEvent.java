package com.kiu.lims.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class TimetableEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("event_title")
    private String eventTitle;

    private String venue;

    @JsonFormat
    private String date;  // New field: Date

    @JsonFormat
    private String time;  // New field: Time

    private String email;

    public TimetableEvent() {
        // Default constructor
    }

    public TimetableEvent(String eventTitle, String venue, String date, String time, String email) {
        this.eventTitle = eventTitle;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.email = email;
    }

    // Remove setters and getters for "startTime" and "endTime"

    @Override
    public String toString() {
        return "TimetableEvent{" +
                "eventId=" + eventId +
                ", eventTitle='" + eventTitle + '\'' +
                ", venue='" + venue + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", email='" + email + '\'' +
                '}';
    }
}
