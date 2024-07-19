package ru.suyundukov.amir.adapter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.suyundukov.amir.adapter.rest.dto.CreateSubdivisionDto;
import ru.suyundukov.amir.adapter.rest.dto.SubdivisionDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateSubdivisionDto;
import ru.suyundukov.amir.adapter.rest.mapper.SubdivisionMapper;
import ru.suyundukov.amir.app.api.SubdivisionInbound;
import ru.suyundukov.amir.entity.Subdivision;

import java.util.List;

@RestController
@RequestMapping("/api/subdivision")
@RequiredArgsConstructor
@Tag(name = "Контроллер подразделений")
public class SubdivisionRestController {

    private final SubdivisionInbound subdivisionInbound;

    private final SubdivisionMapper subdivisionMapper;

    @PostMapping
    @Operation(description = "Создание подразделения")
    @ResponseStatus(HttpStatus.CREATED)
    public SubdivisionDto createSubdivision(@RequestBody CreateSubdivisionDto createSubdivisionDto) {
        Subdivision subdivision = subdivisionMapper.toDomain(createSubdivisionDto);
        Subdivision savedSubdivision = subdivisionInbound.createSubdivision(subdivision);
        return subdivisionMapper.toDto(savedSubdivision);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление подразделения")
    @ResponseStatus(HttpStatus.OK)
    public SubdivisionDto updateSubdivision(@PathVariable Long id, @RequestBody UpdateSubdivisionDto updateSubdivisionDto) {
        Subdivision subdivision = subdivisionMapper.toDamain(updateSubdivisionDto);
        Subdivision savedSubdivision = subdivisionInbound.updateSubdivision(id, subdivision);
        return subdivisionMapper.toDto(savedSubdivision);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск подразделения по ID")
    @ResponseStatus(HttpStatus.OK)
    public SubdivisionDto getSubdivisionById(@PathVariable Long id) {
        Subdivision subdivision = subdivisionInbound.getSubdivisionById(id);
        return subdivisionMapper.toDto(subdivision);
    }

    @GetMapping
    @Operation(description = "Получить список всех подразделений")
    @ResponseStatus(HttpStatus.OK)
    public List<SubdivisionDto> getAllSubdivision() {
        List<Subdivision> list = subdivisionInbound.getAllSubdivision();
        return list.stream().map(subdivisionMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление подразделения")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteSubdivision(@PathVariable Long id) {
        subdivisionInbound.deleteSubdivision(id);
        return ResponseEntity.ok().build();
    }
}