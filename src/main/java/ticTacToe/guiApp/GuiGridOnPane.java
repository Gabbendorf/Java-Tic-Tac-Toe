package ticTacToe.guiApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ticTacToe.grid.Grid;

import java.util.ArrayList;

public class GuiGridOnPane {

    private final GridPane gridPane;
    private final Grid grid;
    private ArrayList<JavaFxButton> allButtons;

    public GuiGridOnPane(GridPane gridPane, Grid grid) {
        this.gridPane = gridPane;
        this.grid = grid;
    }

    public ArrayList<JavaFxButton> getAllButtons() {
        return allButtons;
    }

    public void gridPaneSetUp() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }

    public void addButtonsToPane() {
        createButtons();
        int columnIndex = 1;
        int rowIndex = 0;
        int buttonCounter = 1;
        for (JavaFxButton button : getAllButtons()) {
            gridPane.add(button.actualButton(), columnIndex, rowIndex);
            columnIndex = updateColumnIndex(columnIndex);
            buttonCounter += 1;
            if (buttonCounter > grid.getSize()) {
                rowIndex += 1;
                buttonCounter = 1;
            }
        }
    }

    public GridPane actualGridPane() {
        return gridPane;
    }

    private void createButtons() {
        allButtons = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            allButtons.add(new JavaFxButton(new Button(), cellNumber));
        }
    }

    private int updateColumnIndex(int columnIndex) {
        if (columnIndex == grid.getSize()) {
            return 1;
        }
        return columnIndex + 1;
    }
}
