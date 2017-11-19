package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.game.Rules;
import ticTacToe.ui.Ui;

public class HumanPlayer implements Player {

    private final String mark;

    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    public String makeMove(Ui ui, Grid grid, Rules rules) {
        ui.promptForPosition(mark, rules, grid);
        String positionChosen = ui.validPosition(grid, mark, rules);
        ui.confirmMove(mark, positionChosen);
        return positionChosen;
    }

    public String getMark() {
        return mark;
    }
}
