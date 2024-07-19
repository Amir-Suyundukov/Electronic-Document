package ru.suyundukov.amir.app.api;

import ru.suyundukov.amir.entity.Employee;

import java.util.List;

public interface EmployeeInbound {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployee();

    void deleteEmployee(Long id);
}
