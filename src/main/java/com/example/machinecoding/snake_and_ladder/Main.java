package com.example.machinecoding.snake_and_ladder;

import com.example.machinecoding.snake_and_ladder.models.BoardGame;
import com.example.machinecoding.snake_and_ladder.models.Ladder;
import com.example.machinecoding.snake_and_ladder.models.Player;
import com.example.machinecoding.snake_and_ladder.models.Snake;
import com.example.machinecoding.snake_and_ladder.services.GameService;
import com.example.machinecoding.snake_and_ladder.services.RollingStrategy;
import com.example.machinecoding.snake_and_ladder.services.impl.SingleDiceRollingStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        BoardGame boardGame = new BoardGame(100);
        List<Player> players = new ArrayList<>();

        parseInput(boardGame, players);

        RollingStrategy rollingStrategy = new SingleDiceRollingStrategy();
        GameService gameService = new GameService(rollingStrategy, boardGame, players);
        gameService.playGame();

    }


    private static void parseInput(BoardGame boardGame, List<Player> players) throws FileNotFoundException {
        File file = new File("src/main/resources/input.txt");
        Scanner sc = new Scanner(file);
        int sl = Integer.parseInt(sc.nextLine());

        for (int i=0;i<sl;i++) {
            String[] line = sc.nextLine().split(" ");
            Snake snake = new Snake(
                    Integer.parseInt(line[0]),
                    Integer.parseInt(line[1])
            );
            boardGame.placeObstruction(snake);
        }

        int ld = Integer.parseInt(sc.nextLine());

        for (int i=0;i<ld;i++) {
            String[] line = sc.nextLine().split(" ");
            Ladder ladder = new Ladder(
                    Integer.parseInt(line[0]),
                    Integer.parseInt(line[1])
            );
            boardGame.placeObstruction(ladder);
        }

        int pl = Integer.parseInt(sc.nextLine());

        for (int i=0;i<pl;i++) {
            Player player = new Player(sc.nextLine());
            players.add(player);
        }
    }
}
