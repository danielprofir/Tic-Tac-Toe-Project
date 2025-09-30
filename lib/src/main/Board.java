package lib.src.main;

/**
 * Represents a 3x3 Tic-Tac-Toe board.
 * The board uses a 2D array to store player marks ('X', 'O', or empty).
 */
public class Board {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';
    private final char[][] board;

    /**
     * Creates a new empty Tic-Tac-Toe board.
     */
    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    /**
     * Initializes the board with empty cells.
     */
    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }


    /**
     * Places a mark on the board at the specified position.
     * @param row the row index (0-2)
     * @param col the column index (0-2)
     * @param mark the mark to place ('X' or 'O')
     * @throws IllegalArgumentException if the position is invalid or already occupied
     */
    public void placeMark(int row, int col, char mark) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Position (" + row + ", " + col + ") is out of bounds");
        }

        if (board[row][col] != EMPTY_CELL) {
            throw new IllegalArgumentException("Position (" + row + ", " + col + ") is already occupied");
        }

        if (mark != 'X' && mark != 'O') {
            throw new IllegalArgumentException("Mark must be 'X' or 'O', got: " + mark);
        }
        board[row][col] = mark;
    }

    /**
     * Checks if a position is valid on the board.
     * @param row the row index
     * @param col the column index
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }


    /**
     * Checks if a position is empty.
     * @param row the row index
     * @param col the column index
     * @return true if the position is empty, false otherwise
     */
    public boolean isEmpty(int row, int col) {
        if (!isValidPosition(row, col)) {
            return false;
        }
        return board[row][col] == EMPTY_CELL;
    }

    /**
     * Gets the mark at a specific position.
     * @param row the row index
     * @param col the column index
     * @return the mark at the position, or empty cell character if position is invalid
     */
    public char getMark(int row, int col) {
        if (!isValidPosition(row, col)) {
            return EMPTY_CELL;
        }
        return board[row][col];
    }

    /**
     * Checks if the board is full.
     * @return true if all positions are occupied, false otherwise
     */
    public boolean isFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if there's a winning condition on the board.
     * @return the winning mark ('X' or 'O'), or empty cell character if no winner
     */
    public char getWinner() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] != EMPTY_CELL &&
                    board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                return board[row][0];
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] != EMPTY_CELL &&
                    board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                return board[0][col];
            }
        }

        // Check main diagonal
        if (board[0][0] != EMPTY_CELL &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }

        // Check anti-diagonal
        if (board[0][2] != EMPTY_CELL &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return EMPTY_CELL;
    }

    /**
     * Checks if the game is over (either won or tied).
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return getWinner() != EMPTY_CELL || isFull();
    }

    /**
     * Returns a string representation of the board.
     * @return formatted string showing the current board state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("   0   1   2\n");
        for (int row = 0; row < BOARD_SIZE; row++) {
            sb.append(row).append("  ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                sb.append(board[row][col]);
                if (col < BOARD_SIZE - 1) {
                    sb.append(" | ");
                }
            }
            if (row < BOARD_SIZE - 1) {
                sb.append("\n  -----------\n");
            }
        }
        return sb.toString();
    }
}