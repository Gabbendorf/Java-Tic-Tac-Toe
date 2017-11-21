package ticTacToe.player;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.ui.DoubleUi;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PlayersFactoryTest {

    private DoubleUi doubleUi;
    private PlayersFactory playersFactory;
    private Grid grid;

    @Before
    public void classesInstantiations() {
        doubleUi = new DoubleUi(null, null);
        grid = new Grid(3);
        playersFactory = new PlayersFactory();
    }

    @Test
    public void createsPlayerWithChosenMark() {
        Player firstPlayer = playersFactory.firstPlayer(doubleUi);

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
