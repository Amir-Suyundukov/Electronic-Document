package ru.suyundukov.amir.adapter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.suyundukov.amir.adapter.rest.dto.CreateOrganizationDto;
import ru.suyundukov.amir.adapter.rest.dto.OrganizationDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateOrganizationDto;
import ru.suyundukov.amir.adapter.rest.mapper.OrganizationMapper;
import ru.suyundukov.amir.app.api.OrganizationInbound;
import ru.suyundukov.amir.entity.Organization;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
@Tag(name = "Контроллер организаций")
public class OrganizationRestController {

    private final OrganizationInbound organizationInbound;

    private final OrganizationMapper organizationMapper;

    @PostMapping
    @Operation(description = "Создание организации")
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationDto createOrganization(@RequestBody CreateOrganizationDto createOrganizationDto) {
        Organization organization = organizationMapper.toDomain(createOrganizationDto);
        Organization savedorganization = organizationInbound.createOrganization(organization);
        return organizationMapper.toDto(savedorganization);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление организации")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationDto updateOrganization(@PathVariable Long id, @RequestBody UpdateOrganizationDto updateOrganizationDto) {
        Organization organization = organizationMapper.toDomain(updateOrganizationDto);
        Organization savedOrganization = organizationInbound.updateOrganization(id, organization);
        return organizationMapper.toDto(savedOrganization);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск организации по ID")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationDto getOrganizationById(@PathVariable Long id) {
        Organization organization = organizationInbound.getOrganizationById(id);
        return organizationMapper.toDto(organization);
    }

    @GetMapping
    @Operation(description = "Получение всех организации")
    @ResponseStatus(HttpStatus.OK)
    public List<OrganizationDto> getAllOrganization() {
        List<Organization> list = organizationInbound.getAllOrganization();
        return list.stream().map(organizationMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаления организации")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationInbound.deleteOrganization(id);
        return ResponseEntity.ok().build();
    }
}
