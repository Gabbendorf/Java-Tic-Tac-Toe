package ticTacToe.guiApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import ticTacToe.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class GuiGridOnPane {

    private final GridPaneWrapper gridPane;
    private final Grid grid;
    private ArrayList<ButtonWrapper> allButtons = new ArrayList<>();

    public GuiGridOnPane(GridPaneWrapper gridPane, Grid grid) {
        this.gridPane = gridPane;
        this.grid = grid;
    }

    public ArrayList<ButtonWrapper> getAllButtons() {
        return allButtons;
    }

    public void gridPaneSetUp() {
        gridPane.actualGridPane().setAlignment(Pos.CENTER);
        gridPane.actualGridPane().setPadding(new Insets(25, 25, 25, 25));
        gridPane.actualGridPane().setHgap(10);
        gridPane.actualGridPane().setVgap(10);
    }

    public void addAll(List<ButtonWrapper> buttons) {
        int columnIndex = 1;
        int rowIndex = 0;
        int buttonCounter = 1;
        for (ButtonWrapper button : buttons) {
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
