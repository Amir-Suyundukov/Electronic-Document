package ru.suyundukov.amir.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.suyundukov.amir.entity.Employee;
import ru.suyundukov.amir.entity.TaskStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    @Schema(name = "Id")
    private Long id;
    @Schema(name = "Предмет поручения")
    private String subjectOrder;
    @Schema(name = "Автор поручения")
    private Employee authorOrder;
    @Schema(name = "Исполнители поручерия")
    private String performersOrder;
    @Schema(name = "Срок выполнения")
    private LocalDate deadline;
    @Schema(name = "Прикзнак контрольности")
    private boolean signOfControl;
    @Schema(name = "Признак исполнения")
    private boolean signOfExecution;
    @Schema(name = "Текст")
    private String text;
    @Schema(name = "Статус")
    private TaskStatus status;
}
