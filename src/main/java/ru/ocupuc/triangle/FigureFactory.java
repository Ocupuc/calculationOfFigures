package ru.ocupuc.triangle;

import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.impl.Parallelogram;
import ru.ocupuc.triangle.models.impl.Triangle;

public class FigureFactory {

    public static GeometricFigure createFigure(String type, double... parameters) {
        switch (type.toLowerCase()) {
            case "triangle":
                // Убедитесь, что для треугольника передано ровно три параметра
                if (parameters.length == 3) {
                    return new Triangle(parameters); // Используется один массив, а не три аргумента
                } else {
                    throw new IllegalArgumentException("Для треугольника необходимо три параметра.");
                }
            case "parallelogram":

                return new Parallelogram(parameters[0], parameters[1], parameters[2]);

            default:
                return null;
        }
    }
}



