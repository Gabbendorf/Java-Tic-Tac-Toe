package ticTacToe.game;

public enum Mark {

    NOUGHT("O"), CROSS("X");

    Mark(String name) {
        mark = name;
   }

    public String mark;
}
