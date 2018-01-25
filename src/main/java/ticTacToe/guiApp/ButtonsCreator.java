package ticTacToe.guiApp;

import javafx.scene.control.Button;
import ticTacToe.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class ButtonsCreator {

    public List<ButtonWrapper> createButtons(Grid grid) {
        List<ButtonWrapper> allButtons = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            allButtons.add(new JavaFxButton(new Button(), cellNumber));
        }
        return allButtons;
    }
}
