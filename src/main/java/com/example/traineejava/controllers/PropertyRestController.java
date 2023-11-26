package com.example.traineejava.controllers;

import com.example.traineejava.models.Property;
import com.example.traineejava.services.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PropertyRestController {

    private final PropertyService propertyService;

    public PropertyRestController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/get-properties")
    public List<Property> getProperties() {
        return propertyService.getAllProperties();
    }
}
