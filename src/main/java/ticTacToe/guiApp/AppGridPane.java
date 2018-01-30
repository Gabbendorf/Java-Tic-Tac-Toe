package ticTacToe.guiApp;

import javafx.scene.layout.GridPane;

public interface AppGridPane {

    void addButton(AppButton button, int columnIndex, int rowIndex);
    GridPane actualGridPane();
}
