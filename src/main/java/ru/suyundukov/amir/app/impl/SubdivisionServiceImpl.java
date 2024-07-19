package ru.suyundukov.amir.app.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyundukov.amir.adapter.repository.SubdivisionRepository;
import ru.suyundukov.amir.app.api.SubdivisionInbound;
import ru.suyundukov.amir.entity.Subdivision;
import ru.suyundukov.amir.exception.ApplicationException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubdivisionServiceImpl implements SubdivisionInbound {

    private final SubdivisionRepository subdivisionRepository;

    @Override
    @Transactional
    public Subdivision createSubdivision(Subdivision subdivision) {
        log.info("Create subdivision");
        return subdivisionRepository.save(subdivision);
    }

    @Override
    @Transactional
    public Subdivision updateSubdivision(Long id, Subdivision subdivision) {
        log.info("Update subdivision by Id : {}", id);
        Subdivision foundSubdivision = subdivisionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Organization not found"));
        foundSubdivision.setName(subdivision.getName());
        foundSubdivision.setContactDetails(subdivision.getContactDetails());
        foundSubdivision.setDirector(subdivision.getDirector());
        return subdivisionRepository.save(foundSubdivision);
    }

    @Override
    @Transactional(readOnly = true)
    public Subdivision getSubdivisionById(Long id) {
        log.info("Get subdivision by Id : {}", id);
        return subdivisionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Organization not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subdivision> getAllSubdivision() {
        log.info("Get all subdivision");
        return subdivisionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteSubdivision(Long id) {
        log.info("Delete subdivision by id : {}", id);
        if (!subdivisionRepository.existsById(id)) {
            throw new EntityNotFoundException("Organization not found");
        }
        subdivisionRepository.deleteById(id);
    }
}
