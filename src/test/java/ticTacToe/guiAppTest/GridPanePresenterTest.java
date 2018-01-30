package ticTacToe.guiAppTest;

import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.ButtonsCreator;
import ticTacToe.guiApp.GuiGameFlow;
import ticTacToe.guiApp.GridPanePresenter;


import static org.junit.Assert.assertEquals;

public class GridPanePresenterTest {

    @Test
    public void addsNineButtonsToGridPane() {
        GridPaneDouble gridPaneDouble = new GridPaneDouble();
        Grid grid = new Grid(3);
        GuiGameFlow flow = new GuiGameFlow(grid, new Lines());
        ButtonsCreator buttonsCreator = new ButtonsCreatorDouble(new ActionSetter(flow));
        GridPanePresenter gridPane = new GridPanePresenter(gridPaneDouble, grid);

        gridPane.addAllButtons(buttonsCreator);

        assertEquals(9, gridPaneDouble.buttonsAdded.size());
    }
}
