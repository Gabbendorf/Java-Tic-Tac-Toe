package ticTacToe.ui;

import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.grid.Lines;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import static ticTacToe.game.Mark.CROSS;

public class UiDouble extends Ui {

    public UiDouble(PrintStream output, InputStream input) {
        super(new PrintStream(new ByteArrayOutputStream()), new ByteArrayInputStream("2\nx\nx\nn".getBytes()));
    }

    @Override
    public String validPosition(Grid grid, Mark mark, Lines lines) {
        List<String> emptyPositions = grid.emptyPositions();
        return emptyPositions.get(new Random().nextInt(emptyPositions.size()));
    }

    @Override
    public Mark askForMarkType() {
        return CROSS;
    }
}

