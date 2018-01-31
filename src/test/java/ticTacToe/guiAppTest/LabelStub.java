package ticTacToe.guiAppTest;

import ticTacToe.guiApp.AppLabel;

public class LabelStub implements AppLabel {

    public String textToAdd;

    @Override
    public void setText(String text) {
        textToAdd = text;
    }
}
