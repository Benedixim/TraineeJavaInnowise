package com.example.traineejava.controllers;

import com.example.traineejava.models.User;
import com.example.traineejava.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public MainController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/properties";
    }

    @GetMapping("/panel")
    public String panel( Model model) {
        String email = userService.getCurrentUser();

        User user = userService.getUserByEmail(email);
        if (user == null) {
            //throw new UsernameNotFoundException("No user found with email");
            return "redirect:/login";
        }

        if (user.getRole().equals("ADMIN")) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
        }

        model.addAttribute("user", user);
        return "main/panel";
    }

    @GetMapping("/error-page")
    public String errorPage(@RequestParam String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "main/error-page";
    }

    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "main/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {

        try {

            user.setRole("USER");

            User newUser = userService.createUser(user);
            if (newUser == null) {
                return "redirect:/register?error";
            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

            return "redirect:/";

        } catch (Exception e) {
            return "redirect:/register?error";
        }

    }

}
