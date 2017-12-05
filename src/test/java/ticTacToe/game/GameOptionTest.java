package ticTacToe.game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.GameOption.COMPUTER_VS_COMPUTER;
import static ticTacToe.game.GameOption.HUMAN_VS_COMPUTER;
import static ticTacToe.game.GameOption.HUMAN_VS_HUMAN;

public class GameOptionTest {

    @Test
    public void returnsTrueForValidInput() {
        assertTrue(GameOption.isValid(3));
    }

    @Test
    public void returnsFalseForInvalidInput() {
        assertFalse(GameOption.isValid(5));
    }

    @Test
    public void returnsHumanVsHumanGameOption() {
        assertTrue(GameOption.create(1) == HUMAN_VS_HUMAN);
    }

    @Test
    public void returnsHumanVsComputerGameOption() {
        assertTrue(GameOption.create(2) == HUMAN_VS_COMPUTER);
    }

    @Test
    public void returnsComputerVsComputerGameOption() {
        assertTrue(GameOption.create(3) == COMPUTER_VS_COMPUTER);
    }
}
