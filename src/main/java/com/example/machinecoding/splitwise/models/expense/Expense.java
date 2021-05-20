package com.example.machinecoding.splitwise.models.expense;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.split.Split;

import java.util.Date;
import java.util.List;

public abstract class Expense<T extends Split> {

    private double amount;
    private User paidBy;
    private Date createdDate;
    private List<T> splits;

    public Expense(double amount, User paidBy, Date createdDate, List<T> splits) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.createdDate = createdDate;
        this.splits = splits;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<T> getSplits() {
        return splits;
    }

    public void setSplits(List<T> splits) {
        this.splits = splits;
    }

    public abstract boolean validate();

}
