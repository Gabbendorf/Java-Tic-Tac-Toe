package ticTacToe.grid;

import ticTacToe.game.Mark;

import java.util.List;

public class Line {

    protected final List<String> cells;

    public Line(List<String> cells) {
        this.cells = cells;
    }

    public boolean isWinning() {
        if ((cells.stream()).distinct().limit(2).count() <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public Mark winningMark() {
        return Mark.create(cells.get(0));
    }
}
