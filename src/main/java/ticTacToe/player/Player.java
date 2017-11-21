package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.game.Lines;
import ticTacToe.ui.Ui;

public interface Player {
    String makeMove(Ui ui, Grid grid, Lines lines);

    String getMark();
}


