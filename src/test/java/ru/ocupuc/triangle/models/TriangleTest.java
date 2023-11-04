package ru.ocupuc.triangle.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.ocupuc.triangle.models.Triangle;

public class TriangleTest {

    @Test
    public void testCreateTriangle() {
        assertDoesNotThrow(() -> new Triangle(3, 4, 5));
    }

    @Test
    public void testInvalidTriangle() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 2, 3));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 3));
    }

    @Test
    public void testAreaCalculation() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals(6, triangle.calculateArea(), "Площадь прямоугольного треугольника не соответствует ожидаемой.");
    }

    @Test
    public void testPerimeterCalculation() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals(12, triangle.calculatePerimeter(), "Периметр треугольника не соответствует ожидаемому.");
    }

    @Test
    public void testIdentifyFigure() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Треугольник", triangle.identifyFigure(), "Идентификация фигуры неверна.");
    }

    @Test
    public void testCalculateHeights() {
        Triangle triangle = new Triangle(3, 4, 5);
        double[] heights = triangle.calculateHeights();
        assertAll("Высоты треугольника",
                () -> assertEquals(4, heights[0], "Высота на сторону A неверна."),
                () -> assertEquals(3, heights[1], "Высота на сторону B неверна."),
                () -> assertEquals(2.4, heights[2], 1e-10, "Высота на сторону C неверна.")
        );
    }

    @Test
    public void testDetermineTriangleType() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Прямоугольный треугольник", triangle.determineTriangleType(), "Тип треугольника неверно определен.");
    }

    @Test
    public void testInscribedCircleArea() {
        Triangle triangle = new Triangle(3, 4, 5);
        double area = triangle.calculateInscribedCircleArea();
        assertTrue(area > 0, "Площадь вписанной окружности должна быть больше нуля.");
    }

    @Test
    public void testCircumscribedCircleArea() {
        Triangle triangle = new Triangle(3, 4, 5);
        double area = triangle.calculateCircumscribedCircleArea();
        assertTrue(area > 0, "Площадь описанной окружности должна быть больше нуля.");
    }

    @Test
    public void testCalculateAllMedians() {
        Triangle triangle = new Triangle(3, 4, 5);
        double[] medians = triangle.calculateAllMedians();
        assertAll("Медианы треугольника",
                () -> assertTrue(medians[0] > 0, "Медиана на сторону A должна быть больше нуля."),
                () -> assertTrue(medians[1] > 0, "Медиана на сторону B должна быть больше нуля."),
                () -> assertTrue(medians[2] > 0, "Медиана на сторону C должна быть больше нуля.")
        );
    }

    @Test
    public void testCalculateAllBisectors() {
        Triangle triangle = new Triangle(3, 4, 5);
        double[] bisectors = triangle.calculateAllBisectors();
        assertAll("Биссектрисы треугольника",
                () -> assertTrue(bisectors[0] > 0, "Биссектриса на сторону A должна быть больше нуля."),
                () -> assertTrue(bisectors[1] > 0, "Биссектриса на сторону B должна быть больше нуля."),
                () -> assertTrue(bisectors[2] > 0, "Биссектриса на сторону C должна быть больше нуля.")
        );
    }
}