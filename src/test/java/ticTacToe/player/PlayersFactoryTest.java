package ticTacToe.player;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.ui.UiDouble;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static ticTacToe.game.Mark.CROSS;
import static ticTacToe.game.Mark.NOUGHT;

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

        assertEquals(CROSS, firstPlayer.getMark());
    }

    @Test
    public void createsHumanPlayerWithOtherPossibleMark() {
        Player secondPlayer = playersFactory.secondPlayer("1", CROSS);

        assertTrue(secondPlayer instanceof HumanPlayer);
        assertEquals(NOUGHT, secondPlayer.getMark());
    }

    @Test
    public void createsSmartComputerWithOtherPossibleMark() {
        Player secondPlayer = playersFactory.secondPlayer("2", NOUGHT);

        assertTrue(secondPlayer instanceof SmartComputer);
        assertEquals(CROSS, secondPlayer.getMark());
    }
}
