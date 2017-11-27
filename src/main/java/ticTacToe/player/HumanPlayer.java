package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.game.Lines;
import ticTacToe.ui.Ui;

public class HumanPlayer implements Player {

    private final String mark;

    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        ui.promptForPosition(mark);
        return ui.validPosition(grid, mark, lines);
    }

    public String getMark() {
        return mark;
    }
}
