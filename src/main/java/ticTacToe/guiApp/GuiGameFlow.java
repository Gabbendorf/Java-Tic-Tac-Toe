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

    public String messageForGameState() {
        StringBuilder message = new StringBuilder();
        if (grid.isFinishedGame(lines)) {
            message.append(messageForGameOver());
        } else {
            message.append(String.format("It's %s turn", grid.nextMark().sign));
        }
        return message.toString();
    }

    private String messageForGameOver() {
        StringBuilder message = new StringBuilder();
        if (lines.isWinning(grid)) {
            message.append(String.format("%s won!", lines.winningMark(grid).sign));
        } else if (grid.allOccupiedCells()) {
            message.append("It's draw!");
        }
        return message.toString();
    }
}
