package ticTacToe.player;

import ticTacToe.game.Lines;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.util.*;

import static ticTacToe.game.Mark.switchPlayerMark;

public class SmartComputer implements Player {

    private final String mark;
    private final MoveGenerator firstMove;
    private int score;

    public SmartComputer(String mark, MoveGenerator firstMove) {
        this.mark = mark;
        this.firstMove = firstMove;
    }

    public String makeMove(Ui ui, Grid grid, Lines lines) {
        StringBuilder move = new StringBuilder();
        if (grid.isAllEmpty()) {
            move.append(firstMove.getRandomMove(grid));
        } else {
            move.append(bestMovePosition(grid, lines));
        }
        ui.confirmMove(mark, move.toString());
        return move.toString();
    }

    public String getMark() {
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

    private Map<Integer, Grid> gridCopiesWithScores(Grid grid, Lines lines, String mark) {
        List<Grid> gridCopies = grid.makeCopiesOfGridWith(mark);
        Map<Integer, Grid> gridsWithScores = new HashMap<>();
        for (Grid newGridCopy : gridCopies) {
            gridsWithScores.put(scoreFor(newGridCopy, lines, mark), newGridCopy);
        }
        return gridsWithScores;
    }

    private int scoreFor(Grid gridCopy, Lines lines, String mark) {
        if (gridCopy.isEndedGame(lines)) {
            ScoreForFinalGrid(gridCopy, lines);
        } else {
            String currentMark = switchPlayerMark(mark);
            Map<Integer, Grid> gridsWithScores = gridCopiesWithScores(gridCopy, lines, currentMark);
            applyMiniMax(gridsWithScores, currentMark);
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
        if (currentMark.equals(mark)) {
            score = Collections.max(gridsWithScores.keySet());
        } else {
            score = Collections.min(gridsWithScores.keySet());
        }
    }
}
