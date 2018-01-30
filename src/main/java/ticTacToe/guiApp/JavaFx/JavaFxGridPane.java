package ticTacToe.guiApp.JavaFx;

import javafx.scene.layout.GridPane;
import ticTacToe.guiApp.AppButton;
import ticTacToe.guiApp.AppGridPane;

public class JavaFxGridPane implements AppGridPane {

    private final GridPane gridPane;

    public JavaFxGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    @Override
    public void addButton(AppButton button, int columnIndex, int rowIndex) {
        gridPane.add(button.actualButton(), columnIndex, rowIndex);
    }

    public GridPane actualGridPane() {
        return gridPane;
    }
}
