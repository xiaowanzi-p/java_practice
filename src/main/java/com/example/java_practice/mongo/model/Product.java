package com.example.java_practice.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@Getter
@Setter
public class Product extends BaseModel {

    @Indexed(name = "index_name")
    private String name;

    private Integer price;
}
