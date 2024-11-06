package com.davi.expensemanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseId implements Serializable {
    private Long expenseId;
    private Long userId;
}
