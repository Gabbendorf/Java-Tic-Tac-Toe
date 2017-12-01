package ticTacToe.grid;

public class GridFactory {

    public Grid createGrid(int userInput) {
        return new Grid(userInput);
    }
}
