package ticTacToe.guiAppTest;

import ticTacToe.grid.Grid;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.ButtonWrapper;
import ticTacToe.guiApp.ButtonsCreator;

import java.util.ArrayList;
import java.util.List;

public class ButtonsCreatorDouble extends ButtonsCreator {

    public ButtonsCreatorDouble(ActionSetter actionSetter) {
        super(actionSetter);
    }

    public List<ButtonWrapper> createButtons(Grid grid) {
        List<ButtonWrapper> allButtons = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            allButtons.add(new ButtonDouble());
        }
        return allButtons;
    }
}
