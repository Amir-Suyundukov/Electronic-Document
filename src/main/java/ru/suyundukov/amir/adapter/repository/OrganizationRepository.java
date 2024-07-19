package ru.suyundukov.amir.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyundukov.amir.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
