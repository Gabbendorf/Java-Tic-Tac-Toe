package ticTacToe;

import ticTacToe.game.GameFlow;
import ticTacToe.grid.Grid;
import ticTacToe.game.Lines;
import ticTacToe.ui.Ui;

public class GameRunner {

    public static void main(String[] args) {
        Ui ui = new Ui(System.out, System.in);
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        GameFlow gameFlow = new GameFlow(ui, grid, lines);

        gameFlow.runGame();
    }
}
