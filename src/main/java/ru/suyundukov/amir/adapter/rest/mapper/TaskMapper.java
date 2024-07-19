package ru.suyundukov.amir.adapter.rest.mapper;

import org.mapstruct.Mapper;
import ru.suyundukov.amir.adapter.rest.dto.CreateTask;
import ru.suyundukov.amir.adapter.rest.dto.TaskDto;
import ru.suyundukov.amir.adapter.rest.dto.UpdateTask;
import ru.suyundukov.amir.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    Task toDomain(CreateTask createTask);

    Task toDomain(UpdateTask updateTask);
}
