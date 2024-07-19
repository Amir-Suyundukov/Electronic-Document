package ru.suyundukov.amir.app.api;

import ru.suyundukov.amir.entity.Task;
import ru.suyundukov.amir.entity.TaskStatus;

import java.util.List;

public interface TaskInbound {

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    Task updateTaskStatus(Long id, TaskStatus status);

    Task getTaskById(Long id);

    List<Task> getAllTask();

    void deleteTask(Long id);
}
