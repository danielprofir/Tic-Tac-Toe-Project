package lib.src.main;

/**
 * Represents a player in the Tic-Tac-Toe game.
 * Each player has a name and a mark ('X' or 'O').
 */
public class Player {
    private final String name;
    private final char mark;


    /**
     * Creates a new player with the specified name and mark.
     * @param name The player's name
     * @param mark The player's mark ('X' or 'O')
     * @throws IllegalArgumentException In case name or mark are invalid
     */
    public Player(String name, char mark){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (mark != 'X' && mark != 'O') {
            throw new IllegalArgumentException("Mark must be 'X' or 'O', got: " + mark);
        }
        this.name = name.trim();
        this.mark = mark;
    }

    /**
     * Gets the player's name.
     * @return player's name as String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's mark.
     * @return player's mark as char
     */
    public char getMark() {
        return mark;
    }

    /**
     * Returns a string representation of the player.
     * @return a formatted string with player's name and mark
     */
    @Override
    public String toString(){
        return name + " (" + mark + ")";
    }

    /**
     * Checks if this player equals another object.
     * Players are considered equal if they have the same name and mark.
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Player player = (Player) obj;
        return mark == player.mark && name.equals(player.name);
    }


    /**
     * Returns a hash code for this player.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) mark;
        return result;
    }
}