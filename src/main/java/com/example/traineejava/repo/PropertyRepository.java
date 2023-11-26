package com.example.traineejava.repo;

import com.example.traineejava.models.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
}
