package com.kiu.lims.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lab_reservations")
public class LabReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", unique = true)
    @JsonProperty("reservation_id")
    private Long reservationId;

    private String title;

    private String batch;

    private String venue;

    private String date;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    private String description;

    private String calendar; // Business, Personal, etc.

    @JsonProperty("requester_id")
    private Long requesterId;

    private Byte status;

    @JsonProperty("created_by")
    private Integer createdBy;

    @JsonProperty("updated_by")
    private Integer updatedBy;

    @JsonProperty("deleted_by")
    private Integer deletedBy;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("deleted_at")
    private Timestamp deletedAt;
}
