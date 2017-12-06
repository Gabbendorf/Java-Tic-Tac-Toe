package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

public interface Player {
    String makeMove(Ui ui, Grid grid);

    Mark getMark();
}


