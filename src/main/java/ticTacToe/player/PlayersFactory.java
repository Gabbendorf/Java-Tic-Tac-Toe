package ticTacToe.player;

import ticTacToe.ui.Ui;

import static ticTacToe.grid.Mark.*;

public class PlayersFactory {

    public Player firstPlayer(Ui ui) {
       return new HumanPlayer(ui.askForMarkType());
    }

    public Player secondPlayer(String markChosen) {
       if (markChosen.equals(CROSS.mark)) {
           return new HumanPlayer(NOUGHT.mark);
       } else {
           return new HumanPlayer(CROSS.mark);
       }
    }
}
