package tictactoe;

import java.util.Scanner;

public class Player {

    protected String announce = "Enter the coordinates: ";
    protected int x;
    protected int y;

    public void makeMove(Game game) {
        getCoordinates(game);
        game.drawMove(x, y);
    }

    public void getCoordinates(Game game) {
        final int minCoordinate = 1;
        final int maxCoordinate = game.table.length;
        int raw_x;
        int raw_y;
        boolean validCoordinates = false;
        String s;
        char[] chars;
        int[] transformedCoords;
        Scanner scanner = new Scanner(System.in);

        while (!validCoordinates) {
            System.out.print(announce);
            s = scanner.nextLine().replaceAll(" ", "");
            chars = s.toCharArray();
            raw_x = chars[0] - 49; //48 + 1
            raw_y = chars[1] - 49; //
            if (chars[0] >= 48 && chars[0] <= 57 && chars[1] >= 48 && chars[1] <= 57) {
                if (raw_x >= minCoordinate - 1 && raw_x <= maxCoordinate - 1
                        && raw_y >= minCoordinate - 1 && raw_y <= maxCoordinate - 1) {
                    transformedCoords = game.converter(raw_x, raw_y);
                    if (game.isCellOccupied(transformedCoords[0], transformedCoords[1])) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        x = transformedCoords[0];
                        y = transformedCoords[1];
                        validCoordinates = true;
                    }
                } else {
                    System.out.printf("Coordinates should be from %d to %d!\n", minCoordinate, maxCoordinate);
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
