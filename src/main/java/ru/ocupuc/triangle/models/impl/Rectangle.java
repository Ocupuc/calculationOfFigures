package ru.ocupuc.triangle.models.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
@NoArgsConstructor
public class Rectangle extends Quadrilateral {
    public Rectangle(double length, double width) {
        super(new double[]{length, width, length, width});
    }

    @Override
    public double calculateArea() {
        return getSidesLengths()[0] * getSidesLengths()[1];
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (getSidesLengths()[0] + getSidesLengths()[1]);
    }

    @Override
    public String identifyFigure() {
        return "Rectangle";
    }
}