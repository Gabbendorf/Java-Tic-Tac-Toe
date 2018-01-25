package ticTacToe.guiApp;

import javafx.scene.layout.GridPane;

public interface GridPaneWrapper {

    void addButton(ButtonWrapper button, int columnIndex, int rowIndex);
    GridPane actualGridPane();
}
