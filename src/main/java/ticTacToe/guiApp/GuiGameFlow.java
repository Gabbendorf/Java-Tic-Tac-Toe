package ticTacToe.guiApp;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;

public class GuiGameFlow {
    private final Grid grid;
    private final Lines lines;

    public GuiGameFlow(Grid grid, Lines lines) {

        this.grid = grid;
        this.lines = lines;
    }

    public Mark move(String position) {
        Mark currentMark = grid.nextMark();
        grid.addMark(currentMark, position);
        return currentMark;
    }

    public boolean isNotGameOver() {
        return !grid.isFinishedGame(lines);
    }
}
