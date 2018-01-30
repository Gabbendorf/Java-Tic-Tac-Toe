package ticTacToe.guiApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface AppButton {

    void setOnAction(EventHandler<ActionEvent> event);
    Button actualButton();
}
