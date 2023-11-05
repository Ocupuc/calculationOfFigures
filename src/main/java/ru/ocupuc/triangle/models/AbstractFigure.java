package ru.ocupuc.triangle.models;

import lombok.*;
import ru.ocupuc.triangle.FigureType;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractFigure {

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    public abstract FigureType getType();
}
