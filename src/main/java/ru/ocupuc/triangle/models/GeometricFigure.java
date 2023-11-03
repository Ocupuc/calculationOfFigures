package ru.ocupuc.triangle.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class GeometricFigure {

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    public abstract String identifyFigure();
}
