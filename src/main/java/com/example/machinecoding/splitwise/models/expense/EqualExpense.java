package com.example.machinecoding.splitwise.models.expense;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.split.EqualSplit;

import java.util.Date;
import java.util.List;

public class EqualExpense extends Expense<EqualSplit> {

    public EqualExpense(double amount, User paidBy, Date createdDate, List<EqualSplit> splits) {
        super(amount, paidBy, createdDate, splits);
    }

    @Override
    public boolean validate() {
        return true;
    }
}
