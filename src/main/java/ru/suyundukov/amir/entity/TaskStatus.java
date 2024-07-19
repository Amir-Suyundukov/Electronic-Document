package ru.suyundukov.amir.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    PREPARATION("Подготовка"),
    EXECUTION("Исполнение"),
    CONTROL("Контроль"),
    ACCEPTANCE("Прием"),
    REFINEMENT("Доработка");
    private final String value;
}
