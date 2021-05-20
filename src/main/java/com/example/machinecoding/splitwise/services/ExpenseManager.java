package com.example.machinecoding.splitwise.services;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.models.expense.Expense;
import com.example.machinecoding.splitwise.models.split.Split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private List<Expense> expenses;
    private Map<String, Map<String, Double>> balanceSheet;

    private ExpenseFactory expenseFactory;
    private UserService userService;

    public ExpenseManager(ExpenseFactory expenseFactory, UserService userService) {
        this.expenseFactory = expenseFactory;
        this.userService = userService;
        this.expenses = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }

    public void addUser(User user) {
        userService.addUser(user);
        balanceSheet.put(user.getUserId(), new HashMap<>());
    }

    public <S extends Split> void addExpense(String expenseString) {
        Expense<S> expense = expenseFactory.createExpense(expenseString);
        expenses.add(expense);
        String paidBy = expense.getPaidBy().getUserId();

        for (S split: expense.getSplits()) {
            String paidTo = split.getUser().getUserId();

            // `paidTo` owes `paidBy` split.getAmount()
            Map<String, Double> balances = balanceSheet.get(paidBy);
            balances.put(paidTo, balances.getOrDefault(paidTo, 0.0) + split.getAmount());

            // `paidBy` owes `paidTo` split.getAmount()
            // Since `paidTo` paid split.getAmount() to `paidBy`! Deduct this much from `paidBy`'s balance sheet
            balances = balanceSheet.get(paidTo);
            balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) - split.getAmount());
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) { // do not print double side
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userService.findUserById(user1).getName();
        String user2Name = userService.findUserById(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

}
