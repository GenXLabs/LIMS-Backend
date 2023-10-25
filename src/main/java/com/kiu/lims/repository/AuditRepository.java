package com.kiu.lims.repository;

import com.kiu.lims.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> {



}
