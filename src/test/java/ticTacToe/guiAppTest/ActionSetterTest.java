package ticTacToe.guiAppTest;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.ActionSetter;

import static org.junit.Assert.assertTrue;

public class ActionSetterTest {

    @Test
    public void setsActionOnButton() {
        ActionSetter actionSetter = new ActionSetter(new Grid(3));
        ButtonSpy button = new ButtonSpy();

        actionSetter.addClickHandler(button);

        assertTrue(button.wasClicked);
    }
}
