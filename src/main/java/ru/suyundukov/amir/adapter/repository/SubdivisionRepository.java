package ru.suyundukov.amir.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyundukov.amir.entity.Subdivision;

@Repository
public interface SubdivisionRepository extends JpaRepository<Subdivision, Long> {
}
