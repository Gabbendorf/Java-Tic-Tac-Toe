package ticTacToe.ui;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.grid.GridFormatter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.*;

public class UiTest {

    private ByteArrayOutputStream output;
    private Grid gridWithSize;
    private Grid gridWithCells;
    private Lines lines;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        gridWithSize = new Grid(3);
        gridWithCells = new Grid(new ArrayList<>(Arrays.asList("X", "2")));
        lines = new Lines();
    }

    @Test
    public void saysHi() {
        Ui ui = newUiWith("input");

        ui.welcomePlayer();

        assertTrue(output.toString().contains("Welcome to TIC TAC TOE!"));
        assertTrue(output.toString().contains("Are you ready to play??"));
    }

    @Test
    public void printsGrid() {
        Ui ui = newUiWith("input");

        Grid grid = new Grid(3);
        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| --------- | \n " +
                                              "| 1 | 2 | 3 | \n " +
                                              "| --------- | \n " +
                                              "| 4 | 5 | 6 | \n " +
                                              "| --------- | \n " +
                                              "| 7 | 8 | 9 | \n " +
                                              "| --------- |"));
    }

    @Test
    public void printsGridWithColouredMarks() {
        Ui ui = newUiWith("input");
        Grid grid = new Grid(3);
        grid.addMark(NOUGHT, "2");
        grid.addMark(CROSS, "1");

        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| --------- | \n | " +
                                              GridFormatter.blue_X + " | " + GridFormatter.red_O + " | 3 | \n" +
                                             " | --------- | \n " +
                                              "| 4 | 5 | 6 | \n " +
                                              "| --------- | \n " +
                                              "| 7 | 8 | 9 | \n " +
                                              "| --------- |"));
    }

    @Test
    public void asksForOpponentTypeNumber() {
        Ui ui = newUiWith("1");

        String opponentOptionNumber = ui.chooseOpponent();

        assertTrue(output.toString().contains("Choose opponent type:"));
        assertTrue(output.toString().contains("1- human player"));
        assertTrue(output.toString().contains("2- smart computer."));
        assertEquals("1", opponentOptionNumber);
    }

    @Test
    public void asksForValidOpponentTypeNumber() {
        Ui ui = newUiWith("3\nd\n1");

        String validOpponentNumber = ui.chooseOpponent();

        assertTrue(output.toString().contains("Please choose a valid opponent:"));
        assertEquals("1", validOpponentNumber);
    }

    @Test
    public void asksForMarkType() {
        Ui ui = newUiWith("X");

        Mark markType = ui.askForMarkType();

        assertTrue(output.toString().contains("First player: please choose a mark: " + GridFormatter.blue_X +
                                              " => Cross, " + GridFormatter.red_O + " (letter) => Nought"));
        assertEquals(CROSS, markType);
    }

    @Test
    public void asksToRepeatMarkType() {
        Ui ui = newUiWith("0\no");

        Mark markType = ui.askForMarkType();

        assertTrue(output.toString().contains("Invalid option: " + GridFormatter.blue_X + " => Cross or "
                                              + GridFormatter.red_O + " (letter) => Nought"));
        assertEquals(NOUGHT, markType);
    }

    @Test
    public void asksWhoStarts() {
        Ui ui = newUiWith("x");

        Mark startingMark = ui.askForStarter();

        assertTrue(output.toString().contains("Who starts: " + GridFormatter.blue_X + " or " + GridFormatter.red_O + " (letter)?"));
        assertEquals(CROSS, startingMark);
    }

    @Test
    public void asksForCorrectStarter() {
        Ui ui = newUiWith("me\nx");

        Mark startingMark = ui.askForStarter();

        assertTrue(output.toString().contains("Invalid option: " + GridFormatter.blue_X + " => Cross or " + GridFormatter.red_O + " (letter) => Nought"));
        assertEquals(CROSS, startingMark);
    }

    @Test
    public void asksForPositionForMove() {
        Ui ui = newUiWith("input");

        ui.promptForPosition(CROSS);

        assertTrue(output.toString().contains("Player X: please choose a valid position in the grid"));
    }

    @Test
    public void returnsValidPosition() {
        Ui ui = newUiWith("1");

        String gridPosition = ui.validPosition(gridWithSize, CROSS, lines);

        assertEquals("1", gridPosition);
    }

    @Test
    public void asksForNotOccupiedPosition() {
        Ui ui = newUiWith("1\n2");

        String gridPosition = ui.validPosition(gridWithCells, CROSS , lines);

        assertTrue(output.toString().contains("Position already occupied."));
        assertEquals("2", gridPosition);
    }

    @Test
    public void asksForValidPosition() {
        Ui ui = newUiWith("10\n1");

        String gridPosition = ui.validPosition(gridWithSize, CROSS, lines);

        assertTrue(output.toString().contains("Invalid position."));
        assertEquals("1", gridPosition);
    }

    @Test
    public void asksForNumberAsInput() {
        Ui ui = newUiWith("d\n1");

        String gridPosition = ui.validPosition(gridWithSize, CROSS, lines);

        assertTrue(output.toString().contains("Invalid input: position must be a number."));
        assertEquals("1", gridPosition);
    }

    @Test
    public void returnsValidInputAfterAllChecks() {
        Ui ui = newUiWith("h\n100\nh\n1");

        String validPosition = ui.validPosition(gridWithSize, CROSS, lines);

        assertEquals("1", validPosition);
    }

    @Test
    public void confirmsMove() {
        Ui ui = newUiWith("input");

        ui.confirmMove(CROSS, "1");

        assertTrue(output.toString().contains("Player X marked position 1."));
    }

    @Test
    public void announcesWinner() {
        Ui ui = newUiWith("input");

        ui.declareWinner(CROSS, lines, gridWithSize);

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void announcesDraw() {
        Ui ui = newUiWith("input");

        ui.declareDraw(lines, gridWithSize);

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    @Test
    public void asksUserIfWantsToPlayAgain() {
        Ui ui = newUiWith("yes\nY");

        String newGameAnswer = ui.askToPlayAgain();

        assertTrue(output.toString().contains("New game? y/n"));
        assertTrue(output.toString().contains("Sorry, I didn't understand."));
        assertEquals("y", newGameAnswer);
    }

    @Test
    public void saysByeBeforeUserLeaves() {
        Ui ui = newUiWith("input");

        ui.sayBye();

        assertTrue(output.toString().contains("See you soon!"));
    }

    private Ui newUiWith(String inputString) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());

        return new Ui(new PrintStream(output), input);
    }
}
