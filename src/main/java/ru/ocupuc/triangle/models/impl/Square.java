package ru.ocupuc.triangle.models.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
@NoArgsConstructor
public class Square extends Quadrilateral {
    public Square(double sideLength) {
        super(new double[]{sideLength, sideLength, sideLength, sideLength});
    }

    @Override
    public double calculateArea() {
        return Math.pow(getSidesLengths()[0], 2);
    }

    @Override
    public double calculatePerimeter() {
        return 4 * getSidesLengths()[0];
    }

    @Override
    public String identifyFigure() {
        return "Square";
    }
}