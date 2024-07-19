package ru.suyundukov.amir.adapter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.suyundukov.amir.adapter.rest.dto.CreateEmployeeDto;
import ru.suyundukov.amir.adapter.rest.dto.EmployeeDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateEmployeeDto;
import ru.suyundukov.amir.adapter.rest.mapper.EmployeMapper;
import ru.suyundukov.amir.app.api.EmployeeInbound;
import ru.suyundukov.amir.entity.Employee;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@Tag(name = "Контроллер сотрудников")
public class EmployeeRestController {

    private final EmployeeInbound employeeInbound;

    private final EmployeMapper employeMapper;

    @PostMapping
    @Operation(description = "Создание сотрудника")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        Employee employee = employeMapper.toDomain(createEmployeeDto);
        Employee savedEmployee = employeeInbound.createEmployee(employee);
        return employeMapper.toDto(savedEmployee);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление сотрудника")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = employeMapper.toDomain(updateEmployeeDto);
        Employee savedEmployee = employeeInbound.updateEmployee(id, employee);
        return employeMapper.toDto(savedEmployee);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск сотрудника по ID")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeInbound.getEmployeeById(id);
        return employeMapper.toDto(employee);
    }

    @GetMapping
    @Operation(description = "Поиск всех сотрудников")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> list = employeeInbound.getAllEmployee();
        return list.stream().map(employeMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление сотрудника")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeInbound.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
