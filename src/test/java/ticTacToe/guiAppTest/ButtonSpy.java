package ticTacToe.guiAppTest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.AppButton;

public class ButtonSpy implements AppButton {

    public boolean wasClicked;

    private Grid grid;
    private Lines lines;

    public ButtonSpy(Grid grid, Lines lines) {
        this.grid = grid;
        this.lines = lines;
    }

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {
        if (!grid.isFinishedGame(lines)) {
            wasClicked = true;
        } else {
            wasClicked = false;
        }
    }

    @Override
    public Button actualButton() {
        return null;
    }
}
