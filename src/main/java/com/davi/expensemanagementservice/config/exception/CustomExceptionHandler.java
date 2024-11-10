package com.davi.expensemanagementservice.config.exception;

import com.davi.expensemanagementservice.exception.CategoryNotFoundException;
import com.davi.expensemanagementservice.exception.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private Map<String, String> response(String errorMessage) {
        var map = new HashMap<String, String>();
        map.put("error", errorMessage);
        return map;
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ResponseEntity<>(response(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleExpenseNotFoundException(ExpenseNotFoundException e) {
        return new ResponseEntity<>(response(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
