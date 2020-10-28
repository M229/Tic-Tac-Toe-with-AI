package tictactoe;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Game.createTable("Empty");
        Game.drawTable();

        while (Globals.activeState == Globals.States.GAME_NOT_FINISHED) {
            Game.getCoordinates();
            Game.makeMove();
            Game.processState();
            Game.drawTable();
            System.out.println(Globals.activeState.text);
        }
    }
}


