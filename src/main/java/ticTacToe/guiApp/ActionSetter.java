package ticTacToe.guiApp;

import ticTacToe.game.Mark;

public class ActionSetter {

    private final GuiGameFlow gameFlow;

    public ActionSetter(GuiGameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    public void addClickHandler(AppButton button, AppLabel label) {
        button.setOnAction(event -> {
            if (gameFlow.isNotGameOver()) {
                Mark mark = gameFlow.move(button.actualButton().getText());
                button.actualButton().setText(mark.sign);
                label.setText(gameFlow.messageForGameState());
            }
        });
    }
}
