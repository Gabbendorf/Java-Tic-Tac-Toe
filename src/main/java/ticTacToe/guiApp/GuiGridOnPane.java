package ticTacToe.guiApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import ticTacToe.grid.Grid;

public class GuiGridOnPane {

    private final GridPaneWrapper gridPane;
    private final Grid grid;

    public GuiGridOnPane(GridPaneWrapper gridPane, Grid grid) {
        this.gridPane = gridPane;
        this.grid = grid;
    }

    public void gridPaneSetUp(ButtonsCreator buttonsCreator) {
        addAllButtons(buttonsCreator);
        gridPane.actualGridPane().setAlignment(Pos.CENTER);
        gridPane.actualGridPane().setPadding(new Insets(25, 25, 25, 25));
        gridPane.actualGridPane().setHgap(10);
        gridPane.actualGridPane().setVgap(10);
    }

    public void addAllButtons(ButtonsCreator buttonsCreator) {
        int columnIndex = 1;
        int rowIndex = 0;
        int buttonCounter = 1;
        for (ButtonWrapper button : buttonsCreator.buttons(grid)) {
            gridPane.addButton(button, columnIndex, rowIndex);
            columnIndex = updateColumnIndex(columnIndex);
            buttonCounter += 1;
            if (buttonCounter > grid.getSize()) {
                rowIndex += 1;
                buttonCounter = 1;
            }
        }
    }

    private int updateColumnIndex(int columnIndex) {
        if (columnIndex == grid.getSize()) {
            return 1;
        }
        return columnIndex + 1;
    }
}
