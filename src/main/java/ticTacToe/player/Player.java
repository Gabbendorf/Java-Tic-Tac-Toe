package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.ui.Ui;

public interface Player {
    String makeMove(Ui ui, Grid grid, Rows rows);

    String getMark();
}


