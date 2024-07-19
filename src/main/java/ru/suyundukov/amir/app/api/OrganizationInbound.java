package ru.suyundukov.amir.app.api;

import ru.suyundukov.amir.entity.Organization;

import java.util.List;

public interface OrganizationInbound {

    Organization createOrganization(Organization organization);

    Organization updateOrganization(Long id, Organization organization);

    Organization getOrganizationById(Long id);

    List<Organization> getAllOrganization();

    void deleteOrganization(Long id);

}
