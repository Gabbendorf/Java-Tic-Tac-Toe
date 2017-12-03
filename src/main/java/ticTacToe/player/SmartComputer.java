package ticTacToe.player;

import ticTacToe.grid.Lines;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.util.*;

public class SmartComputer implements Player {

    private final Mark mark;
    private final int speedInMills;
    private Grid bestGrid;

    public SmartComputer(Mark mark, int speedInMillis) {
        this.mark = mark;
        this.speedInMills = speedInMillis;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        slowDown();
        return bestMoveFor(grid, lines);
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

    private String bestMoveFor(Grid grid, Lines lines) {
        calculateMinimax(grid, 8, -1, 1, mark, lines);
        StringBuilder movePosition = new StringBuilder();
        for (String cell : grid.emptyPositions()) {
            if (!bestGrid.isEmptyCell(cell)) {
                movePosition.append(cell);
            }
        }
        return movePosition.toString();
    }

    private int calculateMinimax(Grid grid, int depthLevel, int alpha, int beta, Mark mark, Lines lines) {
        if (grid.isFinishedGame(lines) || depthLevel == 0) {
            return scoreForFinalGrid(grid, lines);
        } else {
            List<Grid> gridsWithMoves = grid.makeCopiesOfGridWith(mark);
            Collections.shuffle(gridsWithMoves);
            if (mark == this.mark) {
                return maximisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark, lines);
            } else {
                return minimisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark, lines);
            }
        }
    }

    private int scoreForFinalGrid(Grid gridCopy, Lines lines) {
        int score;
        if (lines.isWinning(gridCopy)) {
            if (lines.winningMark(gridCopy) == mark) {
                score = 1;
            } else {
                score = -1;
            }
        } else {
            score = 0;
        }
        return score;
    }

    private int maximisedScore(List<Grid> gridsWithMoves, Grid grid, int depthLevel, int alpha, int beta, Mark mark, Lines lines) {
        Grid gridSelected = grid;
        for (Grid gridWithMove : gridsWithMoves) {
            int score = calculateMinimax(gridWithMove, depthLevel-1, alpha, beta, mark.swap(), lines);
            if (score > alpha) {
                alpha = score;
                gridSelected = gridWithMove;
            }
            if (alpha >= beta) {
                break;
            }
        }
        bestGrid = gridSelected;
        return alpha;
    }

    private int minimisedScore(List<Grid> gridsWithMoves, Grid grid, int depthLevel, int alpha, int beta, Mark mark, Lines lines) {
        Grid gridSelected = grid;
        for (Grid gridWithMove : gridsWithMoves) {
            int score = calculateMinimax(gridWithMove, depthLevel-1, alpha, beta, mark.swap(), lines);
            if (score < beta) {
                beta = score;
                gridSelected = gridWithMove;
            }
            if (alpha >= beta) {
                break;
            }
        }
        bestGrid = gridSelected;
        return beta;
    }
}
