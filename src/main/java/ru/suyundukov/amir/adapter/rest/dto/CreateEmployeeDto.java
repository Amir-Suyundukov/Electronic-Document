package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeDto {
    @Schema(name = "Имя")
    @NotBlank
    private String name;
    @Schema(name = "Фамилия")
    @NotBlank
    private String surName;
    @Schema(name = "Отчество")
    private String patronymic;
    @Schema(name = "Должность")
    @NotBlank
    private String post;
}
