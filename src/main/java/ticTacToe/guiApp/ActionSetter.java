package ticTacToe.guiApp;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;

public class ActionSetter {

    private final Grid grid;

    public ActionSetter(Grid grid) {
        this.grid = grid;
    }

    public void addClickHandler(ButtonWrapper button, Mark mark, String buttonNumber) {
        button.setOnAction(event -> {
            grid.addMark(mark, buttonNumber);
            button.actualButton().setText(mark.sign);
        });
    }
}
