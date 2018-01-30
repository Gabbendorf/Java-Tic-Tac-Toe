package ticTacToe.guiAppTest;

import javafx.scene.layout.GridPane;
import ticTacToe.guiApp.AppButton;
import ticTacToe.guiApp.AppGridPane;

import java.util.ArrayList;
import java.util.List;

public class GridPaneDouble implements AppGridPane {

    List<AppButton> buttonsAdded = new ArrayList<>();

    @Override
    public void addButton(AppButton button, int columnIndex, int rowIndex) {
        buttonsAdded.add(button);
    }

    @Override
    public GridPane actualGridPane() {
        return null;
    }
}
