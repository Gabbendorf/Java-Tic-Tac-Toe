package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;

import java.util.Collections;
import java.util.List;

public class ComputerMoveCalculator {

    private final Mark computerMark;
    private Grid bestGrid;

    public ComputerMoveCalculator(Mark mark) {
        this.computerMark = mark;
    }

    public Grid getBestGrid(Grid grid, Mark mark) {
        calculateMinimax(grid, 8, -1, 1, mark);
        return bestGrid;
    }

    private int calculateMinimax(Grid grid, int depthLevel, int alpha, int beta, Mark mark) {
        if (grid.isFinishedGame() || depthLevel == 0) {
            return scoreForFinalGrid(grid);
        } else {
            List<Grid> gridsWithMoves = grid.makeCopiesOfGridWith(mark);
            Collections.shuffle(gridsWithMoves);
            if (mark == computerMark) {
                return maximisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark);
            } else {
                return minimisedScore(gridsWithMoves, grid, depthLevel, alpha, beta, mark);
            }
        }
    }

    private int scoreForFinalGrid(Grid gridCopy) {
        int score;
        if (gridCopy.isWinner()) {
            if (gridCopy.winningMark() == computerMark) {
                score = 1;
            } else {
                score = -1;
            }
        } else {
            score = 0;
        }
        return score;
    }

    private int maximisedScore(List<Grid> gridsWithMoves, Grid grid, int depthLevel, int alpha, int beta, Mark mark) {
        Grid gridSelected = grid;
        for (Grid gridWithMove : gridsWithMoves) {
            int score = calculateMinimax(gridWithMove, depthLevel-1, alpha, beta, mark.swap());
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

    private int minimisedScore(List<Grid> gridsWithMoves, Grid grid, int depthLevel, int alpha, int beta, Mark mark) {
        Grid gridSelected = grid;
        for (Grid gridWithMove : gridsWithMoves) {
            int score = calculateMinimax(gridWithMove, depthLevel-1, alpha, beta, mark.swap());
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
