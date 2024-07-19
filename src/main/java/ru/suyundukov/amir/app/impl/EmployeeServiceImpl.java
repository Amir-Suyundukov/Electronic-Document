package ru.suyundukov.amir.app.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyundukov.amir.adapter.repository.EmployeeRepository;
import ru.suyundukov.amir.app.api.EmployeeInbound;
import ru.suyundukov.amir.entity.Employee;
import ru.suyundukov.amir.exception.ApplicationException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeInbound {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        log.info("Create employee");
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        log.info("Update employee by Id : {}", id);
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Employee not found by Id : " + id));
        foundEmployee.setName(employee.getName());
        foundEmployee.setSurName(employee.getSurName());
        foundEmployee.setPatronymic(employee.getPatronymic());
        foundEmployee.setPost(employee.getPost());
        return employeeRepository.save(foundEmployee);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmployeeById(Long id) {
        log.info("Get employee by Id : {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Employee not found by Id : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployee() {
        log.info("Get all employees");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        log.info("Delete employee by id : {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new ApplicationException("Employee not found by id : " + id);
        }
        employeeRepository.deleteById(id);
    }
}
