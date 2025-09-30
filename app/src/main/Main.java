package app.src.main;

import lib.src.main.Board;
import lib.src.main.Game;
import lib.src.main.Player;
import lib.src.main.ComputerPlayer;
import lib.src.main.AIMoveSelector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /**
     * Entry point for the Tic-Tac-Toe game.
     * Handles player setup (human or computer), game loop, and displaying the board.
     */
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Get player types and names
            String typeX;
            while (true) {
                System.out.print("Is Player X human or computer? [h/c]: ");
                System.out.flush();
                typeX = readLineOrDefault(input, "h").toLowerCase();
                if ("h".equals(typeX) || "c".equals(typeX)) break;
                System.out.println("Please enter 'h' or 'c'.");
            }

            System.out.print("Enter name for Player X: ");
            System.out.flush();
            String nameX = readLineOrDefault(input, typeX.equals("c") ? "Computer X" : "Player X");

            String typeO;
            while (true) {
                System.out.print("Is Player O human or computer? [h/c]: ");
                System.out.flush();
                typeO = readLineOrDefault(input, "h").toLowerCase();
                if ("h".equals(typeO) || "c".equals(typeO)) break;
                System.out.println("Please enter 'h' or 'c'.");
            }

            System.out.print("Enter name for Player O: ");
            System.out.flush();
            String nameO = readLineOrDefault(input, typeO.equals("c") ? "Computer O" : "Player O");

            // If computer selected, choose strategy
            String stratX = null;
            if ("c".equals(typeX)) {
                System.out.print("Should Player X be smart or random? [s/r]: ");
                System.out.flush();
                stratX = readLineOrDefault(input, "s").toLowerCase();
            }
            String stratO = null;
            if ("c".equals(typeO)) {
                System.out.print("Should Player O be smart or random? [s/r]: ");
                System.out.flush();
                stratO = readLineOrDefault(input, "s").toLowerCase();
            }

            // Create game
            Player playerX = createPlayer(nameX, 'X', typeX, stratX);
            Player playerO = createPlayer(nameO, 'O', typeO, stratO);
            Game game = new Game(playerX, playerO);

            System.out.println();
            System.out.println("Starting Tic-Tac-Toe: " + game.getPlayerX().getName() + " (X) vs " + game.getPlayerO().getName() + " (O)");
            System.out.println(renderBoard(game.getBoard()));

            // Game loop
            while (true) {
                Game.GameState status = game.getGameState();

                if (status == Game.GameState.WON) {
                    Player winner = game.getWinner();
                    System.out.println("Winner: " + winner.getName() + " (" + winner.getMark() + ")");
                    break;
                } else if (status == Game.GameState.TIED) {
                    System.out.println("It's a draw.");
                    break;
                } else if (status == Game.GameState.IN_PROGRESS) {
                    Player currentPlayer = game.getCurrentPlayer();
                    boolean isComputer = currentPlayer instanceof ComputerPlayer;
                    int row;
                    int col;

                    if (isComputer) {
                        ComputerPlayer ai = (ComputerPlayer) currentPlayer;
                        int[] move;
                        if (ai.getStrategy() == ComputerPlayer.Strategy.SMART) {
                            move = AIMoveSelector.selectHeuristicMove(game.getBoard(), ai.getMark());
                            if (move == null) move = AIMoveSelector.selectRandomMove(game.getBoard());
                        } else {
                            move = AIMoveSelector.selectRandomMove(game.getBoard());
                        }
                        if (move == null) {
                            System.out.println("No available moves. It's a draw.");
                            break;
                        }
                        row = move[0];
                        col = move[1];
                        System.out.println(currentPlayer.getName() + " (" + currentPlayer.getMark() + ") plays: " + row + "," + col);
                    } else {
                        System.out.print(currentPlayer.getName() + " (" + currentPlayer.getMark() + "), enter your move as row,col [0,2]: ");
                        System.out.flush();

                        String line = input.readLine();
                        if (line == null) {
                            System.out.println();
                            System.out.println("No input detected. Exiting.");
                            break;
                        }

                        String[] parts = line.trim().split(",");
                        if (parts.length != 2) {
                            System.out.println("Invalid input. Please enter as row,col (Example: 1,2)");
                            continue;
                        }
                        try {
                            row = Integer.parseInt(parts[0].trim());
                            col = Integer.parseInt(parts[1].trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid numbers. Try again.");
                            continue;
                        }
                    }

                    try {
                        boolean moveSuccessful = game.makeMove(row, col);
                        if (moveSuccessful) {
                            System.out.println();
                            System.out.println(renderBoard(game.getBoard()));

                            if (game.getGameState() == Game.GameState.WON) {
                                Player winner = game.getWinner();
                                System.out.println("Winner: " + winner.getName() + " (" + winner.getMark() + ")");
                                break;
                            } else if (game.getGameState() == Game.GameState.TIED) {
                                System.out.println("It's a draw.");
                                break;
                            }
                        } else {
                            System.out.println("Game is already over. Invalid move.");
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + ". Try again.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }

    /**
     * Reads a line from the given BufferedReader, returning a default value if input is empty or null.
     * @param input        the BufferedReader to read from
     * @param defaultValue the value to return if input is empty or null
     * @return the trimmed line entered by the user, or the default value if no input
     * @throws IOException if an I/O error occurs
     */
    private static String readLineOrDefault(BufferedReader input, String defaultValue) throws IOException {
        String line = input.readLine();
        return (line == null || line.trim().isEmpty()) ? defaultValue : line.trim();
    }

    /**
     * Returns a string representation of the board in a human-readable format.
     * @param board the game board
     * @return formatted string representing the board
     */
    private static String renderBoard(Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append("   0   1   2\n");
        for (int row = 0; row < 3; row++) {
            sb.append(row).append("  ");
            for (int col = 0; col < 3; col++) {
                char mark = board.getMark(row, col);
                sb.append(mark == ' ' ? ' ' : mark);
                if (col < 2) {
                    sb.append(" | ");
                }
            }
            if (row < 2) {
                sb.append("\n  -----------\n");
            }
        }
        return sb.toString();
    }

    /**
     * Creates a Player instance based on type and strategy.
     * @param name     the player's name
     * @param mark     the player's mark ('X' or 'O')
     * @param type     "h" for human, "c" for computer
     * @param strategy "s" for smart, "r" for random (ignored for human)
     * @return a Player instance, either Human (Player) or ComputerPlayer
     */
    private static Player createPlayer(String name, char mark, String type, String strategy) {
        if ("c".equalsIgnoreCase(type)) {
            ComputerPlayer.Strategy chosen = ("r".equalsIgnoreCase(strategy))
                    ? ComputerPlayer.Strategy.RANDOM
                    : ComputerPlayer.Strategy.SMART;
            return new ComputerPlayer(name, mark, chosen);
        }
        return new Player(name, mark);
    }
}