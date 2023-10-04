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
@Table(name = "sop_table")
public class InstrumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sop_id", unique = true)
    @JsonProperty("sop_Id")
    private Long sopId;

    private String title;
    private String description;

    @JsonProperty("created_by")
    private Integer createdBy;

    @JsonProperty("update_by")
    private Integer updateBy;

    @JsonProperty("deleted_by")
    private Integer deletedBy;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("deleted_at")
    private Timestamp deletedAt;


}
