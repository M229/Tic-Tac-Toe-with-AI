package tictactoe;

import java.util.Random;

public class AI extends Player {

    protected String announce = "Making move level \"easy\"";

    public void getCoordinates(Game game) {
        int raw_x = 0;
        int raw_y = 0;
        boolean cellOccupied = false;

        Random random = new Random();

        do {
            raw_x = random.nextInt(game.fieldSize);
            raw_y = random.nextInt(game.fieldSize);
        } while (game.isCellOccupied(raw_x, raw_y));

        x = raw_x;
        y = raw_y;
        System.out.println(announce);
    }

}
