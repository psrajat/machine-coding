package com.example.machinecoding.splitwise;

import com.example.machinecoding.splitwise.models.User;
import com.example.machinecoding.splitwise.services.ExpenseFactory;
import com.example.machinecoding.splitwise.services.ExpenseManager;
import com.example.machinecoding.splitwise.services.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {

        UserService userService = new UserService();
        ExpenseFactory expenseFactory = new ExpenseFactory(userService);

        ExpenseManager expenseManager = new ExpenseManager(expenseFactory, userService);
        expenseManager.addUser(new User("u1", "User1", "test@gmail.com", "12345678"));
        expenseManager.addUser(new User("u2", "User2", "test@gmail.com", "12345678"));
        expenseManager.addUser(new User("u3", "User3", "test@gmail.com", "12345678"));
        expenseManager.addUser(new User("u4", "User4", "test@gmail.com", "12345678"));


        File file = new File("src/main/resources/splitwise-input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //System.out.println("line: " + line);
            if (line.matches("^SHOW$")) {
                expenseManager.showBalances();
            } else if (line.matches("^SHOW u[1-9][0-9]*$")) {
                String[] tokens = line.split(" ");
                expenseManager.showBalance(tokens[1]);
            } else if (line.startsWith("EXPENSE")) {
                expenseManager.addExpense(line);
            } else {
                throw new IllegalArgumentException("Bad file");
            }
        }

    }

}
