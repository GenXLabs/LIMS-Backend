package com.kiu.lims.service;

import com.kiu.lims.entity.ResearchEntity;

import java.util.List;

public interface ResearchService {

    List<ResearchEntity> getAllResearch();

    ResearchEntity getResearchById(Long id);

    ResearchEntity createResearch(ResearchEntity research);

    ResearchEntity updateResearchById(Long id, ResearchEntity updatedResearch);

    boolean deleteResearchById(Long id);
}
