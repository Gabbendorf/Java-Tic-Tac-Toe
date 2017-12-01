package ticTacToe.game;

public enum GameOption {

    HUMAN_VS_HUMAN(1),
    HUMAN_VS_COMPUTER(2),
    COMPUTER_VS_COMPUTER(3);

    private final int optionNumber;

    GameOption(int number) {
        optionNumber = number;
    }

    public static boolean isValid(int gameOptionNumber) {
        return gameOptionNumber == HUMAN_VS_HUMAN.optionNumber || gameOptionNumber == HUMAN_VS_COMPUTER.optionNumber
                || gameOptionNumber == COMPUTER_VS_COMPUTER.optionNumber;
    }

    public static GameOption create(int gameTypeOptionNumber) {
        if (gameTypeOptionNumber == 1) {
            return HUMAN_VS_HUMAN;
        } else if (gameTypeOptionNumber == 2) {
            return HUMAN_VS_COMPUTER;
        } else {
            return COMPUTER_VS_COMPUTER;
        }
    }
}
