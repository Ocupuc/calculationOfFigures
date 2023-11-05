package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.models.shapes.Rhombus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class RhombusController {

    @PostMapping("/rhombus")
    public ResponseEntity<?> calculateRhombusParameters(@RequestBody Map<String, Double> request) {
        try {
            double sideA = request.get("side");
            double height = request.get("height");
            Rhombus rhombus = new Rhombus(sideA, height);

            // Создание объекта для ответа
            Map<String, String> response = new HashMap<>();
            response.put("area", String.format("%.2f", rhombus.calculateArea()));
            response.put("perimeter", String.format("%.2f", rhombus.calculatePerimeter()));
            response.put("description", "Ромб — это параллелограмм с равными сторонами.");

            return ResponseEntity.ok(response); // Возвращает объект ответа в формате JSON
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
