package tictactoe;

import java.util.Scanner;

public class Game {
    public static void createTable() {
        char[] chars = Globals.input.toCharArray();
        int k = 0;
        int qtyX = 0;
        int qtyO = 0;
        for (int i = 0; i < Globals.table.length; i++) {
            for (int j = 0; j < Globals.table[i].length; j++) {
                if (chars[k] == Globals.Symbols.X.inputSymbol) {
                    Globals.table[i][j] = Globals.Symbols.X.tableSymbol;
                    qtyX++;
                } else if (chars[k] == Globals.Symbols.O.inputSymbol) {
                    Globals.table[i][j] = Globals.Symbols.O.tableSymbol;
                    qtyO++;
                } else {
                    Globals.table[i][j] = Globals.Symbols.EMPTY.tableSymbol;
                }
                k++;
            }
        }
        if (qtyO >= qtyX) {
            Globals.symbolToMove = Globals.Symbols.X;
        } else {
            Globals.symbolToMove = Globals.Symbols.O;
        }
    }

    public static void createTable(String str) {
        for (int i = 0; i < Globals.table.length; i++) {
            for (int j = 0; j < Globals.table[i].length; j++) {
                Globals.table[i][j] = Globals.Symbols.EMPTY.tableSymbol;
            }
        }
            Globals.symbolToMove = Globals.Symbols.X;
    }

    public static void drawTable() {
        System.out.println(Globals.tableVerticalBorder);
        for (int i = 0; i < Globals.table.length; i++) {
            System.out.print(Globals.tableHorizontalBorder + " ");
            for (int j = 0; j < Globals.table[i].length; j++) {
                System.out.print(Globals.table[i][j] + " ");
            }
            System.out.print(Globals.tableHorizontalBorder + " ");
            System.out.println();
        }
        System.out.println(Globals.tableVerticalBorder);
    }

    public static boolean isCellOccupied(int x, int y) {

        return Globals.table[x][y] != Globals.Symbols.EMPTY.tableSymbol;
    }

    public static void getCoordinates() {
        final int minCoordinate = 1;
        final int maxCoordinate = Globals.table.length;
        int x;
        int y;
        boolean validCoordinates = false;
        String s;
        char[] chars;
        int[] transformedCoords;
        Scanner scanner = new Scanner(System.in);

        while (!validCoordinates) {
            System.out.print("Enter the coordinates: ");
            s = scanner.nextLine().replaceAll(" ", "");
            chars = s.toCharArray();
            x = chars[0] - 49; //48 + 1
            y = chars[1] - 49; //
            if (chars[0] >= 48 && chars[0] <= 57 && chars[1] >= 48 && chars[1] <= 57) {
                if (x >= minCoordinate - 1 && x <= maxCoordinate - 1
                        && y >= minCoordinate - 1 && y <= maxCoordinate - 1) {
                    transformedCoords = converter(x, y);
                    if (isCellOccupied(transformedCoords[0], transformedCoords[1])) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        Globals.x = transformedCoords[0];
                        Globals.y = transformedCoords[1];
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

    public static void makeMove() {
        Globals.table[Globals.x][Globals.y] = Globals.symbolToMove.tableSymbol;
        if (Globals.symbolToMove == Globals.Symbols.X) {
            Globals.symbolToMove = Globals.Symbols.O;
        } else if (Globals.symbolToMove == Globals.Symbols.O) {
            Globals.symbolToMove = Globals.Symbols.X;
        }
    }

    public static void processState() {
        //Checking for a win
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    checkRows();
                case 1:
                    checkCols();
                case 2:
                    checkDigs();
            }
            if (Globals.activeState == Globals.States.O_WINS
                    || Globals.activeState == Globals.States.X_WINS) {
                break;
            }
        }
        //If no winner yet, checking for a draw
        if (Globals.activeState != Globals.States.O_WINS
                && Globals.activeState != Globals.States.X_WINS) {
            Globals.activeState = Globals.States.DRAW;
            for (int i = 0; i < Globals.table.length; i++) {
                for (int j = 0; j < Globals.table[0].length; j++) {
                    if (Globals.table[i][j] == Globals.Symbols.EMPTY.tableSymbol) {
                        Globals.activeState = Globals.States.GAME_NOT_FINISHED;
                        break;
                    }
                }
            }
        }


    }

    public static void checkRows() {
        int row = 1;
        for (int i = 0; i < Globals.table.length; i++) {
            for (int j = 1; j < Globals.table[i].length; j++) {
                if (Globals.table[i][j] != Globals.Symbols.EMPTY.tableSymbol
                        && Globals.table[i][j] == Globals.table[i][j - 1]) {
                    row++;
                } else break;
            }
            if (row == Globals.table[i].length) {
                if (Globals.table[i][0] == Globals.Symbols.X.tableSymbol) {
                    Globals.activeState = Globals.States.X_WINS;
                } else if (Globals.table[i][0] == Globals.Symbols.O.tableSymbol) {
                    Globals.activeState = Globals.States.O_WINS;
                }
                break;
            }
            row = 1;
        }
    }

    public static void checkCols() {
        int col = 1;
        for (int j = 0; j < Globals.table[0].length; j++) {
            for (int i = 1; i < Globals.table.length; i++) {
                if (Globals.table[i][j] != Globals.Symbols.EMPTY.tableSymbol
                        && Globals.table[i][j] == Globals.table[i - 1][j]) {
                    col++;
                } else break;
            }
            if (col == Globals.table.length) {
                if (Globals.table[0][j] == Globals.Symbols.X.tableSymbol) {
                    Globals.activeState = Globals.States.X_WINS;
                } else if (Globals.table[0][j] == Globals.Symbols.O.tableSymbol) {
                    Globals.activeState = Globals.States.O_WINS;
                }
                break;
            }
            col = 1;
        }
    }

    public static void checkDigs() {
        int digL = 1;
        int digR = 1;
        int i = 1;
        int j = 1;
        int index = Globals.table.length - 1;

        while (i < Globals.table.length) {
            if (Globals.table[i][j] != Globals.Symbols.EMPTY.tableSymbol
                    && Globals.table[i][j] == Globals.table[i - 1][j - 1]) {
                digL++;
            }
            if (Globals.table[i][index - j] != Globals.Symbols.EMPTY.tableSymbol
                    && Globals.table[i][index - j] == Globals.table[i - 1][index - j + 1]) {
                digR++;
            }
            i++;
            j++;
        }

        if (digL == Globals.table.length) {
            if (Globals.table[0][0] == Globals.Symbols.X.tableSymbol) {
                Globals.activeState = Globals.States.X_WINS;
            } else if (Globals.table[0][0] == Globals.Symbols.O.tableSymbol) {
                Globals.activeState = Globals.States.O_WINS;
            }
        }
        if (digR == Globals.table.length) {
            if (Globals.table[0][index] == Globals.Symbols.X.tableSymbol) {
                Globals.activeState = Globals.States.X_WINS;
            } else if (Globals.table[0][index] == Globals.Symbols.O.tableSymbol) {
                Globals.activeState = Globals.States.O_WINS;
            }
        }
    }

    public static int[] converter(int x, int y) {
        int[] result = new int[2];
        result[0] = Globals.table.length - y - 1;
        result[1] = x;
        return result;
    }
}
