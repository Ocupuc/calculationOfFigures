package ru.ocupuc.triangle.models.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.models.Quadrilateral;


@Getter
@Setter
@NoArgsConstructor
public class Parallelogram extends Quadrilateral {
    private double height; // Высота, опущенная на основание

    public Parallelogram(double baseLength, double sideLength, double height) {
        super(new double[]{baseLength, sideLength, baseLength, sideLength});
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return getSidesLengths()[0] * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (getSidesLengths()[0] + getSidesLengths()[1]);
    }

    @Override
    public String identifyFigure() {
        return "Parallelogram";
    }
}

