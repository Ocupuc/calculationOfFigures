package ru.ocupuc.triangle.models.figurs;

import ru.ocupuc.triangle.FigureType;

public class Square extends Rectangle {

    public Square(double sideA) {
        super(sideA, sideA);
    }

    @Override
    public FigureType getType() {
        return FigureType.SQUARE;
    }
}
