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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameFlowTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
    }

    @Test
    public void runsNewGameThatIsDraw() {
        Ui ui = newUiWith("x\no\n1\n2\n3\n4\n6\n5\n8\n9\n7");
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        GameFlow gameFlow = new GameFlow(ui, grid, rows);

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
        assertFalse(rows.isWinning(grid.getSize()));
    }

    @Test
    public void runsNewGameWherePlayerCrossWins() {
        Ui ui = newUiWith("x\nx\n1\n2\n3\n4\n5\n6\n9");
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        GameFlow gameFlow = new GameFlow(ui, grid, rows);

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
        assertEquals("X", rows.winningMark(grid.getSize()));
    }

    @Test
    public void runsNewGameWherePlayerNoughtWins() {
        Ui ui = newUiWith("x\no\n1\n2\n3\n4\n5\n6\n9");
        Grid grid = new Grid(3);
        Rows rows = new Rows(grid);
        GameFlow gameFlow = new GameFlow(ui, grid, rows);

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player O won!"));
        assertEquals("O", rows.winningMark(grid.getSize()));
    }

    private Ui newUiWith(String inputString) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());

        return new Ui(new PrintStream(output), input);
    }
}
