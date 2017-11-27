package ticTacToe.game;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;
import ticTacToe.ui.UiDouble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameFlowTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    @Test
    public void runsNewGameThatIsDraw() {
        GameFlow gameFlow = newGameFlow("1\nx\no\n1\n2\n3\n4\n6\n5\n8\n9\n7\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    @Test
    public void runsNewGameWherePlayerCrossWins() {
        GameFlow gameFlow = newGameFlow("1\nx\nx\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void runsNewGameWherePlayerNoughtWins() {
        GameFlow gameFlow = newGameFlow("1\nx\no\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player O won!"));
    }

    @Test
    public void runsSecondGame() {
        GameFlow gameFlow = newGameFlow("1\nx\nx\n1\n2\n5\n3\n9\ny\n1\no\no\n1\n2\n5\n3\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
        assertTrue(output.toString().contains("Player O won!"));
    }

    @Test
    public void runsGameAgainstSmartComputer() {
        UiDouble ui = new UiDouble(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        GameFlow gameFlow = new GameFlow(ui, grid, lines);

        gameFlow.runGame();

        String computerMark = "O";
        assertTrue(humanIsNotWinner(grid, lines, computerMark));
    }

    private GameFlow newGameFlow(String allInput) {
        Ui ui = newUiWith(allInput);
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        return new GameFlow(ui, grid, lines);
    }

    private Ui newUiWith(String inputString) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());

        return new Ui(new PrintStream(output), input);
    }

    private boolean humanIsNotWinner(Grid grid, Lines lines, String mark) {
        boolean isDraw = !lines.isWinning(grid);
        boolean computerWon = lines.winningMark(grid).equals(mark);
        return isDraw || computerWon;
    }
}
