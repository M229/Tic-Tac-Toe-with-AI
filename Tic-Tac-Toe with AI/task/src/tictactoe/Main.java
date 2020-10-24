package tictactoe;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        Globals.input = scanner.nextLine();

        createTable();

        while (Globals.activeState == Globals.States.GAME_NOT_FINISHED) {
            drawTable();
            getCoordinates();
            makeMove();
            processState();
        }
    }

    private static void createTable() {
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
        if (qtyX >= qtyO || qtyX == qtyO) {
            Globals.symbolToMove = Globals.Symbols.X;
        } else {
            Globals.symbolToMove = Globals.Symbols.O;
        }
    }

    private static void drawTable() {

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

    private static boolean isCellOccupied(int x, int y) {

        if (Globals.table[x][y] == Globals.Symbols.EMPTY.tableSymbol) {
            return false;
        } else {
            return true;
        }
    }

    private static void getCoordinates() {
        final int minCoordinate = 1;
        final int maxCoordinate = Globals.table.length;
        int x;
        int y;
        boolean validCoordinates = false;
        String s;
        char[] chars;
        Scanner scanner = new Scanner(System.in);

        while (validCoordinates == false) {
            System.out.print("Enter the coordinates: ");
            s = scanner.nextLine().replaceAll(" ","");
            chars = s.toCharArray();
            x = chars[0] - 49; //48 + 1
            y = chars[1] - 49; //
            if (chars[0] >= 48 && chars[0] <= 57 && chars[1] >= 48 && chars[1] <= 57) {
                if (x >= minCoordinate - 1 && x <= maxCoordinate - 1
                        && y >= minCoordinate - 1 && y <= maxCoordinate - 1) {
                    if (isCellOccupied(x, y)) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        Globals.x = x;
                        Globals.y = y;
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

    private static void makeMove() {
        Globals.table[Globals.x][Globals.y] = Globals.symbolToMove.tableSymbol;
        if (Globals.symbolToMove == Globals.Symbols.X) {
            Globals.symbolToMove = Globals.Symbols.O;
        } else if (Globals.symbolToMove == Globals.Symbols.O) {
            Globals.symbolToMove = Globals.Symbols.X;
        }
    }

    private static void processState() {
        int row = 1;
        int col = 0;
        for (int i = 0; i < Globals.table.length; i++) {
            for (int j = 1; j < Globals.table[i].length; j++) {
                if (Globals.table[i][j] != Globals.Symbols.EMPTY.tableSymbol
                        && Globals.table[i][j] == Globals.table[i][j - 1]) {
                    row++;
                }
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
}
