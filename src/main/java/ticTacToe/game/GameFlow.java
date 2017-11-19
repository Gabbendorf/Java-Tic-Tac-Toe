package ticTacToe.game;

import ticTacToe.grid.Grid;
import ticTacToe.player.Player;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;
import java.util.HashMap;
import java.util.Map;

import static ticTacToe.game.Mark.*;
import static ticTacToe.game.Mark.NOUGHT;

public class GameFlow {

    private final Ui ui;
    private final Rules rules;
    private Grid grid;
    private final PlayersFactory playersFactory;
    private Player firstPlayer;
    private Player secondPlayer;

    public GameFlow(Ui ui, Grid grid, Rules rules) {
        this.ui = ui;
        this.grid = grid;
        this.rules = rules;
        this.playersFactory = new PlayersFactory();
        ui.welcomePlayer();
    }

    public void runGame() {
        String startingPlayerMark = setUpPlayers();
        gameFlow(startingPlayerMark);
        reportFinalResult(startingPlayerMark);
        startNewGameOrQuit();
    }

    private String setUpPlayers() {
        firstPlayer = playersFactory.firstPlayer(ui);
        String currentMark = ui.askForStarter();
        secondPlayer = playersFactory.secondPlayer(firstPlayer.getMark());
        return currentMark;
    }

    private void gameFlow(String currentMark) {
        while (!isGameEnded()) {
            String positionChosen = currentPlayer(currentMark).makeMove(ui, grid, rules);
            grid.addMark(currentMark, positionChosen);
            currentMark = switchPlayerMark(currentMark);
        }
    }

    private boolean isGameEnded() {
        return grid.allOccupiedCells() || rules.isWinning(grid);
    }

    private Player currentPlayer(String startingMark) {
        return players().get(startingMark);
    }

    private String switchPlayerMark(String currentMark) {
       if (currentMark.equals(CROSS.mark)) {
           return NOUGHT.mark;
       } else {
           return CROSS.mark;
       }
    }

    private Map<String, Player> players() {
        Map<String, Player> players = new HashMap<>();
        players.put(firstPlayer.getMark(), firstPlayer);
        players.put(secondPlayer.getMark(), secondPlayer);
        return players;
    }

    private void reportFinalResult(String currentMark) {
        if (rules.isWinning(grid)) {
            ui.declareWinner(currentMark, rules, grid);
        } else {
            ui.declareDraw(rules, grid);
        }
    }

    private void startNewGameOrQuit() {
        String answer = ui.askToPlayAgain();
        if (answer.equals("y")) {
            grid.setCellsToEmpty();
            runGame();
        } else {
            ui.sayBye();
        }
    }
}
