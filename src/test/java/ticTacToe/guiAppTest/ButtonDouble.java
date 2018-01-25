package ticTacToe.guiAppTest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.guiApp.ButtonWrapper;

public class ButtonDouble implements ButtonWrapper {

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {

    }

    @Override
    public Button actualButton() {
        return null;
    }
}
