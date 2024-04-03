import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private Random random;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        random = new Random();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void computerMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != '-');
        board[row][col] = currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        while (!game.isBoardFull() && !game.checkWinner()) {
            System.out.println("Current board:");
            game.printBoard();
            System.out.println("Player " + game.getCurrentPlayer() + "'s turn.");
            if (game.getCurrentPlayer() == 'X') {
                System.out.print("Enter row (0-2): ");
                int row = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                int col = scanner.nextInt();
                if (game.makeMove(row, col)) {
                    if (game.checkWinner()) {
                        System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                        game.printBoard();
                        break;
                    } else {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                game.computerMove();
                if (game.checkWinner()) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                    game.printBoard();
                    break;
                } else {
                    game.switchPlayer();
                }
            }
        }

        if (!game.checkWinner() && game.isBoardFull()) {
            System.out.println("It's a draw!");
            game.printBoard();
        }
        scanner.close();
    }
}
