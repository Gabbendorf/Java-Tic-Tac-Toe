package ticTacToe.game;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.player.Player;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;
import java.util.HashMap;
import java.util.Map;

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
        Mark currentMark = ui.askForStarter();
        while (!grid.isFinishedGame(lines)) {
            String positionChosen = currentPlayer(currentMark).makeMove(ui, grid, lines);
            grid.addMark(currentMark, positionChosen);
            ui.confirmMove(currentMark, positionChosen);
            currentMark = currentMark.doSwitch();
        }
    }

    private Player currentPlayer(Mark startingMark) {
        return players().get(startingMark);
    }

    private Map<Mark, Player> players() {
        Map<Mark, Player> players = new HashMap<>();
        players.put(firstPlayer.getMark(), firstPlayer);
        players.put(secondPlayer.getMark(), secondPlayer);
        return players;
    }

    private void reportFinalResult() {
        if (lines.isWinning(grid)) {
            ui.declareWinner(lines.winningMark(grid), lines, grid);
        } else {
            ui.declareDraw(lines, grid);
        }
    }

    private void startNewGameOrQuit() {
        if (playAgain(ui.askToPlayAgain())) {
            grid.setCellsToEmpty();
            runGame();
        } else {
            ui.sayBye();
        }
    }

    private boolean playAgain(String userInput) {
        return userInput.equals("y");
    }
}
