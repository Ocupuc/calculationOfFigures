package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.models.shapes.Parallelogram;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class ParallelogramController {

    @PostMapping("/parallelogram")
    public ResponseEntity<?> calculateParallelogramParameters(@RequestBody Map<String, Double> request) {
        try {
            double sideA = request.get("sideA");
            double sideB = request.get("sideB");
            double height = request.get("height"); // Высота, опущенная на сторону A
            Parallelogram parallelogram = new Parallelogram(sideA, sideB, height);

            // Создание объекта для ответа
            Map<String, String> response = new HashMap<>();
            response.put("area", String.format("%.2f", parallelogram.calculateArea()));
            response.put("perimeter", String.format("%.2f", parallelogram.calculatePerimeter()));
            response.put("description", "Параллелограмм — четырехугольник, противоположные стороны которого попарно параллельны и равны.");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

