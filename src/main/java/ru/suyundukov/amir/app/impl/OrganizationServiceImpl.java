package ru.suyundukov.amir.app.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyundukov.amir.adapter.repository.OrganizationRepository;
import ru.suyundukov.amir.app.api.OrganizationInbound;
import ru.suyundukov.amir.entity.Organization;
import ru.suyundukov.amir.exception.ApplicationException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationInbound {

    private final OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        log.info("Create organization");
        return organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public Organization updateOrganization(Long id, Organization organization) {
        log.info("Update organization by Id : {}", id);
        Organization foundOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Organization not found"));
        foundOrganization.setName(organization.getName());
        foundOrganization.setDirector(organization.getDirector());
        foundOrganization.setFizAddress(organization.getFizAddress());
        foundOrganization.setLegalAddress(organization.getLegalAddress());
        return organizationRepository.save(foundOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationById(Long id) {
        log.info("Get Organization by Id : {}", id);
        return organizationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Organization not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getAllOrganization() {
        log.info("Get all organization");
        return organizationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteOrganization(Long id) {
        log.info("Delete organization by id : {}", id);
        if (!organizationRepository.existsById(id)) {
            throw new EntityNotFoundException("Organization not found");
        }
        organizationRepository.deleteById(id);
    }
}
