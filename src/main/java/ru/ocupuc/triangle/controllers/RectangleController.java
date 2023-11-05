package ru.ocupuc.triangle.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.shapes.Rectangle;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class RectangleController {

    @PostMapping("/rectangle")
    public ResponseEntity<?> calculateRectangleParameters(@RequestBody Map<String, Double> request) {
        try {
            double sideA = request.get("sideA");
            double sideB = request.get("sideB");
            Rectangle rectangle = FigureFactory.createRectangle(sideA, sideB);

            double area = rectangle.calculateArea();
            double perimeter = 2 * (sideA + sideB);

            Map<String, String> response = new HashMap<>();
            response.put("area", String.format("%.2f", area));
            response.put("perimeter", String.format("%.2f", perimeter));
            response.put("description", "Прямоугольник — это четырёхугольник у которого все углы прямые.");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
