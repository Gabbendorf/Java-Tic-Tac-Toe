package ticTacToe.guiApp.JavaFx;

import javafx.scene.layout.GridPane;
import ticTacToe.guiApp.ButtonWrapper;
import ticTacToe.guiApp.GridPaneWrapper;

public class JavaFxGridPane implements GridPaneWrapper {

    private final GridPane gridPane;

    public JavaFxGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    @Override
    public void addButton(ButtonWrapper button, int columnIndex, int rowIndex) {
        gridPane.add(button.actualButton(), columnIndex, rowIndex);
    }

    public GridPane actualGridPane() {
        return gridPane;
    }
}
