package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicTacToe ticTacToe = new TicTacToe(scanner);
        ticTacToe.run();
    }
}