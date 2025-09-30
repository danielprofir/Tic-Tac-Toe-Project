//package lib.src.test;
//
//import lib.src.main.AIMoveSelector;
//import lib.src.main.Board;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AIMoveSelectorTest {
//
//    private Board board;
//
//    @BeforeEach
//    void setup() {
//        board = mock(Board.class);
//    }
//
//    @Test
//    void selectRandomMove_returnsNullWhenBoardFull() {
//        // All cells are occupied
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                when(board.isEmpty(r, c)).thenReturn(false);
//            }
//        }
//
//        assertNull(AIMoveSelector.selectRandomMove(board));
//    }
//
//    @Test
//    void selectRandomMove_returnsEmptyCell() {
//        // Only (0,0) is empty
//        when(board.isEmpty(0, 0)).thenReturn(true);
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                if (r != 0 || c != 0) when(board.isEmpty(r, c)).thenReturn(false);
//            }
//        }
//
//        int[] move = AIMoveSelector.selectRandomMove(board);
//        assertArrayEquals(new int[]{0, 0}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_winsIfPossible() {
//        // AI is 'X', can win at (0,2)
//        when(board.isEmpty(0, 2)).thenReturn(true);
//        when(board.getMark(0, 0)).thenReturn('X');
//        when(board.getMark(0, 1)).thenReturn('X');
//        when(board.getMark(0, 2)).thenReturn(' ');
//
//        int[] move = AIMoveSelector.selectHeuristicMove(board, 'X');
//        assertArrayEquals(new int[]{0, 2}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_blocksOpponentWin() {
//        // Opponent 'O' can win at (2,0)
//        when(board.isEmpty(2, 0)).thenReturn(true);
//        when(board.getMark(2, 1)).thenReturn('O');
//        when(board.getMark(2, 2)).thenReturn('O');
//        when(board.getMark(2, 0)).thenReturn(' ');
//
//        int[] move = AIMoveSelector.selectHeuristicMove(board, 'X');
//        assertArrayEquals(new int[]{2, 0}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_takesCenterIfAvailable() {
//        // Center is empty
//        when(board.isEmpty(1, 1)).thenReturn(true);
//
//        int[] move = AIMoveSelector.selectHeuristicMove(board, 'X');
//        assertArrayEquals(new int[]{1, 1}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_takesCornerIfCenterBlocked() {
//        // Center blocked, top-left empty
//        when(board.isEmpty(1, 1)).thenReturn(false);
//        when(board.isEmpty(0, 0)).thenReturn(true);
//        when(board.isEmpty(0, 2)).thenReturn(false);
//        when(board.isEmpty(2, 0)).thenReturn(false);
//        when(board.isEmpty(2, 2)).thenReturn(false);
//
//        int[] move = AIMoveSelector.selectHeuristicMove(board, 'X');
//        assertArrayEquals(new int[]{0, 0}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_takesEdgeIfNoBetterOption() {
//        // Only edges available
//        when(board.isEmpty(1, 0)).thenReturn(true);
//        when(board.isEmpty(0, 1)).thenReturn(false);
//        when(board.isEmpty(1, 2)).thenReturn(false);
//        when(board.isEmpty(2, 1)).thenReturn(false);
//
//        // Center and corners blocked
//        when(board.isEmpty(1, 1)).thenReturn(false);
//        when(board.isEmpty(0, 0)).thenReturn(false);
//        when(board.isEmpty(0, 2)).thenReturn(false);
//        when(board.isEmpty(2, 0)).thenReturn(false);
//        when(board.isEmpty(2, 2)).thenReturn(false);
//
//        int[] move = AIMoveSelector.selectHeuristicMove(board, 'X');
//        assertArrayEquals(new int[]{1, 0}, move);
//    }
//
//    @Test
//    void selectHeuristicMove_returnsNullWhenBoardFull() {
//        // All cells full
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                when(board.isEmpty(r, c)).thenReturn(false);
//            }
//        }
//
//        assertNull(AIMoveSelector.selectHeuristicMove(board, 'X'));
//    }
//}
