package ticTacToe.ui;

import ticTacToe.grid.Grid;
import ticTacToe.game.Lines;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class UiDouble extends Ui {

    public UiDouble(PrintStream output, InputStream input) {
        super(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
    }

    @Override
    public String validPosition(Grid grid, String mark, Lines lines) {
        return "1";
    }

    @Override
    public String askForMarkType() {
        return "X";
    }
}

