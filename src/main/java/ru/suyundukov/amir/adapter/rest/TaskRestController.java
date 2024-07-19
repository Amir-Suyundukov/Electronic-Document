package ru.suyundukov.amir.adapter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.suyundukov.amir.adapter.rest.dto.CreateTask;
import ru.suyundukov.amir.adapter.rest.dto.TaskDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateTask;
import ru.suyundukov.amir.adapter.rest.mapper.TaskMapper;
import ru.suyundukov.amir.app.api.TaskInbound;
import ru.suyundukov.amir.entity.Task;
import ru.suyundukov.amir.entity.TaskStatus;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Tag(name = "Контроллер поручений")
public class TaskRestController {

    private final TaskInbound taskInbound;

    private final TaskMapper taskMapper;

    @PostMapping
    @Operation(description = "Создание поручения")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody CreateTask createTask) {
        Task task = taskMapper.toDomain(createTask);
        Task savedTask = taskInbound.createTask(task);
        return taskMapper.toDto(savedTask);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление поручения")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable Long id, @RequestBody UpdateTask updateTask) {
        Task task = taskMapper.toDomain(updateTask);
        Task savedTask = taskInbound.updateTask(id, task);
        return taskMapper.toDto(savedTask);
    }

    @PutMapping("/{id}/status")
    @Operation(description = "Обновление статуса поручения")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task task = taskInbound.updateTaskStatus(id, status);
        return taskMapper.toDto(task);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск поручения")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable Long id) {
        Task task = taskInbound.getTaskById(id);
        return taskMapper.toDto(task);
    }

    @GetMapping
    @Operation(description = "Получение всех поручений")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTask() {
        List<Task> list = taskInbound.getAllTask();
        return list.stream().map(taskMapper::toDto).toList();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление поручения")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskInbound.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
