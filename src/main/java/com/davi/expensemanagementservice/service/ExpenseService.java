package com.davi.expensemanagementservice.service;

import com.davi.expensemanagementservice.exception.ExpenseNotFoundException;
import com.davi.expensemanagementservice.model.Expense;
import com.davi.expensemanagementservice.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public Expense create(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense read(Long id) {
        var opt = expenseRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ExpenseNotFoundException("Expense with id :: " + id + " not found");
        }

        return opt.get();
    }

    @Transactional
    public Expense update(Long id, Expense expense) {
        var opt = expenseRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ExpenseNotFoundException("Expense with id :: " + id + " not found");
        }

        Expense updatedExpense = opt.get();
        if (expense.getAmount() != null) updatedExpense.setAmount(expense.getAmount());
        if (expense.getDate() != null) updatedExpense.setDate(expense.getDate());
        if (expense.getCategory() != null) updatedExpense.setCategory(expense.getCategory());
        if (expense.getTitle() != null) updatedExpense.setTitle(expense.getTitle());
        return expenseRepository.save(updatedExpense);
    }

    @Transactional
    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }
}