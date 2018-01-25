package ticTacToe.guiApp;

import javafx.scene.control.Button;
import ticTacToe.grid.Grid;

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
            buttonsCreated.add(new JavaFxButton(new Button(), cellNumber));
        }
        setActionToButtons();
        return buttonsCreated;
    }

    private void setActionToButtons() {
        for (ButtonWrapper button : buttonsCreated) {
            actionSetter.addClickHandler(button, button.actualButton().getText());
        }
    }
}
