package ru.ocupuc.triangle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FigureType {
    TRIANGLE("Треугольник"),
    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    SQUARE("Квадратик");
    String name;
}
