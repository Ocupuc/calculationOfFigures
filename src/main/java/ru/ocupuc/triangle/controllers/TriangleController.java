package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.Triangle;
import ru.ocupuc.triangle.dto.FigureDTO;

@RestController // Определяет, что данный класс является REST-контроллером
@RequestMapping("/triangles") // Определяет базовый путь для всех методов в данном контроллере
public class TriangleController {

    // Этот метод отвечает за определение типа треугольника
    @PostMapping("/determineType")
    public ResponseEntity<String> determineTriangleType(@RequestBody FigureDTO figureDTO) {
        // Создаем фигуру с помощью фабричного метода
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());

        if (figure == null || !(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().build(); // Возвращаем ошибку, если это не так
        }
        Triangle triangle = (Triangle) figure;
        String type = triangle.determineTriangleType();
        return ResponseEntity.ok(type);
    }


}
