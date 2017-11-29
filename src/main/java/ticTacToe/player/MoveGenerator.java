package ticTacToe.player;

import ticTacToe.grid.Grid;

import java.util.List;
import java.util.Random;

public class MoveGenerator {

    public String getRandomMove(Grid grid) {
        List<String> emptyPositions = grid.emptyPositions();
        return emptyPositions.get(new Random().nextInt(emptyPositions.size()));
    }
}
