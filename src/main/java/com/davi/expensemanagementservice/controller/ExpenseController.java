package com.davi.expensemanagementservice.controller;

import com.davi.expensemanagementservice.model.Expense;
import com.davi.expensemanagementservice.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        Expense newExpense = expenseService.create(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> read(@PathVariable Long id) {
        Expense expense = expenseService.read(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable Long id, @RequestBody Expense expense) {
        Expense updatedExpense = expenseService.update(id, expense);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
