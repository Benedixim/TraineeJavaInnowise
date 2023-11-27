package com.example.traineejava.services;

import com.example.traineejava.models.Property;
import com.example.traineejava.repo.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findByDeletedFalse();
    }

    public Property getPropertyById(long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public void propertyEdit(Property property) {
        Property oldProperty = propertyRepository.findById(property.getId()).orElse(null);
        oldProperty.setPrice(property.getPrice());
        oldProperty.setTitle(property.getTitle());
        oldProperty.setDescription(property.getDescription());
        oldProperty.setAddress(property.getAddress());
        oldProperty.setType(property.getType());
        oldProperty.setLinkPhoto(property.getLinkPhoto());
        oldProperty.setDate(new Date());

        propertyRepository.save(oldProperty);
    }

    public void propertyRemove(long id) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            property.setDeleted(true);
            propertyRepository.save(property);
        }

    }

    public void propertyAdd(Property property) {
        propertyRepository.save(property);
    }
}
