package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.ui.Ui;

public class HumanPlayer implements Player {

    private final Mark mark;

    public HumanPlayer(Mark mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        ui.promptForPosition(mark);
        return ui.validPosition(grid, mark, lines);
    }

    public Mark getMark() {
        return mark;
    }
}
