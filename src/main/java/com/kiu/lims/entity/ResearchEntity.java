package com.kiu.lims.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "research_table")
public class ResearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="research_id", unique = true)
    @JsonProperty("research_id")
    private Long researchId;

    @JsonProperty("research_name")
    private String researchName;

    @JsonProperty("study_type")
    private String studyType;

    @JsonProperty("batch")
    private String batch;

    @JsonProperty("num_students")
    private Integer numStudents;

    @JsonProperty("assigned_assistant")
    private String assignedAssistant;

    @JsonProperty("start_date")
    private Timestamp startDate;

    @JsonProperty("end_date")
    private Timestamp endDate;

    @JsonProperty("status")
    private String status;
}
