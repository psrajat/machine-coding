package com.example.machinecoding.snake_and_ladder.models;

public class Snake implements Obstruction {

    private final int head;
    private final int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}
