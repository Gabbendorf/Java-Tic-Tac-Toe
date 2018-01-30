package ticTacToe.guiApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import ticTacToe.grid.Grid;


public class GridPanePresenter {

    private final AppGridPane gridPane;
    private final Grid grid;

    public GridPanePresenter(AppGridPane gridPane, Grid grid) {
        this.gridPane = gridPane;
        this.grid = grid;
    }

    public void gridPaneSetUp(ButtonsCreator buttonsCreator, AppLabel label) {
        addAllButtons(buttonsCreator, label);
        gridPane.actualGridPane().setAlignment(Pos.CENTER);
        gridPane.actualGridPane().setPadding(new Insets(25, 25, 25, 25));
        gridPane.actualGridPane().setHgap(10);
        gridPane.actualGridPane().setVgap(10);
    }

    public void addAllButtons(ButtonsCreator buttonsCreator, AppLabel label) {
        int columnIndex = 1;
        int rowIndex = 0;
        int buttonCounter = 1;
        for (AppButton button : buttonsCreator.buttons(grid, label)) {
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
