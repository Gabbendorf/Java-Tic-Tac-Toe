package ticTacToe.game;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.player.Player;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;
import java.util.HashMap;
import java.util.Map;

import static ticTacToe.game.Mark.*;
import static ticTacToe.game.Mark.NOUGHT;

public class GameFlow {

    private final Ui ui;
    private final Grid grid;
    private final Rows rows;
    private final PlayersFactory playersFactory;
    private Player firstPlayer;
    private Player secondPlayer;

    public GameFlow(Ui ui, Grid grid, Rows rows) {
        this.ui = ui;
        this.grid = grid;
        this.rows = rows;
        this.playersFactory = new PlayersFactory();
        ui.welcomePlayer();
    }

    public void runGame() {
        firstPlayer = playersFactory.firstPlayer(ui);
        String currentMark = ui.askForStarter();
        secondPlayer = playersFactory.secondPlayer(firstPlayer.getMark());
        gameFlow(currentMark);
    }

    private void gameFlow(String currentMark) {
        while (!grid.allOccupiedCells()) {
            String positionChosen = currentPlayer(currentMark).makeMove(ui, grid, rows);
            grid.addMark(currentMark, positionChosen);
            if (rows.isWinning(grid.getSize())) {
                ui.declareWinner(rows.winningMark(grid.getSize()));
            } else {
                currentMark = switchPlayerMark(currentMark);
                gameFlow(currentMark);
            }
            break;
        }
        ui.declareDraw();
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

}
