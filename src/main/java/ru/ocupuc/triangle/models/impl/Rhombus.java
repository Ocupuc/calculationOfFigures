package ru.ocupuc.triangle.models.impl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
@NoArgsConstructor
public class Rhombus extends Quadrilateral {
    private double diagonal1;
    private double diagonal2;

    public Rhombus(double sideLength, double diagonal1, double diagonal2) {
        super(new double[]{sideLength, sideLength, sideLength, sideLength});
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    @Override
    public double calculateArea() {
        return (diagonal1 * diagonal2) / 2;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * getSidesLengths()[0];
    }

    @Override
    public String identifyFigure() {
        return "Rhombus";
    }
}
