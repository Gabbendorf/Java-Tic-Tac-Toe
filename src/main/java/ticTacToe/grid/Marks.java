package ticTacToe.grid;

public enum Marks {

NOUGHT("O"), CROSS("X");

   Marks(String name) {
       sign = name;
   }

   public String sign;
}
