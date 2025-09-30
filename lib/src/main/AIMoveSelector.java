package lib.src.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility to select moves for computer players.
 */
public final class AIMoveSelector {

    private static final Random RANDOM = new Random();

    private AIMoveSelector() {}

    /**
     * Selects a random empty cell from the board.
     * @param board the current game board
     * @return an array of two integers {row, column} representing a randomly chosen empty cell,
     *         or null if the board has no empty cells.
     */
    public static int[] selectRandomMove(Board board) {
        List<int[]> empties = new ArrayList<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.isEmpty(r, c)) {
                    empties.add(new int[]{r, c});
                }
            }
        }
        if (empties.isEmpty()) return null;
        return empties.get(RANDOM.nextInt(empties.size()));
    }

    /**
     * Selects a move based on a simple heuristic strategy:
     * Win if possible
     * Block the opponent if they could win next
     * Take the center if available
     * Take a corner if available
     * Take an edge if available
     * @param board  the current game board
     * @param myMark the mark of the AI player ('X' or 'O')
     * @return an array of two integers {row, column} representing the chosen move,
     *         or null if no moves are availablem
     */
    public static int[] selectHeuristicMove(Board board, char myMark) {
        char opponent = (myMark == 'X') ? 'O' : 'X';

        // 1) Win if possible
        int[] win = findWinningMove(board, myMark);
        if (win != null) return win;

        // 2) Block opponent
        int[] block = findWinningMove(board, opponent);
        if (block != null) return block;

        // 3) Center
        if (board.isEmpty(1, 1)) return new int[]{1, 1};

        // 4) Corners
        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        for (int[] move : corners) {
            if (board.isEmpty(move[0], move[1])) return move;
        }

        // 5) Edges
        int[][] edges = {{0,1},{1,0},{1,2},{2,1}};
        for (int[] move : edges) {
            if (board.isEmpty(move[0], move[1])) return move;
        }

        return null;
    }

    /**
     * Finds a move that would result in an immediate win for the given mark.
     * @param board the current game board
     * @param mark  the player's mark ('X' or 'O')
     * @return an array of two integers {row, column} representing a winning move,
     *         or null if no winning move exists.
     */
    private static int[] findWinningMove(Board board, char mark) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.isEmpty(r, c)) {
                    // Try placing and see if it wins
                    // We cannot mutate the real board, so simulate by checking lines around (r,c)
                    if (wouldCompleteLine(board, r, c, mark)) {
                        return new int[]{r, c};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Determines whether placing the given mark at the specified cell
     * would complete a line (row, column, or diagonal) and result in a win.
     * @param board the current game board
     * @param row   the row index of the cell
     * @param col   the column index of the cell
     * @param mark  the player's mark ('X' or 'O')
     * @return true if placing the mark at (row, col) would complete a line, false otherwise
     */
    private static boolean wouldCompleteLine(Board board, int row, int col, char mark) {
        // Row
        boolean rowWin = true;
        for (int c = 0; c < 3; c++) {
            char m = (c == col) ? mark : board.getMark(row, c);
            if (m != mark) { rowWin = false; break; }
        }
        if (rowWin) return true;

        // Column
        boolean colWin = true;
        for (int r = 0; r < 3; r++) {
            char m = (r == row) ? mark : board.getMark(r, col);
            if (m != mark) { colWin = false; break; }
        }
        if (colWin) return true;

        // Main diagonal
        if (row == 0 && col == 0 || row == 1 && col == 1 || row == 2 && col == 2) {
            boolean diagWin = true;
            for (int i = 0; i < 3; i++) {
                char m = (i == row && i == col) ? mark : board.getMark(i, i);
                if (m != mark) { diagWin = false; break; }
            }
            if (diagWin) return true;
        }

        // Anti-diagonal
        if (row + col == 2) {
            boolean antiDiagWin = true;
            for (int i = 0; i < 3; i++) {
                int r = i;
                int c = 2 - i;
                char m = (r == row && c == col) ? mark : board.getMark(r, c);
                if (m != mark) { antiDiagWin = false; break; }
            }
            if (antiDiagWin) return true;
        }

        return false;
    }
}


