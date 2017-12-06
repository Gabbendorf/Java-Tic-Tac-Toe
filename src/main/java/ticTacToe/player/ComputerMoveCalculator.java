package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;

import java.util.Collections;
import java.util.List;

public class ComputerMoveCalculator {

    private final Mark computerMark;
    private Grid bestGrid;

    public ComputerMoveCalculator(Mark mark) {
        this.computerMark = mark;
    }

    public Grid getBestGrid(Grid grid, Mark mark, Lines lines) {
        calculateMinimax(grid, 8, -1, 1, mark, lines);
        return bestGrid;
    }

    private int calculateMinimax(Grid grid, int depthLevel, int alpha, int beta, Mark mark, Lines lines) {
        if (grid.isFinishedGame(lines) || depthLevel == 0) {
            return scoreForFinalGrid(grid, lines);
        } else {
            List<Grid> gridsWithMoves = grid.makeCopiesOfGridWith(mark);
            Collections.shuffle(gridsWithMoves);
            if (mark == computerMark) {
                return maximisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark, lines);
            } else {
                return minimisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark, lines);
            }
        }
    }

    private int scoreForFinalGrid(Grid gridCopy, Lines lines) {
        int score;
        if (lines.isWinning(gridCopy)) {
            if (lines.winningMark(gridCopy) == computerMark) {
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
