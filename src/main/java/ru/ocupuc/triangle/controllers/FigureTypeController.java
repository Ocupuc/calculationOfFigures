package ru.ocupuc.triangle.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ocupuc.triangle.FigureType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FigureTypeController {

    @GetMapping("/api/figure-types")
    public List<String> getFigureTypes() {
        return Arrays.stream(FigureType.values())
                .map(FigureType::getName)
                .collect(Collectors.toList());
    }
}
