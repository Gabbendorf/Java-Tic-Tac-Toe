package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.ui.Ui;

public class HumanPlayer implements Player {

    private final String mark;

    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid, Rows rows) {
        ui.promptForPosition(mark, rows, grid.getSize());
        String positionChosen = ui.validPosition(grid, mark, rows, grid.getSize());
        ui.confirmMove(mark, positionChosen);
        return positionChosen;
    }

    public String getMark() {
        return mark;
    }
}
