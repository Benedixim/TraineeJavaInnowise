package com.example.traineejava.repo;

import com.example.traineejava.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Page<Property> findByDeletedFalseAndPriceBetween(long first, long second, Pageable pageable);

    List<Property> findByTitleContainingIgnoreCase(String name);



    Property findByIdAndDeletedFalse(long id);
}
