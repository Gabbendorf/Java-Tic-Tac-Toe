package ticTacToe.ui;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.game.GameOption;
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
import static ticTacToe.game.GameOption.COMPUTER_VS_COMPUTER;
import static ticTacToe.game.GameOption.HUMAN_VS_HUMAN;
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
    public void asksToChooseGridSizeAndReturnsIt() {
        Ui ui = newUiWith("3");

        int gridSizeChosen = ui.chooseGridSize();

        assertEquals(3, gridSizeChosen);
        assertTrue(output.toString().contains("Choose a size for the grid [3 or 4]:"));
    }


    @Test
    public void asksForValidGridSize() {
        Ui ui = newUiWith("5\nn\n7\n3");

        int gridSizeChosen = ui.chooseGridSize();

        assertEquals(3, gridSizeChosen);
        assertTrue(output.toString().contains("Invalid grid size [3 or 4]:"));
    }

    @Test
    public void printsGrid3x3() {
        Ui ui = newUiWith("input");
        Grid grid = new Grid(3);

        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| --------- | \n" +
                                              "| 1 | 2 | 3 | \n" +
                                              "| --------- | \n" +
                                              "| 4 | 5 | 6 | \n" +
                                              "| --------- | \n" +
                                              "| 7 | 8 | 9 | \n" +
                                              "| --------- |"));
    }

    @Test
    public void printsGrid4x4() {
        Ui ui = newUiWith("input");
        Grid grid = new Grid(4);

        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| ------------- | \n" +
                                              "| 1 | 2 | 3 | 4 | \n" +
                                              "| ------------- | \n" +
                                              "| 5 | 6 | 7 | 8 | \n" +
                                              "| ------------- | \n" +
                                              "| 9 | 10| 11| 12| \n" +
                                              "| ------------- | \n" +
                                              "| 13| 14| 15| 16| \n" +
                                              "| ------------- |"));
    }
    @Test
    public void prints4x4GridWithColouredMarks() {
        Ui ui = newUiWith("input");
        Grid grid = new Grid(4);
        grid.addMark(NOUGHT, "2");
        grid.addMark(CROSS, "15");

        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| ------------- | \n" +
                                              "| 1 | " + GridFormatter.red_O + " | 3 | 4 | \n" +
                                              "| ------------- | \n" +
                                              "| 5 | 6 | 7 | 8 | \n" +
                                              "| ------------- | \n" +
                                              "| 9 | 10| 11| 12| \n" +
                                              "| ------------- | \n" +
                                              "| 13| 14| " + GridFormatter.blue_X + " | 16| \n" +
                                              "| ------------- |"));
    }

    @Test
    public void prints3x3GridWithColouredMarks() {
        Ui ui = newUiWith("input");
        Grid grid = new Grid(3);
        grid.addMark(NOUGHT, "2");
        grid.addMark(CROSS, "1");

        ui.printGrid(new Lines(), grid);

        assertTrue(output.toString().contains("| --------- | \n" +
                                              "| " + GridFormatter.blue_X + " | " + GridFormatter.red_O + " | 3 | \n" +
                                              "| --------- | \n" +
                                              "| 4 | 5 | 6 | \n" +
                                              "| --------- | \n" +
                                              "| 7 | 8 | 9 | \n" +
                                              "| --------- |"));
    }

    @Test
    public void asksForGameTypeOption() {
        Ui ui = newUiWith("1");

        GameOption gameOptionChosen = ui.chooseGameOption();

        assertTrue(output.toString().contains("Choose a game option:"));
        assertTrue(output.toString().contains("1- Human Player vs Human Player"));
        assertTrue(output.toString().contains("2- Human Player vs Smart Computer"));
        assertTrue(output.toString().contains("3- Computer vs Computer"));
        assertEquals(HUMAN_VS_HUMAN, gameOptionChosen);
    }

    @Test
    public void asksForCorrectInputForGameTypeOption() {
        Ui ui = newUiWith("n\n5\n3");

        GameOption validGameOption = ui.chooseGameOption();

        assertTrue(output.toString().contains("Invalid input."));
        assertEquals(COMPUTER_VS_COMPUTER, validGameOption);
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

        ui.declareWinner(CROSS);

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void announcesDraw() {
        Ui ui = newUiWith("input");

        ui.declareDraw();

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
