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
        ui.promptForPosition(mark, lines, grid);
        String positionChosen = ui.validPosition(grid, mark, lines);
        ui.confirmMove(mark, positionChosen);
        return positionChosen;
    }

    public String getMark() {
        return mark;
    }
}
