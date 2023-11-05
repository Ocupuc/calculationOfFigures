package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.figurs.Triangle;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class TriangleController {

    @PostMapping("/triangle")
    public ResponseEntity<?> calculateTriangleParameters(@RequestBody Map<String, Double> request) {
        try {
            double sideA = request.get("sideA");
            double sideB = request.get("sideB");
            double sideC = request.get("sideC");

            Triangle triangle = FigureFactory.createTriangle(sideA, sideB, sideC);

            Map<String, Object> response = new HashMap<>();
            response.put("area", triangle.calculateArea());
            response.put("perimeter", triangle.calculatePerimeter());
            response.put("definition", "Треугольник — это многоугольник с тремя вершинами и тремя сторонами.");
            response.put("heights", triangle.calculateHeights());
            response.put("medians", triangle.calculateAllMedians());
            response.put("bisectors", triangle.calculateAllBisectors());
            response.put("triangleType", triangle.determineTriangleType());
            response.put("inscribedCircleArea", triangle.calculateInscribedCircleArea());
            response.put("circumscribedCircleArea", triangle.calculateCircumscribedCircleArea());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

