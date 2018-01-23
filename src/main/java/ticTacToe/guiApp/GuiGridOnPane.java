package ticTacToe.guiApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ticTacToe.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class GuiGridOnPane {

    private final GridPane gridPane;
    private final Grid grid;

    public GuiGridOnPane(GridPane gridPane, int gridSize) {
        this.gridPane = gridPane;
        this.grid = new Grid(gridSize);
    }

    public List<JavaFxButton> buttons() {
        List<JavaFxButton> allButtons = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            allButtons.add(new JavaFxButton(new Button(), cellNumber));
        }
        return allButtons;
    }

    public void gridPaneSetUp() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }

    public void addButtonsToPane() {
        int columnIndex = 1;
        int rowIndex = 0;
        int buttonCounter = 1;
        for (JavaFxButton button : buttons()) {
            gridPane.add(button.actualButton(), columnIndex, rowIndex);
            columnIndex = updateColumnIndex(columnIndex);
            buttonCounter += 1;
            if (buttonCounter > 3) {
                rowIndex += 1;
                buttonCounter = 1;
            }
        }
    }

    public GridPane actualGridPane() {
        return gridPane;
    }

    private int updateColumnIndex(int columnIndex) {
        if (columnIndex == 3) {
            return 1;
        }
        return columnIndex + 1;
    }
}
