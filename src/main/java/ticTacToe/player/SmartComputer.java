package ticTacToe.player;

import ticTacToe.game.Lines;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

public class SmartComputer implements Player {

    private final String mark;

    public SmartComputer(String mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        return null;
    }

    public String getMark() {
        return mark;
    }
}
