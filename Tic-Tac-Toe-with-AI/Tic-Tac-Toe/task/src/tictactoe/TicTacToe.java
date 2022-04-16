package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private Scanner scanner;
    private Board board;
    private States state;
    private char turn;

    public TicTacToe(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        this.turn = 'X';

        this.board = new Board();
        System.out.println(board);

        do {
            makeMove();
            System.out.println(board);
            state = BoardStateChecker.complexCheck(board);
            changePlayerTurn();
        } while(state == States.NOT_FINISHED);

        printBoardState();
    }

    private void changePlayerTurn() {
        if(this.turn == 'X') {
            this.turn = 'O';
        } else {
            this.turn = 'X';
        }
    }

    private String getStartingPlacing() {
        System.out.print("Enter cells: ");
        String state = scanner.nextLine();

        return state;

    }

    private void printBoardState() {
        String message = "";

        switch(state) {
            case DRAW: message = "Draw"; break;
            case PLAYER1_WINS: message = "X wins"; break;
            case PLAYER2_WINS: message = "O wins"; break;
            case IMPOSSIBLE: message = "Impossible"; break;
            case NOT_FINISHED: message = "Player " + turn + " turn"; break;
        }

        System.out.println(message);
    }

    private Coordinates getCorrectCoordinates() {
        String xInput, yInput;

        do {
            System.out.print("Enter the coordinates: ");

            yInput = scanner.next();
            xInput = scanner.next();

        } while(!board.isCorrect(xInput, yInput));

        int y = Integer.parseInt(yInput) - 1;
        int x = 2 - (Integer.parseInt(xInput) - 1);

        return new Coordinates(x, y);
    }

    private void makeMove() {
        Coordinates coordinates = getCorrectCoordinates();
        board.makeMove(coordinates, turn);
    }
}
