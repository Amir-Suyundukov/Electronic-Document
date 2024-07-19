package ru.suyundukov.amir.app.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyundukov.amir.adapter.repository.TaskRepository;
import ru.suyundukov.amir.app.api.TaskInbound;
import ru.suyundukov.amir.entity.Task;
import ru.suyundukov.amir.entity.TaskStatus;
import ru.suyundukov.amir.exception.ApplicationException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskInbound {

    private final TaskRepository taskRepository;

    private final UpdateTaskStateMachine updateTaskStateMachine;

    @Override
    @Transactional
    public Task createTask(Task task) {
        log.info("Create task");
        task.setStatus(TaskStatus.PREPARATION);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, Task task) {
        log.info("Update task by Id : {}", id);
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Task not found by id : " + id));
        foundTask.setAuthorOrder(task.getAuthorOrder());
        foundTask.setDeadline(task.getDeadline());
        foundTask.setText(task.getText());
        foundTask.setSignOfControl(task.isSignOfControl());
        foundTask.setSignOfExecution(task.isSignOfExecution());
        foundTask.setSubjectOrder(task.getSubjectOrder());
        foundTask.setStatus(task.getStatus());
        return taskRepository.save(foundTask);
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long id, TaskStatus status) {
        log.info("Update task status by Id : {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Task not found by id : " + id));
        updateTaskStateMachine.changeStatus(task, status);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        log.info("Get task by Id : {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Task not found by id : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTask() {
        log.info("Get all task");
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        log.info("Delete task by id : {}", id);
        if (!taskRepository.existsById(id)) {
            throw new ApplicationException("Task not found by id : " + id);
        }
        taskRepository.deleteById(id);
    }
}
