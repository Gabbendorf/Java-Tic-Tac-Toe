package ticTacToe.game;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Lines;
import ticTacToe.player.PlayersFactory;
import ticTacToe.player.PlayersFactoryDouble;
import ticTacToe.ui.Ui;
import ticTacToe.ui.UiDouble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;

public class GameFlowTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
    }

    @Test
    public void runsNewGameThatIsDraw() {
        GameFlow gameFlow = newGameFlow("3\n1\nX\no\n1\n2\n3\n4\n6\n5\n8\n9\n7\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    @Test
    public void runsNewGameWherePlayerCrossWins() {
        GameFlow gameFlow = newGameFlow("3\n1\nx\nx\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
    }

    @Test
    public void runsNewGameWherePlayerNoughtWins() {
        GameFlow gameFlow = newGameFlow("3\n1\nx\no\n1\n2\n3\n4\n5\n6\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player O won!"));
    }

    @Test
    public void runsSecondGame() {
        GameFlow gameFlow = newGameFlow("3\n1\nx\nx\n1\n2\n5\n3\n9\ny\n3\n1\no\no\n1\n2\n5\n3\n9\nn");

        gameFlow.runGame();

        assertTrue(output.toString().contains("Player X won!"));
        assertTrue(output.toString().contains("Player O won!"));
    }

    @Test
    public void runsGameHumanPlayerAgainstSmartComputerOn3x3Grid() {
        UiDouble ui = new UiDouble(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Lines lines = new Lines();
        PlayersFactory playersFactory = new PlayersFactory();
        GameFlow gameFlow = new GameFlow(ui, lines, playersFactory);

        gameFlow.runGame();

        String humanPlayerMark = CROSS.sign;
        String message = String.format("Player %s won!", humanPlayerMark);
        assertFalse(output.toString().contains(message));
    }

    @Test
    public void runsGameHumanPlayerAgainstSmartComputerOn4x4Grid() {
        UiDouble ui = new UiDouble(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        Lines lines = new Lines();
        PlayersFactory playersFactory = new PlayersFactory();
        GameFlow gameFlow = new GameFlow(ui, lines, playersFactory);

        gameFlow.runGame();

        String humanPlayerMark = CROSS.sign;
        String message = String.format("Player %s won!", humanPlayerMark);
        assertFalse(output.toString().contains(message));
    }

    @Test
    public void runsGameComputerAgainstComputerOn3x3Grid() {
        Ui ui = new Ui (new PrintStream(output), new ByteArrayInputStream("3\n3\nx\nn".getBytes()));
        Lines lines = new Lines();
        PlayersFactory playersFactoryDouble = new PlayersFactoryDouble();
        GameFlow gameFlow = new GameFlow(ui, lines, playersFactoryDouble);

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    @Test
    public void runsGameComputerAgainstComputerOn4x4Grid() {
        Ui ui = new Ui (new PrintStream(output), new ByteArrayInputStream("4\n3\nx\nn".getBytes()));
        Lines lines = new Lines();
        PlayersFactory playersFactoryDouble = new PlayersFactoryDouble();
        GameFlow gameFlow = new GameFlow(ui, lines, playersFactoryDouble);

        gameFlow.runGame();

        assertTrue(output.toString().contains("It's draw: nobody wins!"));
    }

    private GameFlow newGameFlow(String allInput) {
        Ui ui = newUiWith(allInput);
        Lines lines = new Lines();
        PlayersFactory playersFactory = new PlayersFactory();
        return new GameFlow(ui, lines, playersFactory);
    }

    private Ui newUiWith(String inputString) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());

        return new Ui(new PrintStream(output), input);
    }
}
