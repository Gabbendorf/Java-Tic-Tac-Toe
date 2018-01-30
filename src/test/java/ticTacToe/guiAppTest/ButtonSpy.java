package ticTacToe.guiAppTest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;
import ticTacToe.guiApp.AppButton;

public class ButtonSpy implements AppButton {

    public boolean wasClicked;

    private Grid grid;
    private Lines lines;
    private String text;

    public ButtonSpy(Grid grid, Lines lines, String text) {
        this.grid = grid;
        this.lines = lines;
        this.text = text;
    }

    @Override
    public void setOnAction(EventHandler<ActionEvent> event) {
        if (!grid.isFinishedGame(lines) && !Mark.isValid(text)) {
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
