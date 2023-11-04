package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.HeightCalculable;
import ru.ocupuc.triangle.models.Triangle;
import ru.ocupuc.triangle.dto.FigureDTO;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/figures")
public class FigureController {

    private static final List<String> availableFigures = Arrays.asList("triangle", "parallelogram");

    // Получение списка доступных фигур для вычислений
    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableFigures() {
        return ResponseEntity.ok(availableFigures);
    }

    // Вычисление площади фигуры
    @PostMapping("/calculateArea")
    public ResponseEntity<Double> calculateArea(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        double area = figure.calculateArea();
        return ResponseEntity.ok(area);
    }

    // Вычисление периметра фигуры
    @PostMapping("/calculatePerimeter")
    public ResponseEntity<Double> calculatePerimeter(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        double perimeter = figure.calculatePerimeter();
        return ResponseEntity.ok(perimeter);
    }

    // Определение типа фигуры
    @PostMapping("/identifyFigure")
    public ResponseEntity<String> identifyFigure(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        String figureName = figure.identifyFigure();
        return ResponseEntity.ok(figureName);
    }

    // Вычисление высот фигуры
    @PostMapping("/calculateHeights")
    public ResponseEntity<double[]> calculateHeights(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null || !(figure instanceof HeightCalculable)) {
            return ResponseEntity.badRequest().build();
        }
        HeightCalculable heightCalculableFigure = (HeightCalculable) figure;
        double[] heights = heightCalculableFigure.calculateHeights();
        return ResponseEntity.ok(heights);
    }

    // Определение типа треугольника
    @PostMapping("/determineTriangleType")
    public ResponseEntity<String> determineTriangleType(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null || !(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().build();
        }
        Triangle triangle = (Triangle) figure;
        String type = triangle.determineTriangleType();
        return ResponseEntity.ok(type);
    }

    // Добавьте здесь другие методы для треугольников, если они понадобятся
}
