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
    //Getting coordinates and draw symbol using game method
    public void makeMove(Game game) {
        switch (this.type) {
            case USER: getCoordinatesUser(game); break;
            case AI_EASY: getCoordinatesAIEasy(game); break;
            case AI_MEDIUM: getCoordinatesAIMedium(game); break;
            case AI_HARD: break;
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

    private void getCoordinatesAIEasy(Game game) {
        String announce = "Making move level \"easy\"";
        int raw_x;
        int raw_y;

        Random random = new Random();

        do {
            raw_x = random.nextInt(game.fieldSize);
            raw_y = random.nextInt(game.fieldSize);
        } while (game.isCellOccupied(raw_x, raw_y));

        x = raw_x;
        y = raw_y;
        System.out.println(announce);
    }

    private void getCoordinatesAIMedium(Game game) {
        String announce = "Making move level \"medium\"";
        boolean winInOneMove = false;
        int k = 0;
        int index = game.table.length - 1;
        int raw_x;
        int raw_y;
        int row = 0;
        int col = 0;
        int fill_counter = 0;
        int empty_counter = 0;
        Game.Symbols opponentSymbol;

        //finding opponent's symbol
        opponentSymbol = game.symbolToMove == Game.Symbols.O ? Game.Symbols.X : Game.Symbols.O;

        //checking rows
        for (int i = 0; i < game.table.length; i++) {
            for (int j = 0; j < game.table[i].length; j++) {
                if (game.table[i][j] == opponentSymbol.tableSymbol) {
                    fill_counter++;
                } else if (game.table[i][j] == Game.Symbols.EMPTY.tableSymbol) {
                    col = i;
                    row = j;
                    empty_counter++;
                }
            }
            if (fill_counter == game.table.length - 1 && empty_counter == 1) {
                winInOneMove = true;
                break;
            } else {
                col = 0;
                row = 0;
                fill_counter = 0;
                empty_counter = 0;
            }
        }

        //checking cols
        if (!winInOneMove) {
            for (int j = 0; j < game.table.length; j++) {
                for (int i = 0; i < game.table.length; i++) {
                    if (game.table[i][j] == opponentSymbol.tableSymbol) {
                        fill_counter++;
                    } else if (game.table[i][j] == Game.Symbols.EMPTY.tableSymbol) {
                        col = i;
                        row = j;
                        empty_counter++;
                    }
                }
                if (fill_counter == game.table.length - 1 && empty_counter == 1) {
                    winInOneMove = true;
                    break;
                } else {
                    col = 0;
                    row = 0;
                    fill_counter = 0;
                    empty_counter = 0;
                }
            }
        }

        //checking left diagonal
        if (!winInOneMove) {
            while (k < game.table.length) {
                if (game.table[k][k] == opponentSymbol.tableSymbol) {
                    fill_counter++;
                } else if (game.table[k][k] == Game.Symbols.EMPTY.tableSymbol) {
                    col = k;
                    row = k;
                    empty_counter++;
                }
                k++;
            }
            if (fill_counter == game.table.length - 1 && empty_counter == 1) {
                winInOneMove = true;
            } else {
                k = 0;
                col = 0;
                row = 0;
                fill_counter = 0;
                empty_counter = 0;
            }
        }

        //checking right diagonal
        if (!winInOneMove) {
            while (k < game.table.length) {
                if (game.table[k][index - k] == opponentSymbol.tableSymbol) {
                    fill_counter++;
                } else if (game.table[k][index - k] == Game.Symbols.EMPTY.tableSymbol) {
                    col = k;
                    row = index - k;
                    empty_counter++;
                }
                k++;
            }
            if (fill_counter == game.table.length - 1 && empty_counter == 1) {
                winInOneMove = true;
            }
        }

        if (winInOneMove) {
            x = col;
            y = row;
        } else {
            Random random = new Random();
            do {
                raw_x = random.nextInt(game.fieldSize);
                raw_y = random.nextInt(game.fieldSize);
            } while (game.isCellOccupied(raw_x, raw_y));
            x = raw_x;
            y = raw_y;
        }
        System.out.println(announce);
    }
}
