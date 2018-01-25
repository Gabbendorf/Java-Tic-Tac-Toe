package ticTacToe.guiAppTest;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.ButtonWrapper;
import ticTacToe.guiApp.GuiGridOnPane;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GuiGridOnPaneTest {

    private List<ButtonWrapper> buttonsCreated;

    @Test
    public void addsNineButtonsToGridPane() {
        GridPaneDouble gridPaneDouble = new GridPaneDouble();
        GuiGridOnPane gridOnPane = newGuiGridOnPane(gridPaneDouble);

        gridOnPane.addAll(buttonsCreated);

        assertEquals(9, gridPaneDouble.buttonsAdded.size());
    }

    private GuiGridOnPane newGuiGridOnPane(GridPaneDouble gridPaneDouble) {
        Grid grid = new Grid(3);
        ButtonsCreatorDouble buttonsCreator = new ButtonsCreatorDouble(new ActionSetter(grid));
        buttonsCreated = buttonsCreator.createButtons(grid);
        return new GuiGridOnPane(gridPaneDouble, grid);
    }
}
