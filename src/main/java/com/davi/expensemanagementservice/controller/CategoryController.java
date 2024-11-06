package com.davi.expensemanagementservice.controller;

import com.davi.expensemanagementservice.model.Category;
import com.davi.expensemanagementservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category newCategory = categoryService.create(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> read(@PathVariable Long id) {
        Category category = categoryService.read(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
