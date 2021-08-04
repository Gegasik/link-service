package com.service.userservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Document(collection = "links")
public class Link {

    @Id
    private String id;

    private String link;

    private String description;

    private Collection<String> tags;

    public Link(String link, String description, Collection<String> tags) {
        this.link = link;
        this.description = description;
        this.tags = tags;
    }
}
