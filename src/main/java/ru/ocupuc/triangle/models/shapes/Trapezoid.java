package ru.ocupuc.triangle.models.shapes;

import lombok.Getter;
import lombok.Setter;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
public class Trapezoid extends Quadrilateral {

    private double height; // Высота трапеции

    public Trapezoid(double sideB, double sideD, double height) {
        super(0, sideB, 0, sideD);
        this.height = height;
    }

    public double calculateSide() {
        // Проверяем, что основания различны, иначе это будет параллелограмм
        if (getSideB() == getSideD()) {
            throw new IllegalArgumentException("Основания трапеции не могут быть равны");
        }
        // Расчет боковой стороны для равнобедренной трапеции
        return Math.sqrt(Math.pow((getSideB() - getSideD()) / 2, 2) + Math.pow(height, 2));
    }

    @Override
    public double calculateArea() {
        // Формула площади трапеции
        return (getSideB() + getSideD()) * height / 2;
    }

    @Override
    public double calculatePerimeter() {
        // Расчет периметра для равнобедренной трапеции
        double side = calculateSide();
        return getSideB() + getSideD() + 2 * side;
    }

    @Override
    public FigureType getType() {
        return FigureType.TRAPEZOID;
    }
}