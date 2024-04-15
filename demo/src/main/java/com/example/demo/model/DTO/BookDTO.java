package com.example.demo.model.DTO;

import com.example.demo.model.Category;

public record BookDTO(String name,
                      Category category,
                      Long authorId,
                      Integer availableCopies) {
}
