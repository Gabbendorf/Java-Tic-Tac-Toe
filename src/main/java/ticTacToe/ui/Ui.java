package ticTacToe.ui;

import ticTacToe.grid.Grid;
import ticTacToe.grid.GridPreparer;
import ticTacToe.grid.Rows;

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
        output.println("Welcome to tic-tac-toe");
        output.println("Are you ready to play??");
    }

    public void printGrid(Rows rows, int gridSize) {
        output.println(gridPreparer.prepareGridForPrinting(rows, gridSize));
    }

    public String askForMarkType() {
        output.println("First player: please choose a mark: X => Cross, O (letter) => Nought");
        String mark = input.nextLine().toUpperCase();
        return validMarkType(mark);
    }

    public String askForStarter() {
        output.println("Who starts: X or O (letter)?");
        String startingMark = input.nextLine().toUpperCase();
        return validMarkType(startingMark);
    }

    public void promptForPosition(String mark, Rows rows, int gridSize) {
        printGrid(rows, gridSize);
        output.println(String.format("Player %s: please choose a valid position in the grid", mark));
    }

    public String validPosition(Grid grid, String mark, Rows rows, int gridSize) {
        String validatedPosition = validatePosition(grid, mark, rows, gridSize, validInput(grid, mark, rows, gridSize, input.nextLine()));
        return notOccupiedPosition(grid, validatedPosition, mark, rows, gridSize);
    }

    public void confirmMove(String mark, String gridPosition) {
        output.println(String.format("Player %s marked position %s.", mark, gridPosition));
    }

    public void declareWinner(String mark, Rows rows, int gridSize) {
        printGrid(rows, gridSize);
        output.println(String.format("Player %s won!", mark));
    }

    public void declareDraw(Rows rows, int gridSize) {
        printGrid(rows, gridSize);
        output.println("It's draw: nobody wins!");
    }

    private String validMarkType(String mark) {
        while (!CROSS.mark.equals(mark) && !NOUGHT.mark.equals(mark)) {
            output.println("Invalid option: X => Cross or O (letter) => Nought");
            mark = input.nextLine().toUpperCase();
        }
        return mark;
    }

    private String validatePosition(Grid grid, String mark, Rows rows, int gridSize, String usersInput) {
        while (!isInsideValidRange(usersInput, grid)) {
            output.println("Invalid position.");
            usersInput = promptForNewInput(grid, mark, rows, gridSize);
        }
        return usersInput;
    }

    private boolean isInsideValidRange(String gridPosition, Grid grid) {
        Integer positionNumber = Integer.parseInt(gridPosition);
        return positionNumber > 0 && positionNumber <= grid.getCells().size();
    }

    private String validInput(Grid grid, String mark, Rows rows, int gridSize, String usersInput) {
        while (isNotNumber(usersInput)) {
            output.println("Invalid input: position must be a number.");
            usersInput = promptForNewInput(grid, mark, rows, gridSize);
        }
        return usersInput;
    }

    private boolean isNotNumber(String usersInput) {
        int position = -1;
        try {
            position = Integer.parseInt(usersInput);
        } catch (NumberFormatException e) {
        }
        return position == -1;
    }

    private String notOccupiedPosition(Grid grid, String validPosition, String mark, Rows rows, int gridSize) {
        while (!grid.isEmptyCell(validPosition)) {
            output.println("Position already occupied.");
            validPosition = promptForNewInput(grid, mark, rows, gridSize);
        }
        return validPosition;
    }

    private String promptForNewInput(Grid grid, String mark, Rows rows, int gridSize) {
        promptForPosition(mark, rows, gridSize);
        return validPosition(grid, mark, rows, gridSize);
    }
}
