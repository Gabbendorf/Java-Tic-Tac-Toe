package ticTacToe.guiAppTest;

import org.junit.Before;
import org.junit.Test;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.*;

import static org.junit.Assert.assertEquals;

public class GridPanePresenterTest {

    private Grid grid;
    private AppLabel label;
    private GuiGameFlow guiGameFlow;
    private ButtonsCreator buttonsCreator;

    @Before
     public void setUp() {
        grid = new Grid(3);
        label = new LabelStub();
        guiGameFlow = new GuiGameFlow(grid, new Lines());
        buttonsCreator = new ButtonsCreatorDouble(new ActionSetter(guiGameFlow));
    }

    @Test
    public void addsNineButtonsToGridPane() {
        GridPaneDouble gridPane = new GridPaneDouble();
        GridPanePresenter gridPanePresenter = new GridPanePresenter(gridPane, grid);

        gridPanePresenter.addAllButtons(buttonsCreator, label);

        assertEquals(9, gridPane.buttonsAdded.size());
    }
}
