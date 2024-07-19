package ru.suyundukov.amir.app.api;

import ru.suyundukov.amir.entity.Subdivision;

import java.util.List;

public interface SubdivisionInbound {

    Subdivision createSubdivision(Subdivision subdivision);

    Subdivision updateSubdivision(Long id, Subdivision subdivision);

    Subdivision getSubdivisionById(Long id);

    List<Subdivision> getAllSubdivision();

    void deleteSubdivision(Long id);
}
