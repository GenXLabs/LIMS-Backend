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
@Table(name = "inventory_table")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="inventory_id", unique = true)
    @JsonProperty("inventory_id")
    private Long inventoryid;

    @JsonProperty("inventory_name")
    private String inventoryname;

    @JsonProperty("availability")
    private Integer availability;

    @JsonProperty("newly")
    private Integer newly;

    @JsonProperty("broken")
    private Integer broken;

    @JsonProperty("returns")
    private Integer returns;

    @JsonProperty("balance")
    private Integer balance;

    @JsonProperty("created_by")
    private Integer created_by;

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
