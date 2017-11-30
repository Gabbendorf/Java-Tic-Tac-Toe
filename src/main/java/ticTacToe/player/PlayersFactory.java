package ticTacToe.player;

import ticTacToe.game.Mark;
import ticTacToe.ui.Ui;

import java.util.HashMap;
import java.util.Map;

import static ticTacToe.game.Mark.*;

public class PlayersFactory {

    public Player firstPlayer(Ui ui) {
       return new HumanPlayer(ui.askForMarkType());
    }

    public Player secondPlayer(String opponentOptionNumber, Mark firstPlayerMark) {
        if (firstPlayerMark == CROSS) {
            return opponentOptions(NOUGHT).get(opponentOptionNumber);
        }
        return opponentOptions(CROSS).get(opponentOptionNumber);
    }

    private Map<String, Player> opponentOptions(Mark mark) {
        Map<String, Player> playersOptions = new HashMap<>();
        playersOptions.put("1", new HumanPlayer(mark));
        playersOptions.put("2", new SmartComputer(mark, new MoveGenerator()));
        return playersOptions;
    }
}
