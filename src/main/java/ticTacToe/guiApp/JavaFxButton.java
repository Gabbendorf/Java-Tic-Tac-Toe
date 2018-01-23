package ticTacToe.guiApp;

import javafx.scene.control.Button;

public class JavaFxButton {

    private final Button button;

    public JavaFxButton(Button button, String cellNumber) {
        this.button = button;
        this.button.setText(cellNumber);
    }

    public Button actualButton() {
        return button;
    }
}
