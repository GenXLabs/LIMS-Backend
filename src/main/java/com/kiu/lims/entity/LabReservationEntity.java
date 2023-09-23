package com.kiu.lims.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lab_reservations")
public class LabReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", unique = true)
    private Long reservationId;

    private String title;

    private String batch;

    private String venue;

    private String date;

    private String startTime;

    private String endTime;

    private String description;

    private String calendar; // Business, Personal, etc.

    private Long requesterId;

    private Byte status;

    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;
}
