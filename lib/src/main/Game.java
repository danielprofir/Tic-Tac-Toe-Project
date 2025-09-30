package lib.src.main;

/**
 * Manages a game of Tic-Tac-Toe between two players.
 * This class handles the game flow, turn management, and game state.
 */
public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private Player currentPlayer;
    private GameState gameState;

    public enum GameState {
        IN_PROGRESS,
        WON,
        TIED
    }

    /**
     * Creates a new Tic-Tac-Toe game with the specified players.
     * @param playerX the player who will use 'X' marks
     * @param playerO the player who will use 'O' marks
     * @throws IllegalArgumentException if either player is null
     */
    public Game(Player playerX, Player playerO) {
        if (playerX == null || playerO == null) {
            throw new IllegalArgumentException("Players cannot be null");
        }
        if (playerX.getMark() != 'X' || playerO.getMark() != 'O') {
            throw new IllegalArgumentException("Player X must have mark 'X' and Player O must have mark 'O'");
        }

        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX; //X always goes first
        this.gameState = GameState.IN_PROGRESS;
    }

    /**
     * Gets the current state of the game.
     * @return the current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gets the current player whose turn it is.
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the board for this game.
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the player who uses 'X' marks.
     * @return the X player
     */
    public Player getPlayerX() {
        return playerX;
    }

    /**
     * Gets the player who uses 'O' marks.
     * @return the O player
     */
    public Player getPlayerO() {
        return playerO;
    }

    /**
     * Updates the game state based on the current board configuration.
     */
    private void updateGameState() {
        char winner = board.getWinner();
        if (winner != ' ') {
            gameState = GameState.WON;
        } else if (board.isFull()) {
            gameState = GameState.TIED;
        }
    }
    /**
     * Switches the current player to the other player.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    /**
     * Makes a move for the current player at the specified position.
     * @param row the row index (0-2)
     * @param col the column index (0-2)
     * @return true if the move was successful, false if the game is already over
     * @throws IllegalArgumentException if the position is invalid or already occupied
     */
    public boolean makeMove(int row, int col) {
        if (gameState != GameState.IN_PROGRESS) {
            return false; // Game is already over
        }

        board.placeMark(row, col, currentPlayer.getMark());

        // Check for win or tie
        updateGameState();

        // Switch to the other player if game is still in progress
        if (gameState == GameState.IN_PROGRESS) {
            switchPlayer();
        }
        return true;
    }
    /**
     * Gets the winner of the game.
     * @return the winning player, or null if the game is tied or still in progress
     */
    public Player getWinner() {
        if (gameState != GameState.WON) {
            return null;
        }
        char winnerMark = board.getWinner();
        return (winnerMark == 'X') ? playerX : playerO;
    }
    /**
     * Checks if the game is over.
     * @return true if the game is over (won or tied), false otherwise
     */
    public boolean isGameOver() {
        return gameState != GameState.IN_PROGRESS;
    }

    /**
     * Checks if the current position is valid for a move.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the position is valid and empty, false otherwise
     */
    public boolean isValidMove(int row, int col) {
        return gameState == GameState.IN_PROGRESS && board.isEmpty(row, col);
    }

    /**
     * Returns a string representation of the current game state.
     *
     * @return formatted string showing the board and current player
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current player: ").append(currentPlayer.getName()).append("\n");
        sb.append(board.toString());
        return sb.toString();
    }
}
