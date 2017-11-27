package ticTacToe.ui;

import ticTacToe.grid.Grid;
import ticTacToe.grid.GridPreparer;
import ticTacToe.game.Lines;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class Ui {

    private final GridPreparer gridPreparer;
    private PrintStream output;
    private Scanner input;

    public Ui(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
        this.gridPreparer = new GridPreparer();
    }

    public void welcomePlayer() {
        clearScreen();
        output.println("Welcome to TIC TAC TOE!");
        output.println("Are you ready to play??");
    }

    public void printGrid(Lines lines, Grid grid) {
        output.println(gridPreparer.prepareGridForPrinting(lines, grid));
    }

    public String chooseOpponent() {
        output.println("\n");
        output.println("Choose opponent type:");
        opponentOptions();
        return validOptionNumber(input.nextLine());
    }

    public String askForMarkType() {
        clearScreen();
        output.println("First player: please choose a mark: X => Cross, O (letter) => Nought");
        return validMarkType(input.nextLine().toUpperCase());
    }

    public String askForStarter() {
        clearScreen();
        output.println("Who starts: X or O (letter)?");
        String markType = validMarkType(input.nextLine().toUpperCase());
        clearScreen();
        return markType;
    }

    public void promptForPosition(String mark) {
        output.println(String.format("Player %s: please choose a valid position in the grid", mark));
    }

    public String validPosition(Grid grid, String mark, Lines lines) {
        printGrid(lines, grid);
        String validatedPosition = validatePosition(grid, mark, lines, validInput(grid, mark, lines, input.nextLine()));
        return notOccupiedPosition(grid, validatedPosition, mark, lines);
    }

    public void confirmMove(String mark, String gridPosition) {
        clearScreen();
        output.println(String.format("Player %s marked position %s.", mark, gridPosition));
    }

    public void declareWinner(String mark, Lines lines, Grid grid) {
        clearScreen();
        printGrid(lines, grid);
        output.println(String.format("Player %s won!", mark));
    }

    public void declareDraw(Lines lines, Grid grid) {
        clearScreen();
        printGrid(lines, grid);
        output.println("It's draw: nobody wins!");
    }

    public String askToPlayAgain() {
        output.println("New game? y/n");
        return validAnswer(input.nextLine().toLowerCase());
    }

    public void sayBye() {
        output.println("See you soon!");
    }

    private void opponentOptions() {
        output.println("1- human player");
        output.println("2- smart computer.");
    }

    private String validOptionNumber(String opponentOptionNumber) {
        if (isInvalidOptionNumber(opponentOptionNumber)) {
            clearScreen();
            output.println("Please choose a valid option.");
            opponentOptions();
            opponentOptionNumber = input.nextLine();
        }
        return opponentOptionNumber;
    }

    private boolean isInvalidOptionNumber(String opponentOptionNumber) {
        return !opponentOptionNumber.equals("1") && !opponentOptionNumber.equals("2");
    }

    private String validMarkType(String mark) {
        while (isInvalidMark(mark)) {
            clearScreen();
            output.println("Invalid option: X => Cross or O (letter) => Nought");
            mark = input.nextLine().toUpperCase();
        }
        return mark;
    }

    private boolean isInvalidMark(String mark) {
        return !CROSS.mark.equals(mark) && !NOUGHT.mark.equals(mark);
    }

    private String validatePosition(Grid grid, String mark, Lines lines, String usersInput) {
        while (isOutsideValidRange(usersInput, grid)) {
            clearScreen();
            output.println("Invalid position.");
            usersInput = promptForNewInput(grid, mark, lines);
            printGrid(lines, grid);
        }
        return usersInput;
    }

    private boolean isOutsideValidRange(String gridPosition, Grid grid) {
        Integer positionNumber = Integer.parseInt(gridPosition);
        return positionNumber <= 0 || positionNumber > grid.getCells().size();
    }

    private String validInput(Grid grid, String mark, Lines lines, String usersInput) {
        while (isNotNumber(usersInput)) {
            clearScreen();
            output.println("Invalid input: position must be a number.");
            usersInput = promptForNewInput(grid, mark, lines);
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

    private String notOccupiedPosition(Grid grid, String validPosition, String mark, Lines lines) {
        while (!grid.isEmptyCell(validPosition)) {
            clearScreen();
            output.println("Position already occupied.");
            validPosition = promptForNewInput(grid, mark, lines);
            printGrid(lines, grid);
        }
        return validPosition;
    }

    private String promptForNewInput(Grid grid, String mark, Lines lines) {
        promptForPosition(mark);
        return validPosition(grid, mark, lines);
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
