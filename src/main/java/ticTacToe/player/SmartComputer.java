package ticTacToe.player;

import ticTacToe.grid.Lines;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.util.*;

public class SmartComputer implements Player {

    private final Mark mark;
    private final MoveGenerator firstMove;
    private final int speedInMills;
    private int score;

    public SmartComputer(Mark mark, MoveGenerator firstMove, int speedInMillis) {
        this.mark = mark;
        this.firstMove = firstMove;
        this.speedInMills = speedInMillis;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        StringBuilder move = new StringBuilder();
        if (grid.isAllEmpty()) {
            move.append(firstMove.getRandomMove(grid));
        } else {
            move.append(bestMovePosition(grid, lines));
        }
        slowDown();
        return move.toString();
    }

    public Mark getMark() {
        return mark;
    }



    private String bestMovePosition(Grid grid, Lines lines) {
        Grid bestGrid = gridCopyWithBestMove(grid, lines);
        List<String> freePositions = grid.emptyPositions();
        StringBuilder movePosition = new StringBuilder();
        for (String cell : freePositions) {
           if (!bestGrid.isEmptyCell(cell)) {
               movePosition.append(cell);
           }
        }
        return movePosition.toString();
    }

    private Grid gridCopyWithBestMove(Grid grid, Lines lines) {
        Map<Integer, Grid> possibleMovesInGridCopies = gridCopiesWithScores(grid, lines, mark);
        int maxScore = Collections.max(possibleMovesInGridCopies.keySet());
        return possibleMovesInGridCopies.get(maxScore);
    }

    private Map<Integer, Grid> gridCopiesWithScores(Grid grid, Lines lines, Mark mark) {
        List<Grid> gridCopies = grid.makeCopiesOfGridWith(mark);
        Map<Integer, Grid> gridsWithScores = new HashMap<>();
        for (Grid newGridCopy : gridCopies) {
            gridsWithScores.put(scoreFor(newGridCopy, lines, mark), newGridCopy);
        }
        return gridsWithScores;
    }

    private int scoreFor(Grid gridCopy, Lines lines, Mark mark) {
        if (gridCopy.isFinishedGame(lines)) {
            ScoreForFinalGrid(gridCopy, lines);
        } else {
            Mark currentMark = mark.swap();
            Map<Integer, Grid> gridsWithScores = gridCopiesWithScores(gridCopy, lines, currentMark);
            applyMiniMax(gridsWithScores, currentMark.sign);
        }
        return score;
    }

    private void ScoreForFinalGrid(Grid gridCopy, Lines lines) {
        if (lines.isWinning(gridCopy)) {
            if (lines.winningMark(gridCopy).equals(mark)) {
                score = 1;
            } else {
                score = -1;
            }
        } else {
            score = 0;
        }
    }

    private void applyMiniMax(Map<Integer, Grid> gridsWithScores, String currentMark) {
        if (currentMark.equals(mark.sign)) {
            score = Collections.max(gridsWithScores.keySet());
        } else {
            score = Collections.min(gridsWithScores.keySet());
        }
    }

    private void slowDown() {
        try {
            Thread.sleep(speedInMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
