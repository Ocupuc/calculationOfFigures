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

    // Фабричный метод для создания экземпляра трапеции
    public static Trapezoid createTrapezoid(double sideA, double sideB, double sideC, double sideD, double height) {
        checkAllPositive(sideA, sideB, sideC, sideD, height);
        validateTrapezoid(sideA, sideB, sideC, sideD, height);
        return new Trapezoid(sideA, sideB, sideC, sideD, height);
    }

    // Валидация параметров трапеции
    private static void validateTrapezoid(double a, double b, double c, double d, double h) {
        if (!(a + d > b + c && b + c > Math.abs(a - d))) {
            throw new IllegalArgumentException("Для трапеции сумма длин противоположных сторон должна быть больше суммы двух других сторон.");
        }
        if (h >= a || h >= b || h >= c || h >= d) {
            throw new IllegalArgumentException("Высота должна быть меньше длины любой из сторон трапеции.");
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



