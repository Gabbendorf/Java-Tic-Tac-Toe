package ticTacToe.ui;

import ticTacToe.game.GameOption;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.GridFormatter;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private final static String THREE = "3";
    private final static String FOUR = "4";
    private final GridFormatter gridFormatter;
    private PrintStream output;
    private Scanner input;

    public Ui(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
        this.gridFormatter = new GridFormatter();
    }

    public void welcomePlayer() {
        clearScreen();
        output.println("Welcome to TIC TAC TOE!");
        output.println("Are you ready to play??");
        output.println("\n");
    }

    public int chooseGridSize() {
        output.println("Choose a size for the grid [" + THREE + " or " + FOUR + "]:");
        String userInput = input.nextLine();
        while (isInvalidGridSize(userInput)) {
            clearScreen();
            output.println("Invalid grid size [" + THREE + " or " + FOUR + "]:");
            userInput = input.nextLine();
        }
        return Integer.parseInt(userInput);
    }

    public void printGrid(Grid grid) {
        List<ArrayList<String>> rowsWithColours = gridFormatter.rowsWithColours(grid.rows());
        output.println(gridFormatter.prepareGridForPrinting(rowsWithColours, grid.getSize()));
    }

    public GameOption chooseGameOption() {
        clearScreen();
        output.println("Choose a game option:");
        gameTypeOptions();
        return validGameOption(validatedInput());
    }

    public Mark askForMarkType() {
        clearScreen();
        output.println("First player: please choose a mark: " + GridFormatter.BLUE_X + " => Cross, " + GridFormatter.RED_O + " (letter) => Nought");
        return validMarkType(input.nextLine().toUpperCase());
    }

    public Mark askForStarter() {
        clearScreen();
        output.println("Who starts: " + GridFormatter.BLUE_X + " or " + GridFormatter.RED_O + " (letter)?");
        Mark markType = validMarkType(input.nextLine().toUpperCase());
        clearScreen();
        return markType;
    }

    public void promptForPosition(Mark mark) {
        output.println(String.format("Player %s: please choose a valid position in the grid", mark.sign));
    }

    public String validPosition(Grid grid, Mark mark) {
        String validatedPosition = validatePosition(grid, mark, validInput(grid, mark, input.nextLine()));
        return notOccupiedPosition(grid, validatedPosition, mark);
    }

    public void confirmMove(Mark mark, String gridPosition) {
        clearScreen();
        output.println(String.format("Player %s marked position %s.", mark.sign, gridPosition));
    }

    public void declareWinner(Mark mark) {
        clearScreen();
        output.println(String.format("Player %s won!", mark.sign));
    }

    public void declareDraw() {
        clearScreen();
        output.println("It's draw: nobody wins!");
    }

    public String askToPlayAgain() {
        output.println("New game? y/n");
        return validAnswer(input.nextLine().toLowerCase());
    }

    public void sayBye() {
        output.println("See you soon!");
    }

    private boolean isInvalidGridSize(String userInput) {
        return !userInput.equals(THREE) && !userInput.equals(FOUR);
    }

    private void gameTypeOptions() {
        output.println("1- Human Player vs Human Player");
        output.println("2- Human Player vs Smart Computer");
        output.println("3- Computer vs Computer");
    }

    private int validatedInput() {
        String userInput = input.nextLine();
        while (isNotNumber(userInput)) {
            clearScreen();
            output.println("Invalid input.");
            gameTypeOptions();
            userInput = input.nextLine();
        }
        return Integer.parseInt(userInput);
    }

    private GameOption validGameOption(int validInput) {
        while (!GameOption.isValid(validInput)) {
            clearScreen();
            output.println("Wrong option, please choose a valid game option:");
            gameTypeOptions();
            validInput = validatedInput();
        }
        return GameOption.create(validInput);
    }

    private Mark validMarkType(String mark) {
        while (!Mark.isValid(mark)) {
            clearScreen();
            output.println("Invalid option: " + GridFormatter.BLUE_X + " => Cross or " + GridFormatter.RED_O + " (letter) => Nought");
            mark = input.nextLine().toUpperCase();
        }
        return Mark.create(mark);
    }

    private String validatePosition(Grid grid, Mark mark, String usersInput) {
        while (!grid.isInsideCellsRange(usersInput)) {
            clearScreen();
            output.println("Invalid position.");
            printGrid(grid);
            usersInput = validPosition(grid, mark);
            printGrid(grid);
        }
        return usersInput;
    }

    private String validInput(Grid grid, Mark mark, String usersInput) {
        while (isNotNumber(usersInput)) {
            clearScreen();
            output.println("Invalid input: position must be a number.");
            printGrid(grid);
            usersInput = validPosition(grid, mark);
        }
        return usersInput;
    }

    private boolean isNotNumber(String usersInput) {
        try {
            Integer.parseInt(usersInput);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private String notOccupiedPosition(Grid grid, String validPosition, Mark mark) {
        while (!grid.isEmptyCell(validPosition)) {
            clearScreen();
            output.println("Position already occupied.");
            printGrid(grid);
            validPosition = validPosition(grid, mark);
            printGrid(grid);
        }
        return validPosition;
    }

    private String validAnswer(String answer) {
        if (isInvalidAnswer(answer)) {
            clearScreen();
            output.println("Sorry, I didn't understand.");
            answer = askToPlayAgain();
        }
        return answer;
    }

    private boolean isInvalidAnswer (String answer) {
        return !answer.equals("y") && !answer.equals("n");
    }

    private void clearScreen() {
        output.println("\033[H\033[2J");
        output.flush();
    }
}
