package ru.suyundukov.amir.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.suyundukov.amir.app.impl.EmployeeServiceImpl;
import ru.suyundukov.amir.entity.Employee;
import ru.suyundukov.amir.exception.ApplicationException;
import ru.suyundukov.amir.adapter.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock(lenient = true)
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setSurName("Doe");
        employee.setPatronymic("Michael");
        employee.setPost("Developer");

        when(employeeRepository.save(employee)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @Test
    public void testCreateEmployee() {
        Employee createEmployee = employeeServiceImpl.createEmployee(employee);

        assertEquals(employee.getName(), createEmployee.getName());
        assertEquals(employee.getSurName(), createEmployee.getSurName());
        assertEquals(employee.getPatronymic(), createEmployee.getPatronymic());
        assertEquals(employee.getPost(), createEmployee.getPost());
        assertEquals(employee.getId(), createEmployee.getId());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeServiceImpl.getEmployeeById(1L);

        assertEquals(employee.getName(), foundEmployee.getName());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee updateEmployee = new Employee();
        updateEmployee.setName("John 1");
        updateEmployee.setSurName("Doe 1");
        updateEmployee.setPatronymic("Michael 1");
        updateEmployee.setPost("Developer 1");

        Employee updatedEmployee = employeeServiceImpl.updateEmployee(1L, updateEmployee);

        assertEquals("John 1", updatedEmployee.getName());
        assertEquals("Doe 1", updatedEmployee.getSurName());
        assertEquals("Michael 1", updatedEmployee.getPatronymic());
        assertEquals("Developer 1", updatedEmployee.getPost());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenThrow(ApplicationException.class);

        assertThrows(ApplicationException.class, () -> employeeServiceImpl.getEmployeeById(1L));
    }

    @Test
    public void testGetAllEmployee() {
        List<Employee> list = Arrays.asList(employee, employee);
        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> employees = employeeServiceImpl.getAllEmployee();

        assertEquals(2, employees.size());
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        employeeServiceImpl.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void delete_NotFound() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        assertThrows(ApplicationException.class, () -> employeeServiceImpl.deleteEmployee(1L));
    }
}
