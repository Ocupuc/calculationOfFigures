package ru.ocupuc.triangle.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Triangle extends GeometricFigure {
    private double sideA;
    private double sideB;
    private double sideC;

    @Override
    public double calculateArea() {
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String identifyFigure() {
        return "Треугольник";
    }

    public double calculateHeight() {
        double area = calculateArea();
        // Высота для стороны A, измените для других сторон при необходимости
        return (2 * area) / sideA;
    }
}
