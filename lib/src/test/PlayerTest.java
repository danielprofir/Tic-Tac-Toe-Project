package lib.src.test;

import lib.src.main.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    // Constructor tests
    @Test
    void testValidPlayerCreation() {
        Player player = new Player("Alice", 'X');
        assertEquals("Alice", player.getName());
        assertEquals('X', player.getMark());
    }

    @Test
    void testConstructorTrimsName() {
        Player player = new Player("  Bob  ", 'O');
        assertEquals("Bob", player.getName());
    }

    @Test
    void testConstructorRejectsNullName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Player(null, 'X')
        );
        assertEquals("Player name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testConstructorRejectsEmptyName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Player("   ", 'O')
        );
        assertEquals("Player name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testConstructorRejectsInvalidMark() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Player("Alice", 'A')
        );
        assertEquals("Mark must be 'X' or 'O', got: A", exception.getMessage());
    }

    // toString test
    @Test
    void testToString() {
        Player player = new Player("Charlie", 'X');
        assertEquals("Charlie (X)", player.toString());
    }

    // equals and hashCode tests
    @Test
    void testEqualsSameObject() {
        Player player = new Player("Dana", 'O');
        assertEquals(player, player); // should be equal to itself
    }

    @Test
    void testEqualsEqualObjects() {
        Player player1 = new Player("Eve", 'X');
        Player player2 = new Player("Eve", 'X');
        assertEquals(player1, player2);
        assertEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testEqualsDifferentName() {
        Player player1 = new Player("Frank", 'X');
        Player player2 = new Player("Grace", 'X');
        assertNotEquals(player1, player2);
    }

    @Test
    void testEqualsDifferentMark() {
        Player player1 = new Player("Hank", 'X');
        Player player2 = new Player("Hank", 'O');
        assertNotEquals(player1, player2);
    }

    @Test
    void testEqualsNullAndOtherClass() {
        Player player = new Player("Ivy", 'O');
        assertNotEquals(player, null);
        assertNotEquals(player, "NotAPlayer");
    }
}
