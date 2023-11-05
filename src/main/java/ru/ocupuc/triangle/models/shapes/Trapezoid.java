package ru.ocupuc.triangle.models.shapes;

import lombok.Getter;
import lombok.Setter;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.Quadrilateral;


@Getter
@Setter
public class Trapezoid extends Quadrilateral {

    private double height; // Высота трапеции

    public Trapezoid(double sideA, double sideB, double sideC, double sideD, double height) {
        super(sideA, sideB, sideC, sideD);
        this.height = height;
    }

    @Override
    public double calculateArea() {
        // Формула площади трапеции: (a + b) * h / 2, где a и b - основания, h - высота
        // Предполагаем, что sideA и sideB - это основания
        return (getSideA() + getSideB()) * height / 2;
    }

    @Override
    public FigureType getType() {
        return FigureType.TRAPEZOID;
    }

}
