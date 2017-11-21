package ticTacToe.player;

import ticTacToe.grid.Grid;
import ticTacToe.ui.Ui;

import java.util.HashMap;
import java.util.Map;

import static ticTacToe.game.Mark.*;

public class PlayersFactory {

    public Player firstPlayer(Ui ui) {
       return new HumanPlayer(ui.askForMarkType());
    }

    public Player secondPlayer(String opponentOptionNumber, String firstPlayerMark) {
        if (firstPlayerMark.equals(CROSS.mark)) {
            return opponentOptions(NOUGHT.mark).get(opponentOptionNumber);
        }
        return opponentOptions(CROSS.mark).get(opponentOptionNumber);
    }

    private Map<String, Player> opponentOptions(String mark) {
        Map<String, Player> playersOptions = new HashMap<>();
        playersOptions.put("1", new HumanPlayer(mark));
        playersOptions.put("2", new SmartComputer(mark, new MoveGenerator()));
        return playersOptions;
    }
}
