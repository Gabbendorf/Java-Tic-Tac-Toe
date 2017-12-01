package ticTacToe.player;

import ticTacToe.game.GameOption;
import ticTacToe.game.Mark;
import ticTacToe.ui.Ui;

import static ticTacToe.game.GameOption.COMPUTER_VS_COMPUTER;
import static ticTacToe.game.GameOption.HUMAN_VS_HUMAN;

public class PlayersFactory {

    public Player firstPlayer(Ui ui, GameOption gameOptionChosen) {
        if (gameOptionChosen == COMPUTER_VS_COMPUTER) {
            return new SmartComputer(Mark.random(), new MoveGenerator());
        }
        return new HumanPlayer(ui.askForMarkType());
    }

    public Player secondPlayer(GameOption gameOptionChosen, Mark firstPlayerMark) {
        if (gameOptionChosen == HUMAN_VS_HUMAN) {
            return new HumanPlayer(firstPlayerMark.doSwitch());
        }
        return new SmartComputer(firstPlayerMark.doSwitch(), new MoveGenerator());
    }
}
