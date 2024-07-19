package ru.suyundukov.amir.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "subdivision")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subdivision {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование
     */
    @Column(name = "name")
    private String name;
    /**
     * Контактные данные
     */
    @Column(name = "contactdetails")
    private String contactDetails;
    /**
     * Руководитель
     */
    @JoinColumn(name = "director_id")
    @OneToOne
    private Employee director;
    /**
     * Организация, к которой относится подразделение
     */
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    /**
     * Список сотрудников, которые находятся в данном подразделении
     */
    @OneToMany(mappedBy = "subdivision")
    private List<Employee> employees;
}
