package ru.ocupuc.triangle.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Quadrilateral extends Polygon {
    public Quadrilateral(double[] sidesLengths) {
        super(4, sidesLengths);
    }
}
