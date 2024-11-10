package com.davi.expensemanagementservice.exception;

public class ExpenseNotFoundException extends RuntimeException {
  public ExpenseNotFoundException(String message) {
    super(message);
  }
}
