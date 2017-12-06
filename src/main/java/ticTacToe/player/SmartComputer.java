package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

public class SmartComputer implements Player {

    private final Mark mark;
    private final int speedInMills;
    private final ComputerMoveCalculator moveCalculator;

    public SmartComputer(Mark mark, int speedInMillis) {
        this.mark = mark;
        this.speedInMills = speedInMillis;
        this.moveCalculator = new ComputerMoveCalculator(mark);
    }

    public String makeMove(Ui ui, Grid grid) {
        slowDown();
        return bestMoveFor(grid);
    }

    public Mark getMark() {
        return mark;
    }

    private void slowDown() {
        try {
            Thread.sleep(speedInMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String bestMoveFor(Grid grid) {
        Grid bestGrid = moveCalculator.getBestGrid(grid, mark);
        StringBuilder movePosition = new StringBuilder();
        for (String cell : grid.emptyPositions()) {
            if (!bestGrid.isEmptyCell(cell)) {
                movePosition.append(cell);
            }
        }
        return movePosition.toString();
    }
}
