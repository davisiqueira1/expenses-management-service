package com.davi.expensemanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true, of = {"expenseId", "userId"})
@Table()
@Entity(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ExpenseId.class)
public class Expense extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @Id
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private String title;
    private double amount;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
