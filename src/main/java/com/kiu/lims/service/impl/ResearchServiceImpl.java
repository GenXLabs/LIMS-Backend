package com.kiu.lims.service.impl;

import com.kiu.lims.entity.ResearchEntity;
import com.kiu.lims.repository.ResearchRepository;
import com.kiu.lims.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearchServiceImpl implements ResearchService {

    private final ResearchRepository researchRepository;

    @Override
    public List<ResearchEntity> getAllResearch() {
        return researchRepository.findAll();
    }

    @Override
    public ResearchEntity getResearchById(Long id) {
        return researchRepository.findById(id).orElse(null);
    }

    @Override
    public ResearchEntity createResearch(ResearchEntity research) {
        return researchRepository.save(research);
    }

    @Override
    public ResearchEntity updateResearchById(Long id, ResearchEntity updatedResearch) {
        if (researchRepository.existsById(id)) {
            updatedResearch.setResearchId(id);
            return researchRepository.save(updatedResearch);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteResearchById(Long id) {
        if (researchRepository.existsById(id)) {
            researchRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
