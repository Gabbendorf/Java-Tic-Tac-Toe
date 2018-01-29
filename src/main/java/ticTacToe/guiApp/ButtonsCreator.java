package ticTacToe.guiApp;

import javafx.scene.control.Button;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.JavaFx.JavaFxButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonsCreator {

    private final ActionSetter actionSetter;
    private List<ButtonWrapper> buttonsCreated;

    public ButtonsCreator(ActionSetter actionSetter) {
        this.actionSetter = actionSetter;
    }

    public List<ButtonWrapper> createButtons(Grid grid) {
        buttonsCreated = new ArrayList<>();
        for (String cellNumber : grid.getCells()) {
            ButtonWrapper button = new JavaFxButton(new Button(), cellNumber);
            actionSetter.addClickHandler(button);
            buttonsCreated.add(button);
        }
        return buttonsCreated;
    }
}
