package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.game.Rules;
import ticTacToe.ui.Ui;

public interface Player {
    String makeMove(Ui ui, Grid grid, Rules rules);

    String getMark();
}


