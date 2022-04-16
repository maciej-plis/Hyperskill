package tictactoe;

public class Board {

    private final char player1Sign = 'X';
    private final char player2Sign = 'O';

    private char[][] board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    public Board(){};

    public Board(String state) {
        state = state.replace('_', ' ');
        int k = 0;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                board[i][j] = state.charAt(k);
                k++;
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char getPlayer1Sign() {
        return player1Sign;
    }

    public char getPlayer2Sign() {
        return player2Sign;
    }

    public boolean isCorrect(String xInput, String yInput) {
        int x, y;
        try {
            y = Integer.parseInt(yInput) - 1;
            x = 2 - (Integer.parseInt(xInput) - 1);
        } catch(Exception e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if( (x < 0 || x >= board.length) || (y < 0 || y >= board[0].length) ) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if(board[x][y] != 'X' && board[x][y] != 'O') {
            return true;
        }

        System.out.println("This cell is occupied! Choose another one!");
        return false;
    }

    public void makeMove(Coordinates coordinates, char player) {
        this.board[coordinates.getX()][coordinates.getY()] = player;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<5; i++) {
            for(int j=0; j<9; j++) {
                if(i==0 || i==4) {
                    sb.append('-');
                } else if(j==0 || j==8) {
                    sb.append('|');
                } else {
                    if(j%2 == 1)
                        sb.append(' ');
                    else
                        sb.append(board[i-1][j/2-1]);
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}