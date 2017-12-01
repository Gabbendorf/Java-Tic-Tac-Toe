package ticTacToe.grid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridFactoryTest {

    @Test
    public void creates3x3Grid() {
        GridFactory gridFactory = new GridFactory();

        Grid newGrid = gridFactory.createGrid(3);

        assertEquals(3, newGrid.getSize());
    }

    @Test
    public void creates4x4Grid() {
        GridFactory gridFactory = new GridFactory();

        Grid newGrid = gridFactory.createGrid(4);

        assertEquals(4, newGrid.getSize());
    }
}
