package ru.ocupuc.triangle;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FigureDTO {
    private String type; // Тип фигуры, например, "triangle"
    private double[] parameters; // Параметры фигуры, например, стороны треугольника
}
