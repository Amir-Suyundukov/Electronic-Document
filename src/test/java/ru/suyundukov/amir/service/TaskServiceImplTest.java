package ru.suyundukov.amir.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.suyundukov.amir.adapter.repository.TaskRepository;
import ru.suyundukov.amir.app.impl.TaskServiceImpl;
import ru.suyundukov.amir.entity.Task;
import ru.suyundukov.amir.entity.TaskStatus;
import ru.suyundukov.amir.exception.ApplicationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock(lenient = true)
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    private Task task;

    @BeforeEach
    void sutUp() {
        task = new Task();
        task.setId(1L);
        task.setSubjectOrder("Test Task");
        task.setDeadline(LocalDate.of(2024, 7, 19));
        task.setSignOfControl(true);
        task.setSignOfExecution(false);
        task.setText("TEXT");

        when(taskRepository.save(task)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @Test
    public void testCreateTask() {
        Task createTask = taskServiceImpl.createTask(task);

        assertEquals(task.getSubjectOrder(), createTask.getSubjectOrder());
        assertEquals(task.getAuthorOrder(), createTask.getAuthorOrder());
        assertEquals(task.getDeadline(), createTask.getDeadline());
        assertEquals(task.getId(), createTask.getId());
        assertEquals(task.getStatus(), createTask.getStatus());
        assertEquals(task.getText(), createTask.getText());

        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task foundTask = taskServiceImpl.getTaskById(1L);

        assertEquals(task.getSubjectOrder(), foundTask.getSubjectOrder());
    }

    @Test
    public void testUpdateTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task updateTask = new Task();
        updateTask.setSubjectOrder("Test 1");
        updateTask.setSignOfExecution(true);
        updateTask.setSignOfControl(true);
        updateTask.setText("TEXT 1");
        updateTask.setStatus(TaskStatus.REFINEMENT);

        Task updatedTask = taskServiceImpl.updateTask(1L, updateTask);

        assertEquals("Test 1", updatedTask.getSubjectOrder());
        assertEquals("TEXT 1", updatedTask.getText());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskRepository.findById(1L)).thenThrow(ApplicationException.class);

        assertThrows(ApplicationException.class, () -> taskServiceImpl.getTaskById(1L));
    }

    @Test
    public void testGetAllTask() {
        List<Task> list = Arrays.asList(task, task);
        when(taskRepository.findAll()).thenReturn(list);

        List<Task> tasks = taskServiceImpl.getAllTask();

        assertEquals(2, tasks.size());
    }

    @Test
    public void testDeleteTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(1L);

        taskServiceImpl.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    public void delete_NotFound() {
        when(taskRepository.existsById(1L)).thenReturn(false);

        assertThrows(ApplicationException.class, () -> taskServiceImpl.deleteTask(1L));
    }

}

