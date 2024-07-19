package ru.suyundukov.amir.adapter.rest.mapper;

import org.mapstruct.Mapper;
import ru.suyundukov.amir.adapter.rest.dto.CreateOrganizationDto;
import ru.suyundukov.amir.adapter.rest.dto.OrganizationDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateOrganizationDto;
import ru.suyundukov.amir.entity.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationDto toDto(Organization organization);

    Organization toDomain(CreateOrganizationDto createOrganizationDto);

    Organization toDomain(UpdateOrganizationDto updateOrganizationDto);
}

