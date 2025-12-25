package com.example.demo.service;

import com.example.demo.model.Category;
import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}