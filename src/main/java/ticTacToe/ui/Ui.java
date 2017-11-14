package ticTacToe.ui;

import ticTacToe.grid.Grid;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static ticTacToe.grid.Mark.CROSS;
import static ticTacToe.grid.Mark.NOUGHT;

public class Ui {

    private PrintStream output;
    private Scanner input;

    public Ui(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
    }

    public void welcomePlayer() {
        output.println("Welcome to tic-tac-toe");
        output.println("Are you ready to play??");
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

    public void promptForPosition(String mark) {
        output.println(String.format("Player %s: please choose a valid position in the grid", mark));
    }

    public String validPosition(Grid grid, String mark) {
        String validPosition = validatePosition(grid, input.nextLine(), mark);
        return notOccupiedPosition(grid, validPosition, mark);
    }

    public void confirmMove(String mark, String gridPosition) {
        output.println(String.format("Player %s marked position %s.", mark, gridPosition));
    }

    public void declareWinner(String mark) {
        output.println(String.format("Player %s won!", mark));
    }

    public void declareDraw() {
        output.println("It's draw: nobody wins!");
    }

    private String validMarkType(String mark) {
        while (!CROSS.mark.equals(mark) && !NOUGHT.mark.equals(mark)) {
            output.println("Invalid option: X => Cross or O (letter) => Nought");
            mark = input.nextLine().toUpperCase();
        }
        return mark;
    }

    private String validatePosition(Grid grid, String gridPosition, String mark) {
        while (!isInsideValidRange(gridPosition, grid)) {
            output.println("Invalid position.");
            promptForPosition(mark);
            gridPosition = input.nextLine();
        }
        return gridPosition;
    }

    private boolean isInsideValidRange(String gridPosition, Grid grid) {
        return Integer.parseInt(gridPosition) > 0 && Integer.parseInt(gridPosition) <= grid.getCells().size();
    }

    private String notOccupiedPosition(Grid grid, String validPosition, String mark) {
        while (!grid.isEmptyCell(validPosition)) {
            output.println("Position already occupied.");
            promptForPosition(mark);
            validPosition = input.nextLine();
        }
        return validPosition;
    }
}
