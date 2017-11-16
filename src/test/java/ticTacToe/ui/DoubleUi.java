package ticTacToe.ui;

import ticTacToe.grid.Grid;
import ticTacToe.grid.Rows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class DoubleUi extends Ui {

    public DoubleUi(PrintStream output, InputStream input) {
        super(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("".getBytes()));
    }

    @Override
    public String validPosition(Grid grid, String mark, Rows rows, int gridSize) {
        return "1";
    }

    @Override
    public String askForMarkType() {
        return "X";
    }
}

