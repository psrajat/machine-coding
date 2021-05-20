package com.example.machinecoding.splitwise.services;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.expense.*;
import com.example.machinecoding.splitwise.models.split.EqualSplit;
import com.example.machinecoding.splitwise.models.split.ExactSplit;
import com.example.machinecoding.splitwise.models.split.PercentSplit;
import com.example.machinecoding.splitwise.models.split.Split;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseFactory {

    private UserService userService;

    public ExpenseFactory(UserService userService) {
        this.userService = userService;
    }

    public Expense createExpense(String expenseStr) {

        String[] expenseStrTokens = expenseStr.split(" ");

        String paidBy = expenseStrTokens[1];
        User paidByUser = userService.findUserById(paidBy);
        double amount = Double.parseDouble(expenseStrTokens[2]);

        int paidForUsers = Integer.parseInt(expenseStrTokens[3]);
        ExpenseType expenseType = ExpenseType.valueOf(expenseStrTokens[4 + paidForUsers]);

        switch (expenseType) {
            case EQUAL:
                List<EqualSplit> equalSplits = new ArrayList<>();
                for (int i=1;i<=paidForUsers;i++) {
                    String userId = expenseStrTokens[3 + i];
                    User user = userService.findUserById(userId);
                    equalSplits.add(new EqualSplit(user, amount/paidForUsers));
                }
                return new EqualExpense(amount, paidByUser, new Date(), equalSplits);
            case EXACT:
                List<ExactSplit> exactSplits = new ArrayList<>();
                for (int i=1;i<=paidForUsers;i++) {
                    String userId = expenseStrTokens[3 + i];
                    User user = userService.findUserById(userId);
                    double userAmount = Double.parseDouble(expenseStrTokens[3 + paidForUsers + i + 1]);
                    exactSplits.add(new ExactSplit(user, userAmount));
                }
                return new ExactExpense(amount, paidByUser, new Date(), exactSplits);
            case PERCENT:
                List<PercentSplit> percentSplits = new ArrayList<>();
                for (int i=1;i<=paidForUsers;i++) {
                    String userId = expenseStrTokens[3 + i];
                    User user = userService.findUserById(userId);
                    double percent = Double.parseDouble(expenseStrTokens[3 + paidForUsers + i + 1]);
                    PercentSplit percentSplit = new PercentSplit(user, percent);
                    percentSplit.setAmount(amount * percent / 100);
                    percentSplits.add(percentSplit);
                }
                return new PercentExpense(amount, paidByUser, new Date(), percentSplits);
            default:
                throw new IllegalArgumentException("Invalid enum");
        }
    }

}
