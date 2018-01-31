package ticTacToe.guiAppTest;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.AppButton;
import ticTacToe.guiApp.AppLabel;
import ticTacToe.guiApp.ButtonsCreator;

import java.util.ArrayList;
import java.util.List;

public class ButtonsCreatorDouble extends ButtonsCreator {

    public ButtonsCreatorDouble(ActionSetter actionSetter) {
        super(actionSetter);
    }

    @Override
    public List<AppButton> buttons(Grid grid, AppLabel label) {
        List<AppButton> allButtons = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            allButtons.add(new ButtonSpy(grid, new Lines(), null));
        }
        return allButtons;
    }
}
