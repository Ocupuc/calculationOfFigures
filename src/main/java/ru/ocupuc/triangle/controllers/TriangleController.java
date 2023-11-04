package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.dto.FigureDTO;
import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.Triangle;

import javax.validation.Valid;

@RestController
@RequestMapping("/triangles")
@Validated
public class TriangleController {

    // Эндпоинт для определения типа треугольника
    @PostMapping("/determineType")
    public ResponseEntity<?> determineTriangleType(@Valid @RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = createFigureFromDTO(figureDTO);

        if (!(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().body("Не допустимый треугольник..");
        }

        Triangle triangle = (Triangle) figure;
        String type = triangle.determineTriangleType();
        return ResponseEntity.ok(type);
    }

    // Эндпоинт для расчета площади описанной окружности треугольника
    @PostMapping("/circumscribedCircleArea")
    public ResponseEntity<?> calculateCircumscribedCircleArea(@Valid @RequestBody FigureDTO figureDTO) {
        // Используем приватный метод для создания фигуры
        GeometricFigure figure = createFigureFromDTO(figureDTO);

        if (!(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().body("Не допустимый треугольник.");
        }

        Triangle triangle = (Triangle) figure;
        double area = triangle.calculateCircumscribedCircleArea();
        return ResponseEntity.ok(area);
    }
    // Эндпоинт для расчета площади вписанной окружности треугольника
    @PostMapping("/calculateInscribedCircleArea")
    public ResponseEntity<?> calculateInscribedCircleArea(@Valid @RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = createFigureFromDTO(figureDTO);

        if (!(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().body("Не допустимый треугольник для расчёта площади вписанной окружности.");
        }

        Triangle triangle = (Triangle) figure;
        double inscribedCircleArea = triangle.calculateInscribedCircleArea();
        return ResponseEntity.ok(inscribedCircleArea);
    }

    // Эндпоинт для расчета длин медиан треугольника
    @PostMapping("/calculateMedians")
    public ResponseEntity<?> calculateMedians(@Valid @RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = createFigureFromDTO(figureDTO);

        if (!(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().body("Не допустимый треугольник.");
        }

        Triangle triangle = (Triangle) figure;
        double[] medians = triangle.calculateAllMedians();
        return ResponseEntity.ok(medians);
    }

    // Эндпоинт для расчета длин биссектрис треугольника
    @PostMapping("/calculateBisectors")
    public ResponseEntity<?> calculateBisectors(@Valid @RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = createFigureFromDTO(figureDTO);

        if (!(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().body("Не допустимый треугольник.");
        }

        Triangle triangle = (Triangle) figure;
        double[] bisectors = triangle.calculateAllBisectors();
        return ResponseEntity.ok(bisectors);
    }

    // Приватный метод для создания фигуры из DTO
    private GeometricFigure createFigureFromDTO(FigureDTO figureDTO) {
        return FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
    }

}
