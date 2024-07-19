package ru.suyundukov.amir.adapter.rest.mapper;

import org.mapstruct.Mapper;
import ru.suyundukov.amir.adapter.rest.dto.CreateSubdivisionDto;
import ru.suyundukov.amir.adapter.rest.dto.SubdivisionDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateSubdivisionDto;
import ru.suyundukov.amir.entity.Subdivision;

@Mapper(componentModel = "spring")
public interface SubdivisionMapper {
    SubdivisionDto toDto(Subdivision subdivision);

    Subdivision toDomain(CreateSubdivisionDto subdivisionDto);

    Subdivision toDamain(UpdateSubdivisionDto updateSubdivisionDto);
}
