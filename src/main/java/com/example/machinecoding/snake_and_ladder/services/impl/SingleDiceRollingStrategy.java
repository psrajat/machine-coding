package com.example.machinecoding.snake_and_ladder.services.impl;

import com.example.machinecoding.snake_and_ladder.services.RollingStrategy;

import java.util.Random;

public class SingleDiceRollingStrategy implements RollingStrategy {

    private Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
