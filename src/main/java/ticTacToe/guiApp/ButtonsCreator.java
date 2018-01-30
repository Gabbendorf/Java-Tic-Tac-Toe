package ticTacToe.guiApp;

import javafx.scene.control.Button;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.JavaFx.JavaFxButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonsCreator {

    private final ActionSetter actionSetter;

    public ButtonsCreator(ActionSetter actionSetter) {
        this.actionSetter = actionSetter;
    }

    public List<AppButton> buttons(Grid grid) {
        List<AppButton> buttonsCreated = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            AppButton button = new JavaFxButton(new Button(), cellNumber);
            actionSetter.addClickHandler(button);
            buttonsCreated.add(button);
        }
        return buttonsCreated;
    }
}
