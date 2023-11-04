package ru.ocupuc.triangle.models;

import static java.lang.Math.*;

public class Triangle extends GeometricFigure implements HeightCalculable {
    // Стороны треугольника
    private double sideA;
    private double sideB;
    private double sideC;

    // Кеширование вычисленных значений
    private Double area = null;
    private Double semiPerimeter = null;
    private String triangleType = null;

    // Конструктор для инициализации сторон треугольника
    public Triangle(double sideA, double sideB, double sideC) {
        validateSides(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
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
    private double calculateSemiPerimeter() {
        if (semiPerimeter == null) {
            semiPerimeter = (sideA + sideB + sideC) / 2;
        }
        return semiPerimeter;
    }

    // Расчет площади с кешированием результата
    @Override
    public double calculateArea() {
        if (area == null) {
            double s = calculateSemiPerimeter();
            area = sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        }
        return area;
    }

    // Расчет периметра треугольника
    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
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
                2 * area / sideA,
                2 * area / sideB,
                2 * area / sideC
        };
    }

    // Определение типа треугольника с кешированием результата
    public String determineTriangleType() {
        if (triangleType == null) {
            if (sideA == sideB && sideB == sideC) {
                triangleType = "Равносторонний треугольник";
            } else if (sideA == sideB || sideA == sideC || sideB == sideC) {
                triangleType = "Равнобедренный треугольник";
            } else if (isRightAngle(sideA, sideB, sideC) ||
                    isRightAngle(sideA, sideC, sideB) ||
                    isRightAngle(sideB, sideC, sideA)) {
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
        if ("Прямоугольный треугольник".equals(determineTriangleType())) {
            double hypotenuse = max(max(sideA, sideB), sideC);
            return PI * (hypotenuse / 2) * (hypotenuse / 2);
        } else {
            double area = calculateArea();
            double radius = (sideA * sideB * sideC) / (4 * area);
            return PI * radius * radius;
        }
    }

    // Расчет длины медианы
    public double calculateMedian(double side) {
        double[] otherSides = getOtherSides(side);
        return 0.5 * sqrt(2 * otherSides[0] * otherSides[0] + 2 * otherSides[1] * otherSides[1] - side * side);
    }

    // Расчет длины биссектрисы
    public double calculateBisector(double side) {
        double[] otherSides = getOtherSides(side);
        return sqrt(otherSides[0] * otherSides[1] * (otherSides[0] + otherSides[1] + side) * (otherSides[0] + otherSides[1] - side)) / (otherSides[0] + otherSides[1]);
    }

    // Расчет всех медиан треугольника
    public double[] calculateAllMedians() {
        return new double[]{
                calculateMedian(sideA),
                calculateMedian(sideB),
                calculateMedian(sideC)
        };
    }

    // Расчет всех биссектрис треугольника
    public double[] calculateAllBisectors() {
        return new double[]{
                calculateBisector(sideA),
                calculateBisector(sideB),
                calculateBisector(sideC)
        };
    }

    // Получение двух других сторон треугольника
    private double[] getOtherSides(double side) {
        if (side == sideA) {
            return new double[]{sideB, sideC};
        } else if (side == sideB) {
            return new double[]{sideA, sideC};
        } else {
            return new double[]{sideA, sideB};
        }
    }
}
