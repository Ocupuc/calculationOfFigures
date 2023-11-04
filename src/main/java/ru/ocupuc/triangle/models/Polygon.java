package ru.ocupuc.triangle.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Polygon extends GeometricFigure {
    private int numberOfSides;
    private double[] sidesLengths;

    public Polygon(int numberOfSides, double[] sidesLengths) {
        this.numberOfSides = numberOfSides;
        this.sidesLengths = sidesLengths;
    }

    // Реализация метода идентификации фигуры
    @Override
    public String identifyFigure() {
        return "Polygon with " + numberOfSides + " sides.";
    }
}
