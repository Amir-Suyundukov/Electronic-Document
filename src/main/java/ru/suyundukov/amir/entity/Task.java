package ru.suyundukov.amir.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Task {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * предмет поручения
     */
    @Column(name = "subjectorder")
    private String subjectOrder;
    /**
     * Автор поручения
     */
    @JoinColumn(name = "author_id")
    @OneToOne
    private Employee authorOrder;
    /**
     * Срок исполнения
     */
    @Column(name = "deadline")
    private LocalDate deadline;
    /**
     * Признак контрольности
     */
    @Column(name = "singofcontrol")
    private boolean signOfControl;
    /**
     * Признак исполнения
     */
    @Column(name = "singofexecution")
    private boolean signOfExecution;
    /**
     * Текст поручения
     */
    @Column(name = "text")
    private String text;
    /**
     * Статус поручения
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
    /**
     * Исполнители поручения
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employeeExecutors = new ArrayList<>();
}
