package ticTacToe.player;

import org.junit.BeforeClass;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;

public class SmartComputerTest {

    private static SmartComputer computer;

    @BeforeClass
    public static void newComputer() {
        computer = new SmartComputer(CROSS, 0);
    }

    @Test
    public void returnsBestMoveThatWins() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("1", "X", "O", "X", "O", "X", "X", "O", "9"));

        String moveToWin = "1";

        assertEquals(moveToWin, computer.makeMove(ui, grid));
    }

    @Test
    public void returnsMoveThatPreventsOpponentFromWinning() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("X", "O", "X", "4", "O", "6", "7", "8", "9"));

        String moveToBlockOpponent = "8";

        assertEquals(moveToBlockOpponent, computer.makeMove(ui, grid));
    }

    @Test
    public void returnsMoveThatBlocksOpponentOn4x4Grid() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("X", "O", "O", "4", "5", "X", "7", "8", "9", "10", "X", "12", "13", "14", "15", "16"));

        String moveToBlockOpponent = "16";

        assertEquals(moveToBlockOpponent, computer.makeMove(ui, grid));
    }

    @Test
    public void returnsMoveThatWinsOn4x4Grid() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("O", "X", "X", "4", "5", "O", "7", "8", "9", "10", "O", "12", "13", "14", "15", "16"));

        String moveToWin = "16";

        assertEquals(moveToWin, computer.makeMove(ui, grid));
    }

    @Test
    public void returnsMoveThatPreventsOpponentFromCreatingWinningFork() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("O", "2", "3", "4", "X", "6", "7", "8", "O"));

        List<String> movesToAvoidLosing = new ArrayList<>(Arrays.asList("2", "4", "6", "8"));

        assertTrue(movesToAvoidLosing.contains(computer.makeMove(ui, grid)));
    }

    @Test
    public void createsForkForMultipleWinningMoves() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(Arrays.asList("X", "O", "3", "4", "5", "6", "7", "8", "9"));

        List<String> movesToCreateWinningFork = new ArrayList<>(Arrays.asList("4", "5", "7"));

        assertTrue(movesToCreateWinningFork.contains(computer.makeMove(ui, grid)));
    }

    @Test
    public void returnsItsMark() {
        assertEquals(CROSS, computer.getMark());
    }
}

