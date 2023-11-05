package ru.ocupuc.triangle.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Quadrilateral extends AbstractFigure {

    private double sideA;
    private double sideB;
    private double sideC;
    private double sideD;

    @Override
    public double calculatePerimeter() {
        return sideA+sideB+sideC+sideD;
    }
}
