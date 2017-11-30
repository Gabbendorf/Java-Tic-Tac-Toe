package ticTacToe.player;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static ticTacToe.game.Mark.CROSS;

public class HumanPlayerTest {

    @Test
    public void returnsGridPosition() {
        Grid grid = new Grid(3);
        Lines lines = new Lines();
        Ui ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("1".getBytes()));
        HumanPlayer humanPlayer = new HumanPlayer(CROSS);

        String positionChosen = humanPlayer.makeMove(ui, grid, lines);

        assertEquals("1", positionChosen);
    }
}
