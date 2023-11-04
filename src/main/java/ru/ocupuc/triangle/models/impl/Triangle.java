package ru.ocupuc.triangle.models.impl;

import ru.ocupuc.triangle.models.HeightCalculable;
import ru.ocupuc.triangle.models.Polygon;

import static java.lang.Math.*;

public class Triangle extends Polygon implements HeightCalculable {

    // Кеширование вычисленных значений
    private Double area = null;
    private Double semiPerimeter = null;
    private String triangleType = null;

    // Конструктор принимает массив длин сторон треугольника
    public Triangle(double[] sidesLengths) {
        super(3, sidesLengths);
        validateSides(sidesLengths[0], sidesLengths[1], sidesLengths[2]);
    }

    // Валидация сторон треугольника
    private void validateSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными числами.");
        }
        if (a >= b + c || b >= a + c || c >= a + b) {
            throw new IllegalArgumentException("Сумма двух сторон должна быть больше третьей.");
        }
    }

    // Расчет полупериметра с кешированием результата
    public double calculateSemiPerimeter() {
        if (semiPerimeter == null) {
            semiPerimeter = 0.0;
            for (double side : getSidesLengths()) {
                semiPerimeter += side;
            }
            semiPerimeter /= 2;
        }
        return semiPerimeter;
    }

    // Расчет площади с кешированием результата
    @Override
    public double calculateArea() {
        if (area == null) {
            double s = calculateSemiPerimeter();
            area = sqrt(s * (s - getSidesLengths()[0]) * (s - getSidesLengths()[1]) * (s - getSidesLengths()[2]));
        }
        return area;
    }

    // Расчет периметра треугольника
    @Override
    public double calculatePerimeter() {
        return calculateSemiPerimeter() * 2;
    }

    // Определение фигуры
    @Override
    public String identifyFigure() {
        return "Треугольник";
    }

    // Расчет высот треугольника
    @Override
    public double[] calculateHeights() {
        double area = calculateArea();
        return new double[]{
                2 * area / getSidesLengths()[0],
                2 * area / getSidesLengths()[1],
                2 * area / getSidesLengths()[2]
        };
    }

    // Определение типа треугольника с кешированием результата
    public String determineTriangleType() {
        if (triangleType == null) {
            double[] sides = getSidesLengths();
            if (sides[0] == sides[1] && sides[1] == sides[2]) {
                triangleType = "Равносторонний треугольник";
            } else if (sides[0] == sides[1] || sides[0] == sides[2] || sides[1] == sides[2]) {
                triangleType = "Равнобедренный треугольник";
            } else if (isRightAngle(sides[0], sides[1], sides[2]) ||
                    isRightAngle(sides[0], sides[2], sides[1]) ||
                    isRightAngle(sides[1], sides[2], sides[0])) {
                triangleType = "Прямоугольный треугольник";
            } else {
                triangleType = "Разносторонний треугольник";
            }
        }
        return triangleType;
    }

    // Проверка на прямоугольность треугольника
    private boolean isRightAngle(double side1, double side2, double side3) {
        return abs(side1 * side1 + side2 * side2 - side3 * side3) < 1e-10;
    }

    // Расчет площади вписанной окружности
    public double calculateInscribedCircleArea() {
        double radius = calculateArea() / calculateSemiPerimeter();
        return PI * radius * radius;
    }

    // Расчет площади описанной окружности
    public double calculateCircumscribedCircleArea() {
        double[] sides = getSidesLengths();
        if ("Прямоугольный треугольник".equals(determineTriangleType())) {
            double hypotenuse = max(max(sides[0], sides[1]), sides[2]);
            return PI * (hypotenuse / 2) * (hypotenuse / 2);
        } else {
            double area = calculateArea();
            double radius = (sides[0] * sides[1] * sides[2]) / (4 * area);
            return PI * radius * radius;
        }
    }

    // Расчет длины медианы для стороны c
    public double calculateMedianLength(double a, double b, double c) {
        return 0.5 * sqrt(2 * (a * a) + 2 * (b * b) - (c * c));
    }

    // Расчет всех медиан треугольника
    public double[] calculateAllMedians() {
        double[] sides = getSidesLengths();
        return new double[]{
                calculateMedianLength(sides[1], sides[2], sides[0]),
                calculateMedianLength(sides[0], sides[2], sides[1]),
                calculateMedianLength(sides[0], sides[1], sides[2])
        };
    }

    // Расчет длины биссектрисы для стороны c
    public double calculateBisectorLength(double a, double b, double c) {
        double p = calculateSemiPerimeter();
        return (2 * sqrt(a * b * p * (p - c))) / (a + b);
    }

    // Расчет всех биссектрис треугольника
    public double[] calculateAllBisectors() {
        double[] sides = getSidesLengths();
        return new double[]{
                calculateBisectorLength(sides[0], sides[1], sides[2]),
                calculateBisectorLength(sides[0], sides[2], sides[1]),
                calculateBisectorLength(sides[1], sides[2], sides[0])
        };
    }

}
