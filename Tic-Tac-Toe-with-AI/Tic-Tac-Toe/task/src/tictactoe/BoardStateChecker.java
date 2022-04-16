package tictactoe;

public class BoardStateChecker {

    public static States complexCheck(Board board) {
        if(!isPossible(board))
            return States.IMPOSSIBLE;

        if(hasPlayer1Won(board))
            return States.PLAYER1_WINS;


        if(hasPlayer2Won(board))
            return States.PLAYER2_WINS;

        return isFinished(board) ? States.DRAW : States.NOT_FINISHED;

    }

    private static boolean isPossible(Board board) {
        if(checkForHorizontalWin(board, board.getPlayer1Sign()) && checkForHorizontalWin(board, board.getPlayer2Sign())) {
            return false;
        } else if(checkForVerticalWin(board, board.getPlayer1Sign()) && checkForVerticalWin(board, board.getPlayer2Sign())) {
            return false;
        } else if(tooManyMoves(board)) {
            return false;
        }

        return true;
    }

    private static boolean tooManyMoves(Board board) {
        char player1moves = 0;
        char player2moves = 0;

        for(char[] x : board.getBoard()) {
            for(char y : x) {
                if(y == board.getPlayer1Sign())
                    player1moves++;
                else if(y == board.getPlayer2Sign())
                    player2moves++;
            }
        }

        if(player1moves >= player2moves+2 || player2moves >= player1moves+2) {
            return true;
        }

        return false;
    }

    private static boolean hasPlayer1Won(Board board) {
        if(checkForVerticalWin(board, board.getPlayer1Sign())) {
            return true;
        } else if(checkForHorizontalWin(board, board.getPlayer1Sign())) {
            return true;
        } else if(checkForDiagonalWin(board, board.getPlayer1Sign())) {
            return true;
        }

        return false;
    }

    private static boolean hasPlayer2Won(Board board) {
        if(checkForVerticalWin(board, board.getPlayer2Sign())) {
            return true;
        } else if(checkForHorizontalWin(board, board.getPlayer2Sign())) {
            return true;
        } else if(checkForDiagonalWin(board, board.getPlayer2Sign())) {
            return true;
        }

        return false;
    }

    private static boolean checkForVerticalWin(Board board, char c) {
        char[][] boardArr = board.getBoard();

        for(int i=0; i<boardArr.length; i++) {
            if(boardArr[0][i] == c && boardArr[1][i] == c && boardArr[2][i] == c)
                return true;
        }

        return false;
    }

    private static boolean checkForHorizontalWin(Board board, char c) {
        char[][] boardArr = board.getBoard();

        for(int i=0; i<boardArr.length; i++) {
            if(boardArr[i][0] == c && boardArr[i][1] == c && boardArr[i][2] == c)
                return true;
        }

        return false;
    }

    private static boolean checkForDiagonalWin(Board board, char c) {
        char[][] boardArr = board.getBoard();

        if(boardArr[0][0] == c && boardArr[1][1] == c && boardArr[2][2] == c) {
            return true;
        } else if(boardArr[0][2] == c && boardArr[1][1] == c && boardArr[2][0] == c) {
            return true;
        }

        return false;
    }

    private static boolean isFinished(Board board) {
        for(char[] x : board.getBoard()) {
            for(char y : x) {
                if(y == ' ')
                    return false;
            }
        }

        return true;
    }
}
