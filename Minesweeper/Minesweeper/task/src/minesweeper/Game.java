package minesweeper;

import java.util.Scanner;

public class Game {

    private Scanner scanner;

    private Minefield minefield;
    private GameStates state;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        state = GameStates.RUNNING;

        initMinefield();

        while (state == GameStates.RUNNING) {
            minefield.printMinefield();

            makeMove();

            checkForWin();
        }

        minefield.printMinefield();
        printEndMessage();
    }

    public void initMinefield() {
        System.out.println("How many mines do you want on the field? ");
        int mines = scanner.nextInt();

        minefield = new Minefield(mines);
    }

    public void makeMove() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");

        int y = scanner.nextInt()-1;
        int x = scanner.nextInt()-1;
        String command = scanner.next();

        switch(command) {
            case "free": revealIfNotBomb(x, y); break;
            case "mine": setMarkIfPossible(x, y); break;
            default: System.out.println("Unknown command");
        }
    }

    public void setMarkIfPossible(int x, int y) {
        if(minefield.isMarkable(x, y)) {
            minefield.mark(x, y);
        }
    }

    public void revealIfNotBomb(int x, int y) {
        if(minefield.isBomb(x, y)) {
            state = GameStates.LOSE;
            minefield.revealAllMines();
        } else {
            minefield.revealAllConnectedEmpty(x, y);
        }
    }

    public void checkForWin() {
        if( ( minefield.allBombsAreMarked() || minefield.onlyBombsAreHidden() )  && minefield.areMinesGenerated() ) {
            state = GameStates.WIN;
        }
    }

    public void printEndMessage() {
        if(state == GameStates.WIN) {
            System.out.println("Congratulations! You found all mines!");
        } else if(state == GameStates.LOSE) {
            System.out.println("You stepped on a mine and failed!");
        }
    }
}
