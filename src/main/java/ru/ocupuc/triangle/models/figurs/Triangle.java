package ru.ocupuc.triangle.models.figurs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.AbstractFigure;
import ru.ocupuc.triangle.models.HeightCalculable;

import static java.lang.Math.*;

@Getter
@RequiredArgsConstructor
public class Triangle extends AbstractFigure implements HeightCalculable {

    final double sideA;
    final double sideB;
    final double sideC;

    // Кеширование вычисленных значений
    private Double area = null;
    private String triangleType = null;

    // Расчет полупериметра с кешированием результата
    public double calculateSemiPerimeter() {

        return calculatePerimeter() / 2;
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

    @Override
    public FigureType getType() {
        return FigureType.TRIANGLE;
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

    // Расчет длины медианы для стороны c
    public double calculateMedianLength(double a, double b, double c) {
        return 0.5 * sqrt(2 * (a * a) + 2 * (b * b) - (c * c));
    }

    // Расчет всех медиан треугольника
    public double[] calculateAllMedians() {
        return new double[]{
                calculateMedianLength(sideB, sideC, sideA),
                calculateMedianLength(sideA, sideC, sideB),
                calculateMedianLength(sideA, sideB, sideC)
        };
    }

    // Расчет длины биссектрисы для стороны c
    public double calculateBisectorLength(double a, double b, double c) {
        double p = calculateSemiPerimeter();
        return (2 * sqrt(a * b * p * (p - c))) / (a + b);
    }

    // Расчет всех биссектрис треугольника
    public double[] calculateAllBisectors() {
        return new double[]{
                calculateBisectorLength(sideA, sideB, sideC),
                calculateBisectorLength(sideA, sideC, sideB),
                calculateBisectorLength(sideB, sideC, sideA)
        };
    }

}
