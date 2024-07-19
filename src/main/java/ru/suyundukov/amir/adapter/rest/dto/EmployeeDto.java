package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    @Schema(name = "Id")
    private Long id;
    @Schema(name = "Имя")
    private String name;
    @Schema(name = "Фамилия")
    private String surName;
    @Schema(name = "Отчество")
    private String patronymic;
    @Schema(name = "Должность")
    private String post;
}
