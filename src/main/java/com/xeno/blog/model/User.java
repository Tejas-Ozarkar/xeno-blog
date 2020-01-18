package com.xeno.blog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
