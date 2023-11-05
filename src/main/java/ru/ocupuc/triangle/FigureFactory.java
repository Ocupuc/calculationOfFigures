package ru.ocupuc.triangle;

import ru.ocupuc.triangle.models.figurs.Circle;
import ru.ocupuc.triangle.models.figurs.Rectangle;
import ru.ocupuc.triangle.models.figurs.Square;
import ru.ocupuc.triangle.models.figurs.Triangle;

public class FigureFactory {

    //Создаем треугольник
    public static Triangle createTriangle(double sideA, double sideB, double sideC) {
        validateTriangle(sideA, sideB, sideC);
        return new Triangle(sideA, sideB, sideC);
    }

    // Валидация сторон треугольника
    private static void validateTriangle(double a, double b, double c) {
        checkAllPositive(a, b, c);
        if (a >= b + c || b >= a + c || c >= a + b) {
            throw new IllegalArgumentException("Сумма двух сторон должна быть больше третьей.");
        }
    }
    // Фабричный метод для создания экземпляра круга
    public static Circle createCircle(double radius) {
        checkAllPositive(radius);
        return new Circle(radius);
    }

    public static Square createSquare(double sideSize) {
        checkAllPositive(sideSize);
        return new Square(sideSize);
    }


    public static Rectangle createRectangle(double sideA,double sideB) {

        checkAllPositive(sideA, sideB);
        return new Rectangle(sideA, sideB);
    }


    private static void checkAllPositive(double... values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] <= 0) {
                throw new IllegalArgumentException("Стороны должны быть положительными числами.");
            }
        }
    }
}



