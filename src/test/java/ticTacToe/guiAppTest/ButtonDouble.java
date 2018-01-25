package ticTacToe.guiAppTest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.guiApp.ButtonWrapper;

public class ButtonDouble implements ButtonWrapper {

    public boolean wasCalled;

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {
        wasCalled = true;
    }

    @Override
    public Button actualButton() {
        return null;
    }
}
