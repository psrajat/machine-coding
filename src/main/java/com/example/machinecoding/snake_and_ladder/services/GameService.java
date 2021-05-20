package com.example.machinecoding.snake_and_ladder.services;

import com.example.machinecoding.snake_and_ladder.models.BoardGame;
import com.example.machinecoding.snake_and_ladder.models.Player;

import java.util.List;

public class GameService {

    private RollingStrategy rollingStrategy;

    private BoardGame boardGame;

    private List<Player> players;

    public GameService(RollingStrategy rollingStrategy, BoardGame boardGame, List<Player> players) {
        this.rollingStrategy = rollingStrategy;
        this.boardGame = boardGame;
        this.players = players;
    }

    public Player playGame() {
        while(true) {
            for (Player player: players) {
                int currPos = player.getCurrentPosition();

                int rollValue = rollingStrategy.roll();
                int nextPos = currPos + rollValue;
                if (nextPos <= boardGame.getSize()) {

                    System.out.println("Player " + player.getName() + " has rolled a " + rollValue + " moved from " + currPos + " to " + nextPos);
                    nextPos = boardGame.moveToPosition(nextPos);

                    if (boardGame.getSize() == nextPos) {
                        System.out.println(player.getName() + " wins the game");
                        return player;
                    }

                    player.setCurrentPosition(nextPos);
                }
            }
        }
    }

}
