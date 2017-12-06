package ticTacToe;

import ticTacToe.game.GameFlow;
import ticTacToe.player.PlayersFactory;
import ticTacToe.ui.Ui;

public class GameRunner {

    public static void main(String[] args) {
        Ui ui = new Ui(System.out, System.in);
        PlayersFactory playersFactory = new PlayersFactory();
        GameFlow gameFlow = new GameFlow(ui, playersFactory);

        gameFlow.runGame();
    }
}
