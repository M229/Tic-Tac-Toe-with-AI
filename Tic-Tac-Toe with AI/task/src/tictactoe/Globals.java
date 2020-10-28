package tictactoe;

public class Globals {
    final public static int fieldSize = 3;
    final public static String tableVerticalBorder = "---------";
    final public static String tableHorizontalBorder = "|";
    public static String input;
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
    public static Symbols symbolToMove = Symbols.X;

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
    public static States activeState = States.GAME_NOT_FINISHED;
    public static int x = 0;
    public static int y = 0;
    public static char[][] table = new char[fieldSize][fieldSize];
}
