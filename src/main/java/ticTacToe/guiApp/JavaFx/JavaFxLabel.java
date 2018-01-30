package ticTacToe.guiApp.JavaFx;

import javafx.scene.control.Label;
import ticTacToe.guiApp.AppLabel;

public class JavaFxLabel implements AppLabel {

    private Label label;

    public JavaFxLabel(Label label) {
        this.label = label;
    }

    @Override
    public void setText(String text) {
        label.setText(text);
    }
}
