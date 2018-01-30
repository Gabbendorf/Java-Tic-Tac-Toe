package ticTacToe.guiAppTest;

import org.junit.BeforeClass;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.AppLabel;
import ticTacToe.guiApp.GuiGameFlow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ticTacToe.game.Mark.CROSS;

public class ActionSetterTest {

    private static Lines lines;
    private static AppLabel label;
    private static Grid emptyGrid;
    private ActionSetter actionSetter;

    @BeforeClass
    public static void classInstantiations() {
        emptyGrid = new Grid(3);
        lines = new Lines();
        label = new LabelStub();
    }

    @Test
    public void setsActionOnButtonIfGameIsNotOverAndButtonIsNotClicked() {
        ButtonSpy button = newButtonSpy("1", emptyGrid);

        actionSetter.addClickHandler(button, label);

        assertTrue(button.wasClicked);
    }

    @Test
    public void actionIsNotTriggeredIfGameIsOver() {
        ButtonSpy button = newButtonSpy("1", winningGrid());

        actionSetter.addClickHandler(button, label);

        assertFalse(button.wasClicked);
    }

    @Test
    public void actionIsNotTriggeredIfButtonWasAlreadyClicked() {
        ButtonSpy button = newButtonSpy("X", emptyGrid);

        actionSetter.addClickHandler(button, label);

        assertFalse(button.wasClicked);
    }

    private ButtonSpy newButtonSpy(String text, Grid grid) {
        actionSetter = new ActionSetter(new GuiGameFlow(grid, lines));
        return new ButtonSpy(grid, lines, text);
    }

    private Grid winningGrid() {
        Grid grid = new Grid(3);
        grid.addMark(CROSS, "1");
        grid.addMark(CROSS, "4");
        grid.addMark(CROSS, "7");
        return grid;
    }
}
