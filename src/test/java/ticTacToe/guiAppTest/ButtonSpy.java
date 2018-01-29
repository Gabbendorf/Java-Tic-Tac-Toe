package ticTacToe.guiAppTest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.guiApp.ButtonWrapper;

public class ButtonSpy implements ButtonWrapper {

    public boolean wasClicked;

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {
        wasClicked = true;
    }

    @Override
    public Button actualButton() {
        return null;
    }
}
