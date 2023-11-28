package com.example.traineejava.services;

import com.example.traineejava.models.Property;
import com.example.traineejava.repo.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public List<Property> getAllProperties(String name, long f, long s, Integer page, Integer size, String sortField, String sortOrder) {
        if (s == 0) s = Long.MAX_VALUE;
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortField == null) sortField = "id";
        if (sortOrder == null) sortOrder = "ASC";

        // Создание объекта Pageable для пагинации и сортировки
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Получение результата с применением пагинации и сортировки
        Page<Property> resultPage = propertyRepository.findByDeletedFalseAndPriceBetween(f, s, pageable);
        List<Property> result = resultPage.getContent();



        if (!name.equals("null") ) {
            if (!name.equals("")) {
                List<Property> propertiesName = propertyRepository.findByTitleContainingIgnoreCase(name);
                List<Property> result_copy = new ArrayList<>();

                for (Property property1 : propertiesName ) {
                    if(result.contains(property1)){
                        result_copy.add(property1);
                    }
                }
                result = result_copy;
            }
        }
        return result;
    }

    public Property getPropertyById(long id) {
        return propertyRepository.findByIdAndDeletedFalse(id);
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
