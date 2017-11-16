package ticTacToe.player;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.ui.DoubleUi;

import static org.junit.Assert.assertEquals;

public class PlayersFactoryTest {

    private DoubleUi doubleUi;
    private PlayersFactory playersFactory;

    @Before
    public void classesInstantiations() {
        doubleUi = new DoubleUi(null, null);
        playersFactory = new PlayersFactory();
    }

    @Test
    public void createsPlayerWithChosenMark() {
        Player firstPlayer = playersFactory.firstPlayer(doubleUi);

        assertEquals("X", firstPlayer.getMark());
    }

    @Test
    public void createsPlayerWithOtherPossibleMark() {
        Player secondPlayer = playersFactory.secondPlayer("X");

        assertEquals("O", secondPlayer.getMark());
    }
}
