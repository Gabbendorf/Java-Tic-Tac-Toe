package ticTacToe.guiApp.JavaFx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.guiApp.ButtonWrapper;

public class JavaFxButton implements ButtonWrapper {

    private final Button button;

    public JavaFxButton(Button button, String cellNumber) {
        this.button = button;
        this.button.setText(cellNumber);
    }

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {
        button.setOnAction(event);
    }

    public Button actualButton() {
        return button;
    }
}
