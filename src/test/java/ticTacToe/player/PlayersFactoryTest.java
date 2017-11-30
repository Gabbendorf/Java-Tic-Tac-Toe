package ticTacToe.player;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.ui.Ui;
import ticTacToe.ui.UiDouble;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

public class PlayersFactoryTest {

    private UiDouble uiDouble;
    private PlayersFactory playersFactory;
    private Ui ui;

    @Before
    public void classesInstantiations() {
        ui = new Ui(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
        uiDouble = new UiDouble(null, null);
        playersFactory = new PlayersFactory();
    }

    @Test
    public void createsHumanAsFirstPlayerWithChosenMarkForGameType1() {
        Player firstPlayer = playersFactory.firstPlayer(uiDouble, "1");

        assertTrue(firstPlayer instanceof HumanPlayer);
        assertEquals(CROSS, firstPlayer.getMark());
    }

    @Test
    public void createsHumanPlayerAsFirstPlayerForGameType2() {
        Player firstPlayer = playersFactory.firstPlayer(uiDouble, "2");

        assertTrue(firstPlayer instanceof HumanPlayer);
        assertEquals(CROSS, firstPlayer.getMark());
    }

    @Test
    public void createsComputerAsFirstPlayerForGameType3() {
        Player firstPlayer = playersFactory.firstPlayer(ui, "3");

        assertTrue(firstPlayer instanceof SmartComputer);
    }

    @Test
    public void createsHumanPlayerAsSecondPlayerWithOtherPossibleMarkForGameType1() {
        Player secondPlayer = playersFactory.secondPlayer("1", CROSS);

        assertTrue(secondPlayer instanceof HumanPlayer);
        assertEquals(NOUGHT, secondPlayer.getMark());
    }

    @Test
    public void createsSmartComputerAsSecondPlayerWithOtherPossibleMarkForGameType2() {
        Player secondPlayer = playersFactory.secondPlayer("2", NOUGHT);

        assertTrue(secondPlayer instanceof SmartComputer);
        assertEquals(CROSS, secondPlayer.getMark());
    }

    @Test
    public void createsSmartComputerAsSecondPlayerWithOtherPossibleMarkForGameType3() {
        Player secondPlayer = playersFactory.secondPlayer("3", CROSS);

        assertTrue(secondPlayer instanceof SmartComputer);
        assertEquals(NOUGHT, secondPlayer.getMark());
    }
}
