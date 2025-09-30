package lib.src.test;

import lib.src.main.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    // Initialization
    @Test
    void testBoardIsEmptyAfterInitialization() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertTrue(board.isEmpty(r, c));
                assertEquals(' ', board.getMark(r, c));
            }
        }
    }

    // Valid positions
    @Test
    void testIsValidPosition() {
        assertTrue(board.isValidPosition(0, 0));
        assertTrue(board.isValidPosition(2, 2));
        assertFalse(board.isValidPosition(-1, 0));
        assertFalse(board.isValidPosition(0, 3));
    }

    // Place mark
    @Test
    void testPlaceMarkValid() {
        board.placeMark(1, 1, 'X');
        assertEquals('X', board.getMark(1, 1));
        assertFalse(board.isEmpty(1, 1));
    }

    @Test
    void testPlaceMarkInvalidPosition() {
        assertThrows(IllegalArgumentException.class, () -> board.placeMark(3, 0, 'O'));
        assertThrows(IllegalArgumentException.class, () -> board.placeMark(0, -1, 'X'));
    }

    @Test
    void testPlaceMarkInvalidCharacter() {
        assertThrows(IllegalArgumentException.class, () -> board.placeMark(0, 0, 'A'));
    }

    @Test
    void testPlaceMarkOccupiedCell() {
        board.placeMark(0, 0, 'X');
        assertThrows(IllegalArgumentException.class, () -> board.placeMark(0, 0, 'O'));
    }

    // isEmpty
    @Test
    void testIsEmpty() {
        assertTrue(board.isEmpty(0, 1));
        board.placeMark(0, 1, 'O');
        assertFalse(board.isEmpty(0, 1));
    }

    // isFull
    @Test
    void testIsFull() {
        assertFalse(board.isFull());
        // Fill board
        char[][] marks = {{'X','O','X'}, {'O','X','O'}, {'O','X','X'}};
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board.placeMark(r, c, marks[r][c]);
            }
        }
        assertTrue(board.isFull());
    }

    // getWinner
    @Test
    void testWinnerRow() {
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'X');
        board.placeMark(0, 2, 'X');
        assertEquals('X', board.getWinner());
    }

    @Test
    void testWinnerColumn() {
        board.placeMark(0, 1, 'O');
        board.placeMark(1, 1, 'O');
        board.placeMark(2, 1, 'O');
        assertEquals('O', board.getWinner());
    }

    @Test
    void testWinnerDiagonal() {
        board.placeMark(0, 0, 'X');
        board.placeMark(1, 1, 'X');
        board.placeMark(2, 2, 'X');
        assertEquals('X', board.getWinner());
    }

    @Test
    void testWinnerAntiDiagonal() {
        board.placeMark(0, 2, 'O');
        board.placeMark(1, 1, 'O');
        board.placeMark(2, 0, 'O');
        assertEquals('O', board.getWinner());
    }

    @Test
    void testNoWinner() {
        assertEquals(' ', board.getWinner());
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'O');
        assertEquals(' ', board.getWinner());
    }

    // isGameOver
    @Test
    void testIsGameOver() {
        assertFalse(board.isGameOver());
        // Fill board with tie
        char[][] tieMarks = {{'X','O','X'}, {'X','O','O'}, {'O','X','X'}};
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board.placeMark(r, c, tieMarks[r][c]);
            }
        }
        assertTrue(board.isGameOver());
    }

    // toString
    @Test
    void testToStringContainsMarks() {
        board.placeMark(0, 0, 'X');
        board.placeMark(1, 1, 'O');
        String str = board.toString();
        assertTrue(str.contains("X"));
        assertTrue(str.contains("O"));
    }
}
