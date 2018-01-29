package ticTacToe.guiApp;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;

public class ActionSetter {

    private final Grid grid;

    public ActionSetter(Grid grid) {
        this.grid = grid;
    }

    public void addClickHandler(ButtonWrapper button) {
        button.setOnAction(event -> {
            Mark currentMark = grid.nextMark();
            grid.addMark(currentMark, button.actualButton().getText());
            button.actualButton().setText(currentMark.sign);
        });
    }
}
