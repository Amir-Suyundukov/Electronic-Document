package ru.suyundukov.amir.adapter.rest.mapper;

import org.mapstruct.Mapper;
import ru.suyundukov.amir.adapter.rest.dto.CreateEmployeeDto;
import ru.suyundukov.amir.adapter.rest.dto.EmployeeDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateEmployeeDto;
import ru.suyundukov.amir.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeMapper {
    EmployeeDto toDto(Employee employee);

    Employee toDomain(CreateEmployeeDto createEmployeeDto);

    Employee toDomain(UpdateEmployeeDto updateEmployeeDto);
}
