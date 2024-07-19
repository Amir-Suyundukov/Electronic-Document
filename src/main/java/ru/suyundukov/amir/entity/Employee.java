package ru.suyundukov.amir.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Имя
     */
    @Column(name = "name")
    private String name;
    /**
     * Фамилия
     */
    @Column(name = "surname")
    private String surName;
    /**
     * Отчество
     */
    @Column(name = "patronymic")
    private String patronymic;
    /**
     * Должность
     */
    @Column(name = "post")
    private String post;
    /**
     * Подразделение, к которому относится сотрудник
     */
    @ManyToOne
    @JoinColumn(name = "subdivision_id")
    private Subdivision subdivision;
    /**
     * Организация, которой руководит сотрудник
     */
    @OneToOne(mappedBy = "director")
    private Organization managedOrganization;
    /**
     * Подразделение, которым руководит сотрудник
     */
    @OneToOne(mappedBy = "director")
    private Subdivision managedSubdivision;
    /**
     * Задачи, созданные сотрудником
     */
    @OneToMany(mappedBy = "authorOrder", cascade = CascadeType.ALL)
    private List<Task> authoredTasks;
    /**
     * Организация, к которой относится сотрудник
     */
    @ManyToMany(mappedBy = "employeeExecutors", cascade = CascadeType.ALL)
    private List<Task> employeeTasks = new ArrayList<>();

}
