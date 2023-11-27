package com.example.traineejava.controllers;

import com.example.traineejava.models.Property;
import com.example.traineejava.errors.ValidationError;
import com.example.traineejava.services.PropertyService;
import com.example.traineejava.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import java.util.ArrayList;
import java.util.List;

@RestController
public class PropertyRestController {

    private final PropertyService propertyService;

    private final UserService userService;

    public PropertyRestController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping("/get-properties")
    public List<Property> getProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/get-property/{id}")
    public Property getProperty(@PathVariable(value = "id") long id) {
        return propertyService.getPropertyById(id);
    }





    @PostMapping("/update-property/{id}")
    public ResponseEntity<String> propertyEdit(@PathVariable(value = "id") long id, @Valid @RequestBody Property property, BindingResult bindingResult) {
        // Проверка на ошибки валидации
        if (bindingResult.hasErrors()) {
            // Создание списка объектов с информацией о невалидности запроса
            List<ValidationError> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ValidationError validationError = new ValidationError(error.getField(), error.getDefaultMessage());
                validationErrors.add(validationError);
                System.out.println("field: " + error.getField() + " message: " + error.getDefaultMessage());
            }
            String errors = "";
            for(ValidationError error : validationErrors) {
                errors = errors + error.getField() + ": " + error.getMessage() + "\n";
            }
            // Возврат ответа с информацией о невалидности запроса
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        propertyService.propertyEdit(property);
        // Возврат успешного ответа
        return ResponseEntity.ok("Property updated successfully");
    }

    @PostMapping("/remove-property/{id}")
    public void propertyRemove(@PathVariable(value = "id") long id) {
        propertyService.propertyRemove(id);
    }

    @PostMapping("/add-property")
    public ResponseEntity<String> propertyAdd(@Valid @RequestBody Property property, BindingResult bindingResult) {
        // Проверка на ошибки валидации
        property.setUser(userService.getUserByEmail(userService.getCurrentUser()));

        if (bindingResult.hasErrors()) {
            // Создание списка объектов с информацией о невалидности запроса
            List<ValidationError> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ValidationError validationError = new ValidationError(error.getField(), error.getDefaultMessage());
                validationErrors.add(validationError);
                System.out.println("field: " + error.getField() + " message: " + error.getDefaultMessage());
            }
            String errors = "";
            for(ValidationError error : validationErrors) {
                errors = errors + error.getField() + ": " + error.getMessage() + "\n";
            }
            // Возврат ответа с информацией о невалидности запроса
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        propertyService.propertyAdd(property);
        return ResponseEntity.ok("Property added successfully");
    }
}
