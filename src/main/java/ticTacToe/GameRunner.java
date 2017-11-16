package ticTacToe;

import ticTacToe.game.GameFlow;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.ui.Ui;

public class GameRunner {

    public static void main(String[] args) {
        Ui ui = new Ui(System.out, System.in);
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        GameFlow gameFlow = new GameFlow(ui, grid, rows);

        gameFlow.runGame();
    }
}
