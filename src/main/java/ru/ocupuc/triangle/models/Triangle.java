package ru.ocupuc.triangle.models;

import static java.lang.Math.*;

public class Triangle extends GeometricFigure implements HeightCalculable {
    private double sideA;
    private double sideB;
    private double sideC;
    private Double area; // Ленивая инициализация для площади
    private Double semiPerimeter; // Ленивая инициализация для полупериметра

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    private double calculateSemiPerimeter() {
        if (semiPerimeter == null) {
            semiPerimeter = (sideA + sideB + sideC) / 2;
        }
        return semiPerimeter;
    }

    @Override
    public double calculateArea() {
        if (area == null) {
            double s = calculateSemiPerimeter();
            area = Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        }
        return area;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String identifyFigure() {
        return "Треугольник";
    }

    @Override
    public double[] calculateHeights() {
        double area = calculateArea();
        return new double[]{
                (2 * area) / sideA,
                (2 * area) / sideB,
                (2 * area) / sideC
        };
    }


    public String determineTriangleType() {
        if (sideA == sideB && sideB == sideC) {
            return "Равносторонний треугольник";
        } else if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return "Равнобедренный треугольник";
        } else if (isRightAngle(sideA, sideB, sideC) ||
                isRightAngle(sideA, sideC, sideB) ||
                isRightAngle(sideB, sideC, sideA)) {
            return "Прямоугольный треугольник";
        } else {
            return "Разносторонний треугольник";
        }
    }

    private boolean isRightAngle(double side1, double side2, double side3) {
        return Math.pow(side1, 2) + Math.pow(side2, 2) == Math.pow(side3, 2);
    }

    public double calculateInscribedCircleArea() {
        // Расчет площади вписанной окружности
        double semiPerimeter = calculatePerimeter() / 2;
        double radius = calculateArea() / semiPerimeter;
        return PI * pow(radius, 2);
    }


    public double calculateCircumscribedCircleArea() {
        // Проверяем, является ли треугольник прямоугольным
        if ("Прямоугольный треугольник".equals(determineTriangleType())) {
            // Если треугольник прямоугольный, то используем гипотенузу как диаметр
            double hypotenuse = max(sideA, max(sideB, sideC));
            return Math.PI * Math.pow(hypotenuse / 2, 2);
        } else {
            // Для непрямоугольных треугольников используем формулу радиуса описанной окружности
            // через стороны треугольника и его площадь
            double area = calculateArea(); // Здесь используем уже имеющийся метод calculateArea
            double radius = (sideA * sideB * sideC) / (4 * area);
            return Math.PI * Math.pow(radius, 2);
        }
    }
}
