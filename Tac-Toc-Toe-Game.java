import java.util.Scanner;

public class TicTacToe {
    // Game board
    private char[][] board;
    // Current player ('X' or 'O')
    private char currentPlayer;

    // Constructor
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the game board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the game board
    private void printBoard() {
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

    // Check if the current move is valid
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    // Make a move on the game board
    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    // Switch player after each move
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if the game is over (win or draw)
    private boolean isGameOver() {
        return (checkWin('X') || checkWin('O') || isBoardFull());
    }

    // Check if the board is full (draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if a player has won the game
    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Horizontal win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Vertical win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }

    // Main method to play the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            // Print current board
            game.printBoard();
            // Get player's move
            System.out.println("Player " + game.currentPlayer + "'s turn (row[1-3] column[1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            // Check if the move is valid
            if (game.isValidMove(row, col)) {
                game.makeMove(row, col);
                game.switchPlayer();
            } else {
                System.out.println("Invalid move! Please try again.");
            }
        }
        // Game over
        game.printBoard();
        if (game.checkWin('X')) {
            System.out.println("Player X wins!");
        } else if (game.checkWin('O')) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("It's a draw!");
        }
        scanner.close();
    }
}
