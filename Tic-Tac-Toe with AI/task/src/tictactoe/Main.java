package tictactoe;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        //Menu cycle
        if (game.isSettingsPicked()) {
            Player player_1 = new Player(game.settings[0]);
            Player player_2 = new Player(game.settings[1]);

            //Game cycle
            while (game.activeState == Game.States.GAME_NOT_FINISHED) {
                player_1.makeMove(game);
                game.drawTable();
                game.processState();
                game.movesCounter ++;
                if (game.activeState == Game.States.GAME_NOT_FINISHED) {
                    player_2.makeMove(game);
                    game.drawTable();
                    game.processState();
                    game.movesCounter++;
                } else break;
            }

            System.out.println(game.activeState.text);
        }





        /*
        Player player = new Player();

        game.createTable("Empty");
        game.drawTable();

        while (game.activeState == Game.States.GAME_NOT_FINISHED) {
            player.makeMove(game);
            game.processState();
            game.drawTable();
            if (game.activeState == Game.States.GAME_NOT_FINISHED) {
                ai.makeMove(game);
                game.processState();
                game.drawTable();
            } else break;
        }

        System.out.println(game.activeState.text);
        */
    }
}


