package org.example.api.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductCreateRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String description;

    public ProductCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
