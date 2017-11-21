package ticTacToe.ui;

import ticTacToe.grid.Grid;
import ticTacToe.grid.GridPreparer;
import ticTacToe.game.Rules;

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

    public void printGrid(Rules rules, Grid grid) {
        output.println(gridPreparer.prepareGridForPrinting(rules, grid));
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

    public void promptForPosition(String mark, Rules rules, Grid grid) {
        printGrid(rules, grid);
        output.println(String.format("Player %s: please choose a valid position in the grid", mark));
    }

    public String validPosition(Grid grid, String mark, Rules rules) {
        String validatedPosition = validatePosition(grid, mark, rules, validInput(grid, mark, rules, input.nextLine()));
        return notOccupiedPosition(grid, validatedPosition, mark, rules);
    }

    public void confirmMove(String mark, String gridPosition) {
        output.println(String.format("Player %s marked position %s.", mark, gridPosition));
    }

    public void declareWinner(String mark, Rules rules, Grid grid) {
        printGrid(rules, grid);
        output.println(String.format("Player %s won!", mark));
    }

    public void declareDraw(Rules rules, Grid grid) {
        printGrid(rules, grid);
        output.println("It's draw: nobody wins!");
    }

    public String askToPlayAgain() {
        output.println("New game? y/n");
        String answer = input.nextLine().toLowerCase();
        if (!answer.equals("y") && !answer.equals("n")) {
            output.println("Sorry, I didn't understand.");
            answer = askToPlayAgain();
        }
        return answer;
    }

    public void sayBye() {
        output.println("See you soon!");
    }

    private String validMarkType(String mark) {
        while (!CROSS.mark.equals(mark) && !NOUGHT.mark.equals(mark)) {
            output.println("Invalid option: X => Cross or O (letter) => Nought");
            mark = input.nextLine().toUpperCase();
        }
        return mark;
    }

    private String validatePosition(Grid grid, String mark, Rules rules, String usersInput) {
        while (isOutsideValidRange(usersInput, grid)) {
            output.println("Invalid position.");
            usersInput = promptForNewInput(grid, mark, rules);
        }
        return usersInput;
    }

    private boolean isOutsideValidRange(String gridPosition, Grid grid) {
        Integer positionNumber = Integer.parseInt(gridPosition);
        return positionNumber < 0 || positionNumber > grid.getCells().size();
    }

    private String validInput(Grid grid, String mark, Rules rules, String usersInput) {
        while (isNotNumber(usersInput)) {
            output.println("Invalid input: position must be a number.");
            usersInput = promptForNewInput(grid, mark, rules);
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

    private String notOccupiedPosition(Grid grid, String validPosition, String mark, Rules rules) {
        while (!grid.isEmptyCell(validPosition)) {
            output.println("Position already occupied.");
            validPosition = promptForNewInput(grid, mark, rules);
        }
        return validPosition;
    }

    private String promptForNewInput(Grid grid, String mark, Rules rules) {
        promptForPosition(mark, rules, grid);
        return validPosition(grid, mark, rules);
    }
}
