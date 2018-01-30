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

    private static Grid grid;
    private static Lines lines;
    private static AppLabel label;

    @BeforeClass
    public static void classInstantiations() {
        grid = new Grid(3);
        lines = new Lines();
        label = new LabelStub();

    }

    @Test
    public void setsActionOnButton() {
        ActionSetter actionSetter = new ActionSetter(new GuiGameFlow(grid, lines));
        ButtonSpy button = new ButtonSpy(grid, lines);

        actionSetter.addClickHandler(button, label);

        assertTrue(button.wasClicked);
    }

    @Test
    public void actionIsNotTriggeredIfGameIsOver() {
        Grid grid = winningGrid();
        ActionSetter actionSetter = new ActionSetter(new GuiGameFlow(grid, lines));
        ButtonSpy button = new ButtonSpy(grid, lines);

        actionSetter.addClickHandler(button, label);

        assertFalse(button.wasClicked);
    }

    private Grid winningGrid() {
        Grid grid = new Grid(3);
        grid.addMark(CROSS, "1");
        grid.addMark(CROSS, "4");
        grid.addMark(CROSS, "7");
        return grid;
    }
}
