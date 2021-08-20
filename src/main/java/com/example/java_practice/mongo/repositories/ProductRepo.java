package com.example.java_practice.mongo.repositories;

import com.example.java_practice.mongo.model.Product;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@EntityScan("com.example.java_practice.mongo.model")*/
@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByName(String name);

    Page<Product> findAllByName(String name, Pageable pageable);
}
