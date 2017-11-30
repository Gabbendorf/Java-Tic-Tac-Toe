package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.ui.Ui;

public class PlayersFactory {

    public Player firstPlayer(Ui ui, String gameTypeOption) {
        if (gameTypeOption.equals("3")) {
            return new SmartComputer(Mark.random(), new MoveGenerator());
        }
        return new HumanPlayer(ui.askForMarkType());
    }

    public Player secondPlayer(String gameTypeOption, Mark firstPlayerMark) {
        if (gameTypeOption.equals("1")) {
            return new HumanPlayer(firstPlayerMark.doSwitch());
        }
        return new SmartComputer(firstPlayerMark.doSwitch(), new MoveGenerator());
    }
}
