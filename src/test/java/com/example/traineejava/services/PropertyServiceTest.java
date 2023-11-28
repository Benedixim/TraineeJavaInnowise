package com.example.traineejava.services;

import com.example.traineejava.models.Property;
import com.example.traineejava.repo.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    private PropertyService propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        propertyService = new PropertyService(propertyRepository);
    }



    @Test
    void getPropertyById_ExistingId_ReturnsProperty() {
        long id = 1;
        Property expectedProperty = new Property();
        when(propertyRepository.findByIdAndDeletedFalse(id)).thenReturn(expectedProperty);

        Property actualProperty = propertyService.getPropertyById(id);

        assertEquals(expectedProperty, actualProperty);
        verify(propertyRepository, times(1)).findByIdAndDeletedFalse(id);
    }

    @Test
    void propertyEdit_ExistingProperty_UpdatesProperty() {
        Property property = new Property();
        property.setId(1L);
        Property oldProperty = new Property();
        oldProperty.setId(1L);
        when(propertyRepository.findById(property.getId())).thenReturn(Optional.of(oldProperty));

        propertyService.propertyEdit(property);

        verify(propertyRepository, times(1)).findById(property.getId());
        verify(propertyRepository, times(1)).save(oldProperty);
        assertEquals(property.getTitle(), oldProperty.getTitle());
        assertEquals(property.getPrice(), oldProperty.getPrice());
        assertEquals(property.getDescription(), oldProperty.getDescription());
        assertEquals(property.getAddress(), oldProperty.getAddress());
        assertEquals(property.getType(), oldProperty.getType());
        assertEquals(property.getLinkPhoto(), oldProperty.getLinkPhoto());
    }

    @Test
    void propertyRemove_ExistingProperty_SetsDeletedFlag() {
        long id = 1;
        Property property = new Property();
        property.setId(id);
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        propertyService.propertyRemove(id);

        verify(propertyRepository, times(1)).findById(id);
        verify(propertyRepository, times(1)).save(property);
        assertEquals(true, property.isDeleted());
    }

    @Test
    void propertyAdd_NewProperty_SavesProperty() {
        Property property = new Property();

        propertyService.propertyAdd(property);

        verify(propertyRepository, times(1)).save(property);
    }
}