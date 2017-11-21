package ticTacToe.player;

import org.junit.BeforeClass;
import org.junit.Test;
import ticTacToe.game.Lines;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SmartComputerTest {

    private static SmartComputer computer;

    @BeforeClass
    public static void newComputer() {
        computer = new SmartComputer("X", new MoveGenerator());
    }

    @Test
    public void returnsRandomMoveIfStarter() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        SmartComputer computer = new SmartComputer("X", new MoveGeneratorDouble());

        assertEquals("5", computer.makeMove(ui, grid, lines));
    }

    @Test
    public void returnsBestMoveIfNotStarter() {
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(new ArrayList<>(Arrays.asList("1", "X", "O", "X", "O", "X", "X", "O", "9")));
        Lines lines = new Lines();

        String moveToWin = "1";

        assertEquals(moveToWin, computer.makeMove(ui, grid, lines));
    }

    @Test
    public void returnsItsMark() {
        assertEquals("X", computer.getMark());
    }
}

