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
public class UpdateSubdivisionDto {
    @Schema(name = "Имя")
    @NotBlank
    private String name;
    @Schema(name = "Контактный данные")
    @NotBlank
    private String сontactDetails;
    @Schema(name = "руководитель")
    @NotBlank
    private Employee director;
}
