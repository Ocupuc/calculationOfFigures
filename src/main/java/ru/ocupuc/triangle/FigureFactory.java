package ru.ocupuc.triangle;

import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.Parallelogram;
import ru.ocupuc.triangle.models.Triangle;

public class FigureFactory {

    public static GeometricFigure createFigure(String type, double... parameters) {
        switch (type.toLowerCase()) {
            case "triangle":
                return new Triangle(parameters[0], parameters[1], parameters[2]);
            case "parallelogram":
                return new Parallelogram(parameters[0], parameters[1], parameters[2]);
            // Другие фигуры по мере добавления
            default:
                return null;
        }
    }
}


