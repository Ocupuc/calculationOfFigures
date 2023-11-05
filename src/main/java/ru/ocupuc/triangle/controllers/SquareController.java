package ru.ocupuc.triangle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureFactory;
import ru.ocupuc.triangle.models.shapes.Square;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/figures")
public class SquareController {

    @PostMapping("/square")
    public ResponseEntity<?> calculateSquareParameters(@RequestBody Map<String, Double> request) {
        try {
            double side = request.get("side");
            Square square = FigureFactory.createSquare(side);

            // Создание объекта для ответа
            Map<String, String> response = new HashMap<>();
            response.put("area", String.format("%.2f", square.calculateArea()));
            response.put("perimeter", String.format("%.2f", square.calculatePerimeter()));
            response.put("description", "Квадрат — это правильный четырехугольник, у которого все стороны равны и все углы прямые.");
            response.put("inscribedCircleArea", String.format("%.2f", square.inscribedCircleArea()));
            response.put("circumscribedCircleArea", String.format("%.2f", square.circumscribedCircleArea()));

            return ResponseEntity.ok(response); // Возвращает объект ответа в формате JSON
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
