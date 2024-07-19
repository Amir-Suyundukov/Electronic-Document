package ru.suyundukov.amir.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {
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
     * Физ.адрес
     */
    @Column(name = "fizaddres")
    private String fizAddress;
    /**
     * Юр.адрес
     */
    @Column(name = "legaladdres")
    private String legalAddress;
    /**
     * Руководитель
     */
    @JoinColumn(name = "director_id")
    @OneToOne
    private Employee director;
    /**
     * Список организации
     */
    @OneToMany(mappedBy = "organization")
    private List<Subdivision> subdivisions;
}