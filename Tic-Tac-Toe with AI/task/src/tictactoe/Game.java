package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    final public int fieldSize;
    final public String tableVerticalBorder;
    final public String tableHorizontalBorder;
    public Player.Type[] settings;
    public int movesCounter;
    public int inputLength;


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
        this.settings = new Player.Type[2];
        this.movesCounter = 0;
        this.inputLength = 3;

        this.symbolToMove = Game.Symbols.X;

        this.activeState = Game.States.GAME_NOT_FINISHED;

        this.table = new char[fieldSize][fieldSize];

        for (char[] chars : this.table) {
            Arrays.fill(chars, Symbols.EMPTY.tableSymbol);
        }
        this.symbolToMove = Symbols.X;
    }

    public boolean isSettingsPicked() {
        boolean validInput = false;
        String err_msg = "Bad parameters!";
        String str;
        String[] arr;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Input command: ");
            str = scanner.nextLine();
            arr = str.split(" ");
            if (arr.length == inputLength) {
                if ("start".equals(arr[0])) {
                    for (int i = 1; i < arr.length; i++) {
                        validInput = true;
                        if (checkEnumByText(arr[i]) == null) {
                            validInput = false;
                            break;
                        }
                    }
                }
            } else if ("exit".equals(arr[0])) {
                return false;
                //break;
            }
            if (!validInput) {
                System.out.println(err_msg);
            }
        } while (!validInput);
        this.settings[0] = checkEnumByText(arr[1]);
        this.settings[1] = checkEnumByText(arr[2]);
        return true;
    }

    protected void drawMove(int x, int y) {
        this.table[x][y] = this.symbolToMove.tableSymbol;
        if (this.symbolToMove == Game.Symbols.X) {
            this.symbolToMove = Game.Symbols.O;
        } else if (this.symbolToMove == Game.Symbols.O) {
            this.symbolToMove = Game.Symbols.X;
        }
    }

    public void drawTable() {
        System.out.println(this.tableVerticalBorder);
        for (char[] chars : this.table) {
            System.out.print(this.tableHorizontalBorder + " ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.print(this.tableHorizontalBorder + " ");
            System.out.println();
        }
        System.out.println(this.tableVerticalBorder);
    }

    protected boolean isCellOccupied(int x, int y) {
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
            for (char[] chars : this.table) {
                for (int j = 0; j < this.table[0].length; j++) {
                    if (chars[j] == Symbols.EMPTY.tableSymbol) {
                        this.activeState = States.GAME_NOT_FINISHED;
                        break;
                    }
                }
            }
        }


    }

    public void checkRows() {
        int row = 1;
        for (char[] chars : this.table) {
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] != Symbols.EMPTY.tableSymbol
                        && chars[j] == chars[j - 1]) {
                    row++;
                } else break;
            }
            if (row == chars.length) {
                if (chars[0] == Symbols.X.tableSymbol) {
                    this.activeState = States.X_WINS;
                } else if (chars[0] == Symbols.O.tableSymbol) {
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

    private Player.Type checkEnumByText(String str) {
        for (Player.Type type: Player.Type.values()) {
            if (type.text.equals(str)) {
                return type;
            }
        }
        return null;
    }
}
