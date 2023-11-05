package ru.ocupuc.triangle.models.figurs;

import lombok.AllArgsConstructor;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.Quadrilateral;


public class Rectangle extends Quadrilateral {


    public Rectangle(double sideA, double sideB) {
        super(sideA, sideB, sideA, sideB);
    }

    @Override
    public double calculateArea() {
        return getSideA()*getSideB();
    }

    @Override
    public FigureType getType() {
        return FigureType.RECTANGLE;
    }
}
