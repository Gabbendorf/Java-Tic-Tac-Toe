package ticTacToe;

import ticTacToe.game.GameFlow;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;

public class GameRunner {

    public static void main(String[] args) {
        Ui ui = new Ui(System.out, System.in);
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        PlayersFactory playersFactory = new PlayersFactory();
        GameFlow gameFlow = new GameFlow(ui, grid, lines, playersFactory);

        gameFlow.runGame();
    }
}
