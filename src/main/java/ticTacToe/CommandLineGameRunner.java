package ticTacToe;

import ticTacToe.game.GameFlow;
import ticTacToe.grid.Lines;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;

public class CommandLineGameRunner {

    public static void main(String[] args) {
        Ui ui = new Ui(System.out, System.in);
        Lines lines = new Lines();
        PlayersFactory playersFactory = new PlayersFactory();
        GameFlow gameFlow = new GameFlow(ui, lines, playersFactory);

        gameFlow.runGame();
    }
}
