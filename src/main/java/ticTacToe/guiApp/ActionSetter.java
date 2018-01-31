package ticTacToe.guiApp;

import ticTacToe.game.Mark;

public class ActionSetter {

    private final GuiGameFlow gameFlow;

    public ActionSetter(GuiGameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    public void addClickHandler(AppButton button, AppLabel label) {
        button.setOnAction(event -> {
            String buttonText = button.actualButton().getText();
            if (gameFlow.isNotGameOver() && gameFlow.isNotClickedButton(buttonText)) {
                Mark mark = gameFlow.move(buttonText);
                button.actualButton().setText(mark.sign);
                label.setText(gameFlow.messageForGameState());
            }
        });
    }
}
