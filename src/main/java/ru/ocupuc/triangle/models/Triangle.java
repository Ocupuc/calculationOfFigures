package ru.ocupuc.triangle.models;

import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Triangle extends GeometricFigure implements HeightCalculable {
    private double sideA;
    private double sideB;
    private double sideC;

    @Override
    public double calculateArea() {
        // Формула Герона для расчета площади треугольника
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

    @Override
    public double[] calculateHeights() {
        double area = calculateArea();
        return new double[] {
                (2 * area) / sideA, // Высота относительно стороны A
                (2 * area) / sideB, // Высота относительно стороны B
                (2 * area) / sideC  // Высота относительно стороны C
        };
    }
}

