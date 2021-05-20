package com.example.machinecoding.snake_and_ladder.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardGame {

    private final int size;
    Map<Integer, Optional<Obstruction>> boardMap;

    public BoardGame(int size) {
        this.size = size;
        this.boardMap = new HashMap<>();
        initialize();
    }

    public void initialize() {
        for (int i=1;i<=size;i++) {
            boardMap.put(i, Optional.empty());
        }
    }

    public void placeObstruction(Obstruction obs) {
        boardMap.put(obs.getHead(), Optional.of(obs));
    }

    public int moveToPosition(int pos) {

        while (boardMap.get(pos).isPresent()) {
            pos = boardMap.get(pos).get().getTail();
        }
        return pos;
    }

    public int getSize() {
        return size;
    }

}
