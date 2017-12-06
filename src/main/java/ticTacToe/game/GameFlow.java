package ticTacToe.game;

import ticTacToe.grid.Grid;
import ticTacToe.player.Player;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;
import java.util.HashMap;
import java.util.Map;

public class GameFlow {

    private final Ui ui;
    private Grid grid;
    private final PlayersFactory playersFactory;
    private Player firstPlayer;
    private Player secondPlayer;

    public GameFlow(Ui ui, PlayersFactory playersFactory) {
        this.ui = ui;
        ui.welcomePlayer();
        this.grid = createNewGrid();
        this.playersFactory = playersFactory;
    }

    public void runGame() {
        setUpGame();
        gameFlow();
        reportFinalResult();
        startNewGameOrQuit();
    }

    private void setUpGame() {
        ui.welcomePlayer();
        grid = createNewGrid();
        GameOption gameTypeOption = ui.chooseGameOption();
        firstPlayer = playersFactory.firstPlayer(ui, gameTypeOption);
        secondPlayer = playersFactory.secondPlayer(gameTypeOption, firstPlayer.getMark());
    }

    private void gameFlow() {
        Mark currentMark = ui.askForStarter();
        while (!grid.isFinishedGame()) {
            ui.printGrid(grid);
            String positionChosen = currentPlayer(currentMark).makeMove(ui, grid);
            grid.addMark(currentMark, positionChosen);
            ui.confirmMove(currentMark, positionChosen);
            currentMark = currentMark.swap();
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
        if (grid.isWinner()) {
            ui.declareWinner(grid.winningMark());
        } else {
            ui.declareDraw();
        }
        ui.printGrid(grid);
    }

    private void startNewGameOrQuit() {
        if (playAgain(ui.askToPlayAgain())) {
            runGame();
        } else {
            ui.sayBye();
        }
    }

    private boolean playAgain(String userInput) {
        return userInput.equals("y");
    }

    private Grid createNewGrid() {
        return new Grid(ui.chooseGridSize());
    }
}
