package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.suyundukov.amir.entity.Employee;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubdivisionDto {
    @Schema(name = "Id")
    private Long id;
    @Schema(name = "Имя")
    private String name;
    @Schema(name = "Контактный данные")
    private String сontactDetails;
    @Schema(name = "руководитель")
    private Employee director;
}
