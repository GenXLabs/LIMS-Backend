package com.kiu.lims.repository;

import com.kiu.lims.entity.BiohazardEntity;
import com.kiu.lims.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiohazardRepository extends JpaRepository<BiohazardEntity, Long> {
}
