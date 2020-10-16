package tictactoe;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[][] field = new char[3][3];

        field = createField(input);
        drawField(field);
    }

    private static char[][] createField(String s) {

        char[][] field = new char[3][3];
        char[] chars = s.toCharArray();
        int k = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = chars[k];
                k++;
            }
        }
        return field;
    }

    private  static void drawField(char[][] field) {
        final char inputEmptySymbol = '_';
        final char inputXSymbol = 'X';
        final char inputOSymbol = 'O';

        final String fieldVerticalBorder = "---------";
        final String fieldHorizontalBorder = "|";
        final char fieldEmptySymbol = ' ';
        final char fieldXSymbol = 'X';
        final char fieldOSymbol = 'O';

        System.out.println(fieldVerticalBorder);
        for (int i = 0; i < field.length; i++) {
            System.out.print(fieldHorizontalBorder + " ");
            for (int j = 0; j < field[i].length; j++) {
                switch (field[i][j]) {
                    case inputEmptySymbol: System.out.print(fieldEmptySymbol + " "); break;
                    case inputXSymbol: System.out.print(fieldXSymbol + " "); break;
                    case inputOSymbol: System.out.print(fieldOSymbol + " "); break;
                }
            }
            System.out.print(fieldHorizontalBorder + " ");
            System.out.println();
        }
        System.out.println(fieldVerticalBorder);
    }
}
