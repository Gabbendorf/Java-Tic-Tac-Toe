package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

public class HumanPlayer implements Player {

    private final String mark;

    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid) {
        return ui.validPosition(grid, mark);
    }

    public String getMark() {
        return mark;
    }
}
