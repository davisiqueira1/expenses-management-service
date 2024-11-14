package com.davi.expensemanagementservice.dtos;

import com.davi.expensemanagementservice.enums.UserRole;
import com.davi.expensemanagementservice.model.Expense;

import java.util.List;

public record SignUpDTO(
        String username,
        String password,
        String name,
        String email,
        UserRole role,
        List<Expense> expenses
) {
}
