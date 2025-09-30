package lib.src.test;

import lib.src.main.ComputerPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void constructor_setsFieldsCorrectly() {
        ComputerPlayer player = new ComputerPlayer("AI", 'X', ComputerPlayer.Strategy.RANDOM);
        assertEquals("AI", player.getName());
        assertEquals('X', player.getMark());
        assertEquals(ComputerPlayer.Strategy.RANDOM, player.getStrategy());
    }

    @Test
    void getStrategy_returnsCorrectStrategy() {
        ComputerPlayer player = new ComputerPlayer("SmartBot", 'O', ComputerPlayer.Strategy.SMART);
        assertEquals(ComputerPlayer.Strategy.SMART, player.getStrategy());
    }

    @Test
    void constructor_throwsNullPointerExceptionIfStrategyNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new ComputerPlayer("AI", 'X', null));
        assertEquals("strategy", exception.getMessage());
    }
}
