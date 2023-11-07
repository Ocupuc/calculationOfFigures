package ru.ocupuc.triangle.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.shapes.Trapezoid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class TrapezoidController {

    @PostMapping("/trapezoid")
    public ResponseEntity<?> calculateTrapezoidParameters(@RequestBody Map<String, Double> request) {
        try {
            double sideB = request.get("sideB");
            double sideD = request.get("sideD");
            double height = request.get("height");
            Trapezoid trapezoid = FigureFactory.createTrapezoid(sideB, sideD, height);

            // Создание объекта для ответа
            Map<String, String> response = new HashMap<>();
            response.put("area", String.format("%.2f", trapezoid.calculateArea()));
            response.put("perimeter", String.format("%.2f", trapezoid.calculatePerimeter()));
            response.put("description", "Трапеция — четырехугольник с двумя параллельными и двумя не параллельными сторонами.");
            response.put("side", String.format("%.2f", trapezoid.calculateSide()));

            return ResponseEntity.ok(response); // Возвращает объект ответа в формате JSON
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
