package ticTacToe.player;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.ui.UiDouble;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PlayersFactoryTest {

    private UiDouble uiDouble;
    private PlayersFactory playersFactory;

    @Before
    public void classesInstantiations() {
        uiDouble = new UiDouble(null, null);
        playersFactory = new PlayersFactory();
    }

    @Test
    public void createsPlayerWithChosenMark() {
        Player firstPlayer = playersFactory.firstPlayer(uiDouble);

        assertEquals("X", firstPlayer.getMark());
    }

    @Test
    public void createsHumanPlayerWithOtherPossibleMark() {
        Player secondPlayer = playersFactory.secondPlayer("1", "X");

        assertTrue(secondPlayer instanceof HumanPlayer);
        assertEquals("O", secondPlayer.getMark());
    }

    @Test
    public void createsSmartComputerWithOtherPossibleMark() {
        Player secondPlayer = playersFactory.secondPlayer("2", "O");

        assertTrue(secondPlayer instanceof SmartComputer);
        assertEquals("X", secondPlayer.getMark());
    }
}
