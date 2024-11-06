package com.davi.expensemanagementservice.service;

import com.davi.expensemanagementservice.exception.CategoryNotFoundException;
import com.davi.expensemanagementservice.model.Category;
import com.davi.expensemanagementservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category) {
        if (category.getExpenses() == null) category.setExpenses(new ArrayList<>());
        return categoryRepository.save(category);
    }

    public Category read(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with id :: " + id + " not found");
        }
        return category.get();
    }

    public Category update(Long id, Category category) {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new CategoryNotFoundException("Category with id :: " + id + " not found");
        }
        Category updatedCategory = opt.get();
        if (category.getName() != null) updatedCategory.setName(category.getName());
        if (category.getExpenses() != null) updatedCategory.setExpenses(category.getExpenses());

        return categoryRepository.save(updatedCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
