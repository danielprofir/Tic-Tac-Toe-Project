package lib.src.test;


import lib.src.main.Board;
import lib.src.main.Game;
import lib.src.main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Player playerX;
    private Player playerO;
    private Game game;

    @BeforeEach
    void setUp() {
        playerX = new Player("Alice", 'X');
        playerO = new Player("Bob", 'O');
        game = new Game(playerX, playerO);
    }

    // Constructor tests
    @Test
    void testConstructorRejectsNullPlayers() {
        assertThrows(IllegalArgumentException.class, () -> new Game(null, playerO));
        assertThrows(IllegalArgumentException.class, () -> new Game(playerX, null));
    }

    @Test
    void testConstructorRejectsWrongMarks() {
        Player invalidX = new Player("Charlie", 'O');
        Player invalidO = new Player("Dana", 'X');
        assertThrows(IllegalArgumentException.class, () -> new Game(invalidX, playerO));
        assertThrows(IllegalArgumentException.class, () -> new Game(playerX, invalidO));
    }

    // Initial state
    @Test
    void testInitialState() {
        assertEquals(Game.GameState.IN_PROGRESS, game.getGameState());
        assertEquals(playerX, game.getCurrentPlayer());
        assertNotNull(game.getBoard());
    }

    // Make moves
    @Test
    void testValidMoveSwitchesPlayer() {
        assertTrue(game.makeMove(0, 0));
        assertEquals(playerO, game.getCurrentPlayer());
    }

    @Test
    void testInvalidMoveOnOccupiedCell() {
        game.makeMove(0, 0);
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, 0));
    }
    
    // isValidMove
    @Test
    void testIsValidMove() {
        assertTrue(game.isValidMove(1, 1));
        game.makeMove(1, 1);
        assertFalse(game.isValidMove(1, 1));
    }

    // Game over and winner detection
    @Test
    void testGameOverAndWinner() {
        Board board = game.getBoard();
        // X wins
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'X');
        board.placeMark(0, 2, 'X');
        game.makeMove(1, 2); // Trigger state update
        assertTrue(game.isGameOver());
        assertEquals(playerX, game.getWinner());
    }

    @Test
    void testTiedGame() {
        Board board = game.getBoard();
        // Fill board without winner
        char[][] moves = {
                {'X','O','X'},
                {'X','O','O'},
                {'O','X','X'}
        };
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if(r==0 && c==0) continue;
                board.placeMark(r, c, moves[r][c]);
            }
        }
        game.makeMove(0,0); // Trigger updateGameState
        assertTrue(game.isGameOver());
        assertNull(game.getWinner());
        assertEquals(Game.GameState.TIED, game.getGameState());
    }

    // toString
    @Test
    void testToStringContainsCurrentPlayer() {
        String str = game.toString();
        assertTrue(str.contains(playerX.getName()));
        assertTrue(str.contains("Current player"));
    }
}
