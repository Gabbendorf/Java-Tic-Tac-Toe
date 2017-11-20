package ticTacToe.game;

import ticTacToe.grid.Grid;
import ticTacToe.player.Player;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;
import java.util.HashMap;
import java.util.Map;

import static ticTacToe.game.Mark.*;

public class GameFlow {

    private final Ui ui;
    private final Lines lines;
    private Grid grid;
    private final PlayersFactory playersFactory;
    private Player firstPlayer;
    private Player secondPlayer;

    public GameFlow(Ui ui, Grid grid, Lines lines) {
        this.ui = ui;
        this.grid = grid;
        this.lines = lines;
        this.playersFactory = new PlayersFactory();
        ui.welcomePlayer();
    }

    public void runGame() {
        setUpPlayers();
        gameFlow();
        reportFinalResult();
        startNewGameOrQuit();
    }

    private void setUpPlayers() {
        String opponentOptionNumber = ui.chooseOpponent();
        firstPlayer = playersFactory.firstPlayer(ui);
        secondPlayer = playersFactory.secondPlayer(opponentOptionNumber, firstPlayer.getMark());
    }

    private void gameFlow() {
        String currentMark = ui.askForStarter();
        while (isOnGoingGame()) {
            String positionChosen = currentPlayer(currentMark).makeMove(ui, grid, lines);
            grid.addMark(currentMark, positionChosen);
            currentMark = switchPlayerMark(currentMark);
        }
    }

    private boolean isOnGoingGame() {
        return !grid.allOccupiedCells() && !lines.isWinning(grid);
    }

    private Player currentPlayer(String startingMark) {
        return players().get(startingMark);
    }

    private Map<String, Player> players() {
        Map<String, Player> players = new HashMap<>();
        players.put(firstPlayer.getMark(), firstPlayer);
        players.put(secondPlayer.getMark(), secondPlayer);
        return players;
    }

    private void reportFinalResult() {
        if (lines.isWinning(grid)) {
            ui.declareWinner(lines.winningMark(grid), lines, grid);
        }
        ui.declareDraw(lines, grid);
    }

    private void startNewGameOrQuit() {
        if (ui.askToPlayAgain().equals("y")) {
            grid.setCellsToEmpty();
            runGame();
        }
        ui.sayBye();
    }
}
