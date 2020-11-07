package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Player {

    enum Type {
        USER("user"),
        AI_EASY("easy"),
        AI_MEDIUM("medium"),
        AI_HARD("hard");


        String text;

        Type(String text) {
            this.text = text;
        }
    }

    Type type;

    protected int x; //
    protected int y;

    public Player(Type type) {
        this.type = type;
        this.x = 0;
        this.y = 0;
    }

    public void makeMove(Game game) {
        switch (this.type) {
            case USER: getCoordinatesUser(game); break;
            case AI_EASY:
            case AI_MEDIUM:
            case AI_HARD: getCoordinatesAI(game); break;
        }
        game.drawMove(x, y);
    }

    private void getCoordinatesUser(Game game) {
        final String announce = "Enter the coordinates: ";
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

    private void getCoordinatesAI(Game game) {
        String announce = "Making move level \"easy\"";
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
