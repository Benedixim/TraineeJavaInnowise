package com.example.traineejava.controllers;

import com.example.traineejava.models.Property;
import com.example.traineejava.models.User;
import com.example.traineejava.services.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;



@Controller
@RequestMapping(value = "/properties")
public class PropertyController {

    private final WebClient webClient;

    private final UserService userService;

    public PropertyController(WebClient webClient, UserService userService) {
        this.webClient = webClient;
        this.userService = userService;
    }

    @GetMapping
    public String propertyMain(@RequestParam(required = false) String name, @RequestParam(required = false) Long f, @RequestParam(required = false) Long s,
                               @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false) String sortField, @RequestParam(required = false) String sortOrder, Model model) {
        // Получение списка свойств через REST-запрос

        if (page == null) page = 0;
        if (size == null) size = 5;
        if (sortField == null) sortField = "title";
        if (sortOrder == null) sortOrder = "ASC";
        if (f == null) {
            f = 0L; // Инициализация переменной f значением 0 типа long
        }
        if(s == null) {
            s = 0L; // Инициализация переменной s значением 0 типа long
        }

        List<Property> properties = webClient.get()
                .uri("/get-properties?name=" + name + "&f=" + f.longValue() + "&s=" + s.longValue() + "&page=" + page + "&size=" + size + "&sortField=" + sortField + "&sortOrder=" + sortOrder)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Property>>() {})
                .block();
        model.addAttribute("properties", properties);
        return "property/property-main";
    }

    @GetMapping("/{id}")
    public String propertyDetail(@PathVariable(value = "id") long id, Model model) {
        // Получение списка свойств через REST-запрос
        Property property = webClient.get()
                .uri("/get-property/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Property>() {})
                .block();
        model.addAttribute("property", property);
        return "property/property-details";
    }

    @PostMapping("/{id}")
    public String propertyEdit(@PathVariable(value = "id") long id, @RequestParam String title,
                               @RequestParam String address, @RequestParam long price,
                               @RequestParam String type, @RequestParam String linkPhoto,
                               @RequestParam String description) {

        String url = "/update-property/" + id;
        WebClient.RequestBodySpec request = webClient.post().uri(url);
        WebClient.ResponseSpec response = request.bodyValue(new Property(id, title, address, price, type, linkPhoto, description, new Date())).retrieve();

        ResponseEntity<String> responseEntity = response.toEntity(String.class)
                .onErrorResume(error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage()));
                })
                .block();

        // Проверка на ошибку
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/properties/{id}";
        } else {
            // Получение сообщения об ошибке
            String errorMessage = responseEntity != null ? responseEntity.getBody() : "Unknown error";

            // Перенаправление на страницу ошибки
            return "redirect:/error-page?errorMessage=" + errorMessage;
        }
    }

    @PostMapping("/{id}/remove")
    public String propertyEdit(@PathVariable(value = "id") long id) {

        Property property = webClient.post()
                .uri("/remove-property/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Property>() {})
                .block();

        return "redirect:/properties";
    }

    @GetMapping("/add")
    public String propertyAdd(Model model) {
        return "property/property-add";
    }



    @PostMapping("/add")
    public String propertyAdd(@RequestParam String title, @RequestParam String address,
                               @RequestParam long price, @RequestParam String type,
                               @RequestParam String linkPhoto, @RequestParam String description) {



        String url = "/add-property";
        WebClient.RequestBodySpec request = webClient.post().uri(url);
        WebClient.ResponseSpec response = request.bodyValue(new Property(title, address, price, type, linkPhoto, description, new Date())).retrieve();
        ResponseEntity<String> responseEntity = response.toEntity(String.class)
                .onErrorResume(error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.getMessage()));
                })
                .block();

        // Проверка на ошибку
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/properties";
        } else {
            // Получение сообщения об ошибке
            String errorMessage = responseEntity != null ? responseEntity.getBody() : "Unknown error";

            // Перенаправление на страницу ошибки
            return "redirect:/error-page?errorMessage=" + errorMessage;
        }
    }
}

