package com.example.machinecoding.splitwise.models.expense;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.split.PercentSplit;

import java.util.Date;
import java.util.List;

public class PercentExpense extends Expense<PercentSplit> {

    public PercentExpense(double amount, User paidBy, Date createdDate, List<PercentSplit> splits) {
        super(amount, paidBy, createdDate, splits);
    }

    @Override
    public boolean validate() {
        double checkPercent = super.getSplits().stream().mapToDouble(PercentSplit::getPercent).sum();
        if (checkPercent != 100)
            return false;
        return true;
    }
}
