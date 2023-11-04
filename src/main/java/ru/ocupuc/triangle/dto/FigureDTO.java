package ru.ocupuc.triangle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FigureDTO {

    @NotBlank(message = "Тип фигуры не может быть пустым")
    private String type;

    @NotNull(message = "Параметры фигуры не могут быть null")
    @Size(min = 1, message = "Должен быть хотя бы один параметр")
    private double[] parameters;

}
