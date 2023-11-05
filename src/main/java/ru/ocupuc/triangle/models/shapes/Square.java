package ru.ocupuc.triangle.models.shapes;

import ru.ocupuc.triangle.FigureType;

public class Square extends Rectangle {

    public Square(double sideA) {
        super(sideA, sideA);
    }

    @Override
    public FigureType getType() {
        return FigureType.SQUARE;
    }

    // Метод для расчета площади вписанной окружности
    public double inscribedCircleArea() {
        double radius = getSideA() / 2;
        return Math.PI * radius * radius;
    }

    // Метод для расчета площади описанной окружности
    public double circumscribedCircleArea() {
        double radius = (getSideA() * Math.sqrt(2)) / 2;
        return Math.PI * radius * radius;
    }
}
