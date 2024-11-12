package com.davi.expensemanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class ExpenseManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagementServiceApplication.class, args);
    }

}
