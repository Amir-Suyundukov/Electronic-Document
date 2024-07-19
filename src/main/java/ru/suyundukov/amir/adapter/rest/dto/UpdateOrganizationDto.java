package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.suyundukov.amir.entity.Employee;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrganizationDto {
    @Schema(name = "Имя")
    @NotBlank
    private String name;
    @Schema(name = "Физический адрес")
    @NotBlank
    private String fizAddress;
    @Schema(name = "Юридический адрес")
    @NotBlank
    private String legalAddress;
    @Schema(name = "Руководитель")
    @NotBlank
    private Employee director;
}
