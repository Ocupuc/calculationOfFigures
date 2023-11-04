package ru.ocupuc.triangle.models;

import static java.lang.Math.*;

public class Triangle extends GeometricFigure implements HeightCalculable {
    // Стороны треугольника
    private double sideA;
    private double sideB;
    private double sideC;

    // Ленивая инициализация для площади, чтобы не пересчитывать её каждый раз
    private Double area;
    // Ленивая инициализация для полупериметра, чтобы не пересчитывать его каждый раз
    private Double semiPerimeter;

    // Конструктор для создания экземпляра треугольника с заданными сторонами
    public Triangle(double sideA, double sideB, double sideC) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными числами.");
        }
        if (sideA >= sideB + sideC || sideB >= sideA + sideC || sideC >= sideA + sideB) {
            throw new IllegalArgumentException("Сумма длин любых двух сторон должна быть больше длины третьей стороны.");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    // Метод для расчета полупериметра
    private double calculateSemiPerimeter() {
        if (semiPerimeter == null) {
            semiPerimeter = (sideA + sideB + sideC) / 2;
        }
        return semiPerimeter;
    }

    // Метод для расчета площади треугольника по формуле Герона
    @Override
    public double calculateArea() {
        if (area == null) {
            double s = calculateSemiPerimeter();
            area = sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        }
        return area;
    }

    // Метод для расчета периметра треугольника
    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    // Метод для идентификации фигуры
    @Override
    public String identifyFigure() {
        return "Треугольник";
    }

    // Метод для расчета высот треугольника
    @Override
    public double[] calculateHeights() {
        double area = calculateArea();
        // Возвращаем массив высот для каждой стороны
        return new double[]{
                (2 * area) / sideA,
                (2 * area) / sideB,
                (2 * area) / sideC
        };
    }

    // Метод для определения типа треугольника
    public String determineTriangleType() {
        // Определение типа треугольника на основе длин его сторон
        if (sideA == sideB && sideB == sideC) {
            return "Равносторонний треугольник";
        } else if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return "Равнобедренный треугольник";
        } else {
            // Проверяем на прямоугольность один раз и сохраняем результат
            boolean isRightTriangle = isRightAngle(sideA, sideB, sideC) ||
                    isRightAngle(sideA, sideC, sideB) ||
                    isRightAngle(sideB, sideC, sideA);
            if (isRightTriangle) {
                return "Прямоугольный треугольник";
            } else {
                return "Разносторонний треугольник";
            }
        }
    }


    // Вспомогательный метод для проверки, является ли треугольник прямоугольным
    private boolean isRightAngle(double side1, double side2, double side3) {
        // Проверка на прямоугольность по теореме Пифагора
        return pow(side1, 2) + pow(side2, 2) == pow(side3, 2);
    }

    // Метод для расчета площади вписанной окружности
    public double calculateInscribedCircleArea() {
        double semiPerimeter = calculateSemiPerimeter(); // Используем существующий метод
        double radius = calculateArea() / semiPerimeter;
        return PI * pow(radius, 2);
    }

    // Метод для расчета площади описанной окружности
    public double calculateCircumscribedCircleArea() {
        // Используем уже вычисленный тип треугольника
        String triangleType = determineTriangleType();

        if ("Прямоугольный треугольник".equals(triangleType)) {
            // Находим гипотенузу, используя Math.max один раз для упрощения чтения
            double hypotenuse = Math.max(Math.max(sideA, sideB), sideC);
            return PI * pow(hypotenuse / 2, 2);
        } else {
            // Для непрямоугольных треугольников используем формулу радиуса описанной окружности
            double area = calculateArea(); // используем уже имеющийся метод calculateArea
            double radius = (sideA * sideB * sideC) / (4 * area);
            return PI * pow(radius, 2);
        }
    }


    // Метод для расчета длины медианы для одной из сторон
    public double calculateMedian(double side) {
        double[] otherSides = getOtherSides(side);
        return 0.5 * sqrt(2 * pow(otherSides[0], 2) + 2 * pow(otherSides[1], 2) - pow(side, 2));
    }

    // Метод для расчета длины биссектрисы для одной из сторон
    public double calculateBisector(double side) {
        double[] otherSides = getOtherSides(side);
        return sqrt(otherSides[0] * otherSides[1] * (otherSides[0] + otherSides[1] + side) * (otherSides[0] + otherSides[1] - side)) / (otherSides[0] + otherSides[1]);
    }




    // Метод для расчета всех медиан треугольника
    public double[] calculateAllMedians() {
        return new double[]{
                calculateMedian(sideA),
                calculateMedian(sideB),
                calculateMedian(sideC)
        };
    }

    // Метод для расчета всех биссектрис треугольника
    public double[] calculateAllBisectors() {
        return new double[]{
                calculateBisector(sideA),
                calculateBisector(sideB),
                calculateBisector(sideC)
        };
    }
    //метод для расчета бессектрис и медиан
    private double[] getOtherSides(double side) {
        double[] otherSides = new double[2];
        if (side == sideA) {
            otherSides[0] = sideB;
            otherSides[1] = sideC;
        } else if (side == sideB) {
            otherSides[0] = sideA;
            otherSides[1] = sideC;
        } else if (side == sideC) {
            otherSides[0] = sideA;
            otherSides[1] = sideB;
        }
        return otherSides;
    }


}
