package com.xeno.blog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Document
public class Category {
    @Id
    private String id;

    @NotBlank
    private String name;

}
