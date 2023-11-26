package com.example.traineejava.controllers;

import com.example.traineejava.models.Property;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.crypto.Data;
import java.util.List;

@Controller
@RequestMapping(value = "/properties")
public class PropertyController {

    private final WebClient webClient;

    public PropertyController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public String propertyMain(Model model) {
        // Получение списка свойств через REST-запрос
        List<Property> properties = webClient.get()
                .uri("/get-properties")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Property>>() {})
                .block();
        model.addAttribute("properties", properties);
        return "property/property-main";
    }
}

