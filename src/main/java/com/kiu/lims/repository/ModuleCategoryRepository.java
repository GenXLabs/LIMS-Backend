package com.kiu.lims.repository;

import com.kiu.lims.entity.ModuleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleCategoryRepository extends JpaRepository<ModuleCategoryEntity, Long> {
}
