package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.GeometricFigure;
import ru.ocupuc.triangle.models.HeightCalculable;
import ru.ocupuc.triangle.dto.FigureDTO;
import javax.validation.Valid;

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
    public ResponseEntity<Double> calculateArea(@Valid @RequestBody FigureDTO figureDTO) {
        try {
            GeometricFigure figure = getFigureFromDTO(figureDTO);
            double area = figure.calculateArea();
            return ResponseEntity.ok(area);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Вычисление периметра фигуры
    @PostMapping("/calculatePerimeter")
    public ResponseEntity<Double> calculatePerimeter(@Valid @RequestBody FigureDTO figureDTO) {
        try {
            GeometricFigure figure = getFigureFromDTO(figureDTO);
            double perimeter = figure.calculatePerimeter();
            return ResponseEntity.ok(perimeter);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Определение типа фигуры
    @PostMapping("/identifyFigure")
    public ResponseEntity<String> identifyFigure(@Valid @RequestBody FigureDTO figureDTO) {
        try {
            GeometricFigure figure = getFigureFromDTO(figureDTO);
            String figureName = figure.identifyFigure();
            return ResponseEntity.ok(figureName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Вычисление высот фигуры
    @PostMapping("/calculateHeights")
    public ResponseEntity<double[]> calculateHeights(@Valid @RequestBody FigureDTO figureDTO) {
        try {
            GeometricFigure figure = getFigureFromDTO(figureDTO);
            if (!(figure instanceof HeightCalculable)) {
                throw new IllegalArgumentException();
            }
            HeightCalculable heightCalculableFigure = (HeightCalculable) figure;
            double[] heights = heightCalculableFigure.calculateHeights();
            return ResponseEntity.ok(heights);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Приватный метод для создания фигуры из DTO
    private GeometricFigure getFigureFromDTO(FigureDTO figureDTO) {
        GeometricFigure figure = FigureFactory.createFigure(figureDTO.getType(), figureDTO.getParameters());
        if (figure == null) {
            throw new IllegalArgumentException("Фигура не может быть создана");
        }
        return figure;
    }

}
