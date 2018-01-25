package ticTacToe.guiApp;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;

public class ActionSetter {

    private final Grid grid;

    public ActionSetter(Grid grid) {
        this.grid = grid;
    }

    public void addClickHandler(ButtonWrapper button, String buttonNumber) {
        button.setOnAction(event -> {
            Mark currentMark = grid.markThatMoves();
            grid.addMark(currentMark, buttonNumber);
            button.actualButton().setText(currentMark.sign);
        });
    }
}
