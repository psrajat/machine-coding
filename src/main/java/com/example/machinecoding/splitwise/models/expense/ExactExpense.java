package com.example.machinecoding.splitwise.models.expense;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.split.ExactSplit;

import java.util.Date;
import java.util.List;

public class ExactExpense extends Expense<ExactSplit> {

    public ExactExpense(double amount, User paidBy, Date createdDate, List<ExactSplit> splits) {
        super(amount, paidBy, createdDate, splits);
    }

    @Override
    public boolean validate() {
        double checkAmount = super.getSplits().stream().mapToDouble(ExactSplit::getAmount).sum();
        if (checkAmount != super.getAmount())
            return false;
        return true;
    }

}
