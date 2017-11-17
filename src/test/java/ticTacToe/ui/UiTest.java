package ticTacToe.ui;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UiTest {

    private ByteArrayOutputStream output;
    private Grid gridWithSize;
    private Grid gridWithCells;
    private Rows rows;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        gridWithSize = new Grid(3);
        gridWithCells = new Grid(new ArrayList<>(Arrays.asList("X", "2")));
        rows = new Rows(gridWithSize);
    }

    @Test
    public void saysHi() {
        Ui ui = newUiWith("input");

        ui.welcomePlayer();

        assertTrue(output.toString().contains("Welcome to tic-tac-toe"));
        assertTrue(output.toString().contains("Are you ready to play??"));
    }

    @Test
    public void printsGrid() {
        Ui ui = newUiWith("input");

        ui.printGrid(new Rows(new Grid(3)));

        assertTrue(output.toString().contains("| 1 | 2 | 3 | \n | _________ | \n | 4 | 5 | 6 | \n | _________ | \n | 7 | 8 | 9 | \n | _________ |"));
    }

    @Test
    public void asksForMarkType() {
        Ui ui = newUiWith("X");

        String markType = ui.askForMarkType();

        assertTrue(output.toString().contains("First player: please choose a mark: X => Cross, O (letter) => Nought"));
        assertEquals("X", markType);
    }

    @Test
    public void asksToRepeatMarkType() {
        Ui ui = newUiWith("0\no");

        String markType = ui.askForMarkType();

        assertTrue(output.toString().contains("Invalid option: X => Cross or O (letter) => Nought"));
        assertEquals("O", markType);
    }

    @Test
    public void asksWhoStarts() {
        Ui ui = newUiWith("x");

        String startingMark = ui.askForStarter();

        assertTrue(output.toString().contains("Who starts: X or O (letter)?"));
        assertEquals("X", startingMark);
    }

    @Test
    public void asksForCorrectStarter() {
        Ui ui = newUiWith("me\nx");

        String startingMark = ui.askForStarter();

        assertTrue(output.toString().contains("Invalid option: X => Cross or O (letter) => Nought"));
        assertEquals("X", startingMark);
    }

    @Test
    public void checksInputIsValidNumberAndInsideRange() {
        Ui ui = newUiWith("h\n100\nh\n1");

        String validPosition = ui.validPosition(gridWithSize, "X", rows);

        assertEquals("1", validPosition);
    }

    @Test
    public void asksForPositionForMove() {
        Ui ui = newUiWith("input");

        ui.promptForPosition("X", rows);

        assertTrue(output.toString().contains("Player X: please choose a valid position in the grid"));
    }

    @Test
    public void returnsValidPosition() {
        Ui ui = newUiWith("1");

        String gridPosition = ui.validPosition(gridWithSize, "X", rows);

        assertEquals("1", gridPosition);
    }

    @Test
    public void asksForNotOccupiedPosition() {
        Ui ui = newUiWith("1\n2");

        String gridPosition = ui.validPosition(gridWithCells, "X", rows);

        assertTrue(output.toString().contains("Position already occupied."));
        assertEquals("2", gridPosition);
    }

    @Test
    public void asksForValidPosition() {
        Ui ui = newUiWith("10\n1");

        String gridPosition = ui.validPosition(gridWithSize, "X", rows);

        assertTrue(output.toString().contains("Invalid position."));
        assertEquals("1", gridPosition);
    }

    @Test
    public void confirmsMove() {
        Ui ui = newUiWith("input");

        ui.confirmMove("X", "1");

        assertTrue(output.toString().contains("Player X marked position 1."));
    }

    @Test
    public void announcesWinner() {
        Ui ui = newUiWith("input");

        ui.declareWinner("X", rows);

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void announcesDraw() {
        Ui ui = newUiWith("input");

        ui.declareDraw(rows);

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
