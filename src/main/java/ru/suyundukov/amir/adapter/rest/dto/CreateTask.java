package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.suyundukov.amir.entity.Employee;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTask {
    @Schema(name = "Предмет поручения")
    @NotBlank
    private String subjectOrder;
    @Schema(name = "Автор поручения")
    @NotBlank
    private Employee authorOrder;
    @Schema(name = "Исполнители поручерия")
    @NotBlank
    private String performersOrder;
    @Schema(name = "Срок выполнения")
    @NotBlank
    private LocalDate deadline;
    @Schema(name = "Прикзнак контрольности")
    @NotNull
    private boolean signOfControl;
    @Schema(name = "Признак исполнения")
    @NotNull
    private boolean signOfExecution;
    @Schema(name = "Текст")
    private String text;
}
