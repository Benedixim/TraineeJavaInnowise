package com.example.traineejava.services;

import com.example.traineejava.models.User;
import com.example.traineejava.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Установка мок объектов в SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        userService = new UserService(userRepository);
    }

    @Test
    void getUserByEmail_ValidEmail_ReturnsUser() {
        String email = "test@example.com";
        User expectedUser = new User();
        expectedUser.setEmail(email);

        when(userRepository.findUserByEmail(email)).thenReturn(expectedUser);

        User actualUser = userService.getUserByEmail(email);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findUserByEmail(email);
    }

    @Test
    void createUser_ValidUser_ReturnsNewUser() {
        User user = new User();
        User expectedUser = new User();

        when(userRepository.save(user)).thenReturn(expectedUser);

        User actualUser = userService.createUser(user);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).flush();
    }

    @Test
    void getCurrentUser_AuthenticatedUser_ReturnsUsername() {
        String username = "testUser";
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password("password") // Установка непустого значения для пароля
                .authorities(new ArrayList<>()) // Установка непустого списка прав доступа
                .build();

        when(authentication.getPrincipal()).thenReturn(userDetails);

        String actualUsername = userService.getCurrentUser();

        assertEquals(username, actualUsername);
    }

    @Test
    void getCurrentUser_UnauthenticatedUser_ReturnsEmptyString() {
        when(authentication.getPrincipal()).thenReturn("anonymousUser");

        String actualUsername = userService.getCurrentUser();

        assertEquals("", actualUsername);
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }
}