package ticTacToe.game;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;
import ticTacToe.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;

public class GameFlowTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
    }

    @Test
    public void runsNewGameThatIsDraw() {
        GameFlow gameFlow = newGameFlow("x\no\n1\n2\n3\n4\n6\n5\n8\n9\n7\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    @Test
    public void runsNewGameWherePlayerCrossWins() {
        GameFlow gameFlow = newGameFlow("x\nx\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void runsNewGameWherePlayerNoughtWins() {
        GameFlow gameFlow = newGameFlow("x\no\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player O won!"));
    }

    @Test
    public void runsSecondGame() {
        GameFlow gameFlow = newGameFlow("x\nx\n1\n2\n5\n3\n9\ny\no\no\n1\n2\n5\n3\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
        assertTrue(output.toString().contains("Player O won!"));
    }

    private GameFlow newGameFlow(String allInput) {
        Ui ui = newUiWith(allInput);
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        return new GameFlow(ui, grid, rows);
    }

    private Ui newUiWith(String inputString) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());

        return new Ui(new PrintStream(output), input);
    }
}
