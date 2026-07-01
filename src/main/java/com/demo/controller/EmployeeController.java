package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<String> employees =
            new ArrayList<>(Arrays.asList("Kandavel","John"));

    @GetMapping
    public List<String> getEmployees() {
        return employees;
    }

    @PostMapping
    public String addEmployee(@RequestParam String name) {
        employees.add(name);
        return "Added " + name;
    }

    @DeleteMapping("/{name}")
    public String deleteEmployee(@PathVariable String name) {
        employees.remove(name);
        return "Deleted " + name;
    }

    @GetMapping("/health")
    public String health() {
        return "Application is UP";
    }
}
