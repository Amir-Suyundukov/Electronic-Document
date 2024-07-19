package ru.suyundukov.amir.app.impl;

import org.springframework.stereotype.Component;
import ru.suyundukov.amir.entity.Task;
import ru.suyundukov.amir.entity.TaskStatus;

import static java.lang.String.format;

@Component
public class UpdateTaskStateMachine {
    private static final String INVALID_TASK_STATUS = "Wrong task status: '%s'.";

    public void changeStatus(Task task, TaskStatus status) {
        switch (task.getStatus()) {
            case PREPARATION -> preparingStatus(task, status);
            case EXECUTION -> executionStatus(task, status);
            case CONTROL -> controlStatus(task, status);
            case REFINEMENT -> refinementStatus(task, status);
            case ACCEPTANCE -> acceptanceStatus(task, status);
        }
    }

    private void preparingStatus(Task task, TaskStatus taskStatus) {
        if (taskStatus.equals(TaskStatus.EXECUTION)) {
            task.setStatus(TaskStatus.EXECUTION);
        } else {
            throw new IllegalArgumentException(format(INVALID_TASK_STATUS, taskStatus));
        }
    }

    private void executionStatus(Task task, TaskStatus taskStatus) {
        if (taskStatus.equals(TaskStatus.CONTROL)) {
            task.setSignOfExecution(Boolean.TRUE)
                    .setStatus(TaskStatus.CONTROL);
        } else {
            throw new IllegalArgumentException(format(INVALID_TASK_STATUS, taskStatus));
        }
    }

    private void controlStatus(Task task, TaskStatus taskStatus) {
        if (taskStatus.equals(TaskStatus.REFINEMENT)) {
            task.setSignOfControl(Boolean.TRUE)
                    .setSignOfExecution(Boolean.FALSE)
                    .setStatus(TaskStatus.REFINEMENT);
        } else if (taskStatus.equals(TaskStatus.ACCEPTANCE)) {
            task.setSignOfControl(Boolean.TRUE)
                    .setStatus(TaskStatus.ACCEPTANCE);
        } else {
            throw new IllegalArgumentException(format(INVALID_TASK_STATUS, taskStatus));
        }
    }

    private void refinementStatus(Task task, TaskStatus taskStatus) {
        if (taskStatus.equals(TaskStatus.EXECUTION)) {
            task.setSignOfControl(Boolean.FALSE)
                    .setStatus(TaskStatus.EXECUTION);
        } else {
            throw new IllegalArgumentException(format(INVALID_TASK_STATUS, taskStatus));
        }
    }

    private void acceptanceStatus(Task task, TaskStatus taskStatus) {
        if (taskStatus.equals(TaskStatus.ACCEPTANCE)) {
            //У поручения конечный статус поэтому ничего не делаем
        }
    }
}
