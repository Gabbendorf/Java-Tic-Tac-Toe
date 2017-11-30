package ticTacToe.player;

import org.junit.BeforeClass;
import org.junit.Test;
import ticTacToe.grid.Lines;
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
        computer = new SmartComputer(CROSS, new MoveGenerator());
    }

    @Test
    public void returnsRandomMoveIfStarter() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        SmartComputer computer = new SmartComputer(CROSS, new MoveGeneratorDouble());

        assertEquals("5", computer.makeMove(ui, grid, lines));
    }

    @Test
    public void returnsBestMoveThatLeadsToWin() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("1", "X", "O", "X", "O", "X", "X", "O", "9")));
        Lines lines = new Lines();

        String moveToWin = "1";

        assertEquals(moveToWin, computer.makeMove(ui, grid, lines));
    }

    @Test
    public void returnsMoveThatPreventsOpponentFromWinning() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "O", "X", "4", "O", "6", "7", "8", "9")));
        Lines lines = new Lines();

        String moveToBlockOpponent = "8";

        assertEquals(moveToBlockOpponent, computer.makeMove(ui, grid, lines));
    }

    @Test
    public void createsForkForMultipleWinningMoves() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("X", "O", "3", "4", "5", "6", "7", "8", "9")));
        Lines lines = new Lines();

        String moveToCreateWinningFork = "7";

        assertEquals(moveToCreateWinningFork, computer.makeMove(ui, grid, lines));
    }

    @Test
    public void blocksWinningForkForOpponent() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("O", "X", "3", "4", "X", "6", "7", "8", "O")));
        Lines lines = new Lines();

        List<String> movesToBlockWinningFork = new ArrayList<>(Arrays.asList("4", "7", "8"));

        assertTrue(movesToBlockWinningFork.contains(computer.makeMove(ui, grid, lines)));
    }

    @Test
    public void returnsItsMark() {
        assertEquals(CROSS, computer.getMark());
    }
}

