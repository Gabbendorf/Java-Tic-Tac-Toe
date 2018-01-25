package ticTacToe.guiAppTest;

import javafx.scene.layout.GridPane;
import ticTacToe.guiApp.ButtonWrapper;
import ticTacToe.guiApp.GridPaneWrapper;

import java.util.ArrayList;
import java.util.List;

public class GridPaneDouble implements GridPaneWrapper {

    List<ButtonWrapper> buttonsAdded = new ArrayList<>();

    @Override
    public void addButton(ButtonWrapper button, int columnIndex, int rowIndex) {
        buttonsAdded.add(button);
    }

    @Override
    public GridPane actualGridPane() {
        return null;
    }
}
