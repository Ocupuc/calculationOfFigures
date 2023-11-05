package ru.ocupuc.triangle.models.shapes;

import lombok.Getter;
import lombok.Setter;
import ru.ocupuc.triangle.FigureType;
import ru.ocupuc.triangle.models.Quadrilateral;

@Getter
@Setter
public class Parallelogram extends Quadrilateral {

    private double height;

    // Изменен конструктор для инициализации высоты
    public Parallelogram(double sideA, double sideB, double height) {
        super(sideA, sideB, sideA, sideB); // Параллелограмм имеет пары равных сторон
        this.height = height; // Инициализация высоты
    }



    @Override
    public double calculateArea() {
        // Площадь вычисляется как произведение основания на высоту
        return getSideA() * height;
    }

    @Override
    public FigureType getType() {
        // Возвращает тип фигуры из перечисления
        return FigureType.PARALLELOGRAM;
    }

    // Метод для установки высоты, основываясь на площади и стороне A
    public void setHeightBasedOnArea(double area) {
        this.height = area / getSideA();
    }

}
