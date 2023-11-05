package ru.ocupuc.triangle.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.shapes.Circle;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class CircleController {

    @PostMapping("/circle")
    public ResponseEntity<?> calculateCircleParameters(@RequestBody Map<String, Double> request) {
        try {
            double radius = request.get("radius");
            Circle circle = FigureFactory.createCircle(radius);

            // Создание объекта для ответа
            Map<String, String> response = new HashMap<>();
            response.put("perimeter", String.format("%.2f", circle.calculatePerimeter()));
            response.put("area", String.format("%.2f", circle.calculateArea()));
            response.put("description", "Круг — геометрическое место точек на плоскости, находящихся на одинаковом расстоянии от данной точки, называемой центром круга.");

            return ResponseEntity.ok(response); // Возвращает объект ответа в формате JSON
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

