package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.triangle.FigureDTO;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.Triangle;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/figures")
public class FigureController {

    private static final List<String> availableFigures = Arrays.asList("triangle", "parallelogram");

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableFigures() {
        return ResponseEntity.ok(availableFigures);
    }

    @PostMapping("/calculateArea")
    public ResponseEntity<Double> calculateArea(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        double area = figure.calculateArea();
        return ResponseEntity.ok(area);
    }

    @PostMapping("/calculatePerimeter")
    public ResponseEntity<Double> calculatePerimeter(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        double perimeter = figure.calculatePerimeter();
        return ResponseEntity.ok(perimeter);
    }

    @PostMapping("/identifyFigure")
    public ResponseEntity<String> identifyFigure(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            return ResponseEntity.badRequest().build();
        }
        String figureName = figure.identifyFigure();
        return ResponseEntity.ok(figureName);
    }

    @PostMapping("/calculateHeight")
    public ResponseEntity<Double> calculateHeight(@RequestBody FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null || !(figure instanceof Triangle)) {
            return ResponseEntity.badRequest().build();
        }
        Triangle triangle = (Triangle) figure;
        double height = triangle.calculateHeight();
        return ResponseEntity.ok(height);
    }

    // Другие методы для треугольников и других фигур по мере добавления...
}


