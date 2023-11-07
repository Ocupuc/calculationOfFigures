package ru.ocupuc.triangle;

import ru.ocupuc.triangle.models.shapes.*;

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

    public static Trapezoid createTrapezoid(double sideB, double sideD, double height) {
        checkAllPositive(sideB, sideD, height);
        validateTrapezoidBases(sideB, sideD);
        return new Trapezoid(sideB, sideD, height);
    }

    private static void validateTrapezoidBases(double sideB, double sideD) {
        if (sideB == sideD) {
            throw new IllegalArgumentException("Основания трапеции не должны быть равны.");
        }
    }


    // Фабричный метод для создания экземпляра параллелограмма
    public static Parallelogram createParallelogram(double sideA, double sideB, double height) {
        checkAllPositive(sideA, sideB, height);
        return new Parallelogram(sideA, sideB, height);
    }
    // Фабричный метод для создания экземпляра ромб
    public static Rhombus createRhombus(double sideSize,  double height) {
        checkAllPositive(sideSize, height);
        return new Rhombus(sideSize, height);
    }


    private static void checkAllPositive(double... values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] <= 0) {
                throw new IllegalArgumentException("Стороны должны быть положительными числами.");
            }
        }
    }
}



