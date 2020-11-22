package tictactoe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        //Menu cycle
        if (game.isSettingsPicked()) {
            Player player_1 = new Player(game.settings[0]);
            Player player_2 = new Player(game.settings[1]);

            //Game cycle
            while (game.activeState == Game.States.GAME_NOT_FINISHED) {
                player_1.makeMove(game);
                game.processState();
                if (game.activeState == Game.States.GAME_NOT_FINISHED) {
                    player_2.makeMove(game);
                    game.processState();
                } else break;
            }
            System.out.println(game.activeState.text);
        }
    }
}


