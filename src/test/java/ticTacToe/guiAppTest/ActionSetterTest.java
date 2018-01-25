package ticTacToe.guiAppTest;

import org.junit.Test;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.ActionSetter;

import static org.junit.Assert.assertTrue;

public class ActionSetterTest {

    @Test
    public void setsActionOnButton() {
        ActionSetter actionSetter = new ActionSetter(new Grid(3));
        ButtonDouble button = new ButtonDouble();

        actionSetter.addClickHandler(button, Mark.CROSS, "1");

        assertTrue(button.wasCalled);
    }
}
