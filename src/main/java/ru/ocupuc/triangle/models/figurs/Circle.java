package ru.ocupuc.triangle.models.figurs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.AbstractFigure;

@Getter
@Setter
@NoArgsConstructor
public class Circle extends AbstractFigure {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public FigureType getType() {
        return FigureType.CIRCLE;
    }


}