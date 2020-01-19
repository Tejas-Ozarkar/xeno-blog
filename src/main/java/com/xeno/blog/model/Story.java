package com.xeno.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document
@NoArgsConstructor
@Data
public class Story {
    @Id
    private String id;

    @NotBlank
    private String data;
    @NotBlank
    private String categoryId;
    @NotBlank
    private String userId;

    private List<String> likes;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @Field
    private boolean active=true;
}
