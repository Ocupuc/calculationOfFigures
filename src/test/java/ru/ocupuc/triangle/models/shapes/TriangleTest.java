package ru.ocupuc.triangle.models.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private Triangle equilateralTriangle; // Равносторонний треугольник
    private Triangle isoscelesTriangle;  // Равнобедренный треугольник
    private Triangle scaleneTriangle;    // Разносторонний треугольник
    private Triangle rightTriangle;      // Прямоугольный треугольник

    @BeforeEach
    void setUp() {
        equilateralTriangle = new Triangle(2.0, 2.0, 2.0);
        isoscelesTriangle = new Triangle(2.0, 2.0, 3.0);
        scaleneTriangle = new Triangle(2.0, 3.0, 4.0);
        rightTriangle = new Triangle(3.0, 4.0, 5.0);
    }

    @Test
    void testCalculateArea() {
        // Площадь равностороннего треугольника
        assertEquals(Math.sqrt(3), equilateralTriangle.calculateArea(), 1e-10);

        // Площадь разностороннего треугольника
        assertEquals(2.9047375096555625, scaleneTriangle.calculateArea(), 1e-10);

        // Площадь прямоугольного треугольника
        assertEquals(6.0, rightTriangle.calculateArea(), 1e-10);
    }

    @Test
    void testCalculatePerimeter() {
        // Периметр равностороннего треугольника
        assertEquals(6.0, equilateralTriangle.calculatePerimeter(), 1e-10);

        // Периметр разностороннего треугольника
        assertEquals(9.0, scaleneTriangle.calculatePerimeter(), 1e-10);

        // Периметр прямоугольного треугольника
        assertEquals(12.0, rightTriangle.calculatePerimeter(), 1e-10);
    }

    @Test
    void testIdentifyFigure() {
        assertEquals("Треугольник", equilateralTriangle.getType().getName());
        assertEquals("Треугольник", scaleneTriangle.getType().getName());
        assertEquals("Треугольник", rightTriangle.getType().getName());
    }

    @Test
    void testCalculateHeights() {
        // Высоты равностороннего треугольника
        assertArrayEquals(new double[]{Math.sqrt(3), Math.sqrt(3), Math.sqrt(3)},
                equilateralTriangle.calculateHeights(), 1e-10);

        // Высоты разностороннего треугольника
        double areaScalene = scaleneTriangle.calculateArea();
        double[] expectedScaleneHeights = {
                2 * areaScalene / 2.0,
                2 * areaScalene / 3.0,
                2 * areaScalene / 4.0
        };
        assertArrayEquals(expectedScaleneHeights, scaleneTriangle.calculateHeights(), 1e-10);
    }

    @Test
    void testDetermineTriangleType() {
        assertEquals("Равносторонний треугольник", equilateralTriangle.determineTriangleType());
        assertEquals("Равнобедренный треугольник", isoscelesTriangle.determineTriangleType());
        assertEquals("Разносторонний треугольник", scaleneTriangle.determineTriangleType());
        assertEquals("Прямоугольный треугольник", rightTriangle.determineTriangleType());
    }

    @Test
    void testCalculateInscribedCircleArea() {
        // Площадь вписанной окружности для равностороннего треугольника
        double rEquilateral = equilateralTriangle.calculateArea() / equilateralTriangle.calculateSemiPerimeter();
        assertEquals(Math.PI * rEquilateral * rEquilateral, equilateralTriangle.calculateInscribedCircleArea(), 1e-10);

        // Площадь вписанной окружности для прямоугольного треугольника
        double rRight = rightTriangle.calculateArea() / rightTriangle.calculateSemiPerimeter();
        assertEquals(Math.PI * rRight * rRight, rightTriangle.calculateInscribedCircleArea(), 1e-10);
    }

    @Test
    void testCalculateCircumscribedCircleArea() {
        // Площадь описанной окружности для равностороннего треугольника
        double rEquilateralCircum = equilateralTriangle.getSideA() / (Math.sqrt(3));
        assertEquals(Math.PI * rEquilateralCircum * rEquilateralCircum, equilateralTriangle.calculateCircumscribedCircleArea(), 1e-10);

        // Площадь описанной окружности для прямоугольного треугольника
        double rRightCircum = rightTriangle.getSideC() / 2.0;
        assertEquals(Math.PI * rRightCircum * rRightCircum, rightTriangle.calculateCircumscribedCircleArea(), 1e-10);
    }

    @Test
    void testCalculateMedianLength() {
        // Длина медианы для стороны c равностороннего треугольника
        assertEquals(equilateralTriangle.getSideA() * Math.sqrt(3) / 2, equilateralTriangle.calculateMedianLength(2.0, 2.0, 2.0), 1e-10);

        // Длина медианы для стороны c прямоугольного треугольника
        assertEquals(2.5, rightTriangle.calculateMedianLength(3.0, 4.0, 5.0), 1e-10);
    }

    @Test
    void testCalculateAllMedians() {
        // Медианы равностороннего треугольника
        double[] expectedEquilateralMedians = {equilateralTriangle.calculateMedianLength(2.0, 2.0, 2.0),
                equilateralTriangle.calculateMedianLength(2.0, 2.0, 2.0),
                equilateralTriangle.calculateMedianLength(2.0, 2.0, 2.0)};
        assertArrayEquals(expectedEquilateralMedians, equilateralTriangle.calculateAllMedians(), 1e-10);

        // Медианы прямоугольного треугольника
        double[] expectedRightMedians = {rightTriangle.calculateMedianLength(4.0, 5.0, 3.0),
                rightTriangle.calculateMedianLength(3.0, 5.0, 4.0),
                rightTriangle.calculateMedianLength(3.0, 4.0, 5.0)};
        assertArrayEquals(expectedRightMedians, rightTriangle.calculateAllMedians(), 1e-10);
    }

    @Test
    void testCalculateBisectorLength() {
        // Длина биссектрисы для стороны c равностороннего треугольника
        assertEquals(Math.sqrt(3), equilateralTriangle.calculateBisectorLength(2.0, 2.0, 2.0), 1e-10);

        // Длина биссектрисы для стороны c прямоугольного треугольника
        double pRight = rightTriangle.calculateSemiPerimeter();
        assertEquals((2 * Math.sqrt(3.0 * 4.0 * pRight * (pRight - 5.0))) / (3.0 + 4.0), rightTriangle.calculateBisectorLength(3.0, 4.0, 5.0), 1e-10);
    }

    @Test
    void testCalculateAllBisectors() {
        // Биссектрисы равностороннего треугольника
        double equilateralExpectedBisector = equilateralTriangle.calculateBisectorLength(2.0, 2.0, 2.0);
        double[] expectedEquilateralBisectors = {equilateralExpectedBisector, equilateralExpectedBisector, equilateralExpectedBisector};
        assertArrayEquals(expectedEquilateralBisectors, equilateralTriangle.calculateAllBisectors(), 1e-10);

        // Биссектрисы прямоугольного треугольника
        double[] expectedRightBisectors = {
                2.4243661069253055,  // Биссектриса против стороны a=3
                3.3541019662496847, // Биссектриса против стороны b=4
                4.216370213557839   // Биссектриса против стороны c=5 (гипотенузы)
        };
        assertArrayEquals(expectedRightBisectors, rightTriangle.calculateAllBisectors(), 1e-10);
    }

}
