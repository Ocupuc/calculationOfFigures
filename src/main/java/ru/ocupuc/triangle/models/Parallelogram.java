package ru.ocupuc.triangle.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parallelogram extends GeometricFigure implements HeightCalculable {
    private double base;
    private double height;
    private double side;

    @Override
    public double calculateArea() {
        return base * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (base + side);
    }

    @Override
    public String identifyFigure() {
        return "Параллелограмм";
    }


    @Override
    public double[] calculateHeights() {
        return new double[1];
    }
}

