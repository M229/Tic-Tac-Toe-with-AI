package tictactoe;

import java.util.Scanner;

public class Game {
    final public int fieldSize;
    final public String tableVerticalBorder;
    final public String tableHorizontalBorder;
    public String input;

    public enum Symbols {
        X('X', 'X'),
        O('O', 'O'),
        EMPTY('_', ' ');

        final char inputSymbol;
        final char tableSymbol;

        Symbols(char inputSymbol, char tableSymbol) {
            this.inputSymbol = inputSymbol;
            this.tableSymbol = tableSymbol;
        }
    }
    public Symbols symbolToMove;

    public enum States {
        GAME_NOT_FINISHED("Game not finished"),
        DRAW("Draw"),
        X_WINS("X wins"),
        O_WINS("O wins");

        String text;

        States(String text) {
            this.text = text;
        }

    }
    public States activeState;

    public char[][] table;

    public Game() {
        this.fieldSize = 3;
        this.tableVerticalBorder = "---------";
        this.tableHorizontalBorder = "|";

        this.symbolToMove = Game.Symbols.X;

        this.activeState = Game.States.GAME_NOT_FINISHED;

        this.table = new char[fieldSize][fieldSize];

        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                this.table[i][j] = Symbols.EMPTY.tableSymbol;
            }
        }
        this.symbolToMove = Symbols.X;
    }

    public void drawMove(int x, int y) {
        this.table[x][y] = this.symbolToMove.tableSymbol;
        if (this.symbolToMove == Game.Symbols.X) {
            this.symbolToMove = Game.Symbols.O;
        } else if (this.symbolToMove == Game.Symbols.O) {
            this.symbolToMove = Game.Symbols.X;
        }
    }

    public void drawTable() {
        System.out.println(this.tableVerticalBorder);
        for (int i = 0; i < this.table.length; i++) {
            System.out.print(this.tableHorizontalBorder + " ");
            for (int j = 0; j < this.table[i].length; j++) {
                System.out.print(this.table[i][j] + " ");
            }
            System.out.print(this.tableHorizontalBorder + " ");
            System.out.println();
        }
        System.out.println(this.tableVerticalBorder);
    }

    public boolean isCellOccupied(int x, int y) {
        return this.table[x][y] != Symbols.EMPTY.tableSymbol;
    }

    public void processState() {
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
            if (this.activeState == States.O_WINS
                    || this.activeState == States.X_WINS) {
                break;
            }
        }
        //If no winner yet, checking for a draw
        if (this.activeState != States.O_WINS
                && this.activeState != States.X_WINS) {
            this.activeState = States.DRAW;
            for (int i = 0; i < this.table.length; i++) {
                for (int j = 0; j < this.table[0].length; j++) {
                    if (this.table[i][j] == Symbols.EMPTY.tableSymbol) {
                        this.activeState = States.GAME_NOT_FINISHED;
                        break;
                    }
                }
            }
        }


    }

    public void checkRows() {
        int row = 1;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 1; j < this.table[i].length; j++) {
                if (this.table[i][j] != Symbols.EMPTY.tableSymbol
                        && this.table[i][j] == this.table[i][j - 1]) {
                    row++;
                } else break;
            }
            if (row == this.table[i].length) {
                if (this.table[i][0] == Symbols.X.tableSymbol) {
                    this.activeState = States.X_WINS;
                } else if (this.table[i][0] == Symbols.O.tableSymbol) {
                    this.activeState = States.O_WINS;
                }
                break;
            }
            row = 1;
        }
    }

    public void checkCols() {
        int col = 1;
        for (int j = 0; j < this.table[0].length; j++) {
            for (int i = 1; i < this.table.length; i++) {
                if (this.table[i][j] != Symbols.EMPTY.tableSymbol
                        && this.table[i][j] == this.table[i - 1][j]) {
                    col++;
                } else break;
            }
            if (col == this.table.length) {
                if (this.table[0][j] == Symbols.X.tableSymbol) {
                    this.activeState = States.X_WINS;
                } else if (this.table[0][j] == Symbols.O.tableSymbol) {
                    this.activeState = States.O_WINS;
                }
                break;
            }
            col = 1;
        }
    }

    public void checkDigs() {
        int digL = 1;
        int digR = 1;
        int i = 1;
        int j = 1;
        int index = this.table.length - 1;

        while (i < this.table.length) {
            if (this.table[i][j] != Symbols.EMPTY.tableSymbol
                    && this.table[i][j] == this.table[i - 1][j - 1]) {
                digL++;
            }
            if (this.table[i][index - j] != Symbols.EMPTY.tableSymbol
                    && this.table[i][index - j] == this.table[i - 1][index - j + 1]) {
                digR++;
            }
            i++;
            j++;
        }

        if (digL == this.table.length) {
            if (this.table[0][0] == Symbols.X.tableSymbol) {
                this.activeState = States.X_WINS;
            } else if (this.table[0][0] == Symbols.O.tableSymbol) {
                this.activeState = States.O_WINS;
            }
        }
        if (digR == this.table.length) {
            if (this.table[0][index] == Symbols.X.tableSymbol) {
                this.activeState = States.X_WINS;
            } else if (this.table[0][index] == Symbols.O.tableSymbol) {
                this.activeState = States.O_WINS;
            }
        }
    }

    public int[] converter(int x, int y) {
        int[] result = new int[2];
        result[0] = this.table.length - y - 1;
        result[1] = x;
        return result;
    }
}
