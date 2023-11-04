package ru.ocupuc.triangle.models.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
@NoArgsConstructor
public class Trapezoid extends Quadrilateral {
    private double height; // Высота трапеции

    public Trapezoid(double base1, double base2, double side1, double side2, double height) {
        super(new double[]{base1, base2, side1, side2});
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return (getSidesLengths()[0] + getSidesLengths()[1]) / 2 * height;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 0;
        for (double side : getSidesLengths()) {
            perimeter += side;
        }
        return perimeter;
    }

    @Override
    public String identifyFigure() {
        return "Trapezoid";
    }
}
