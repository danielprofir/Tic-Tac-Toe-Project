package lib.src.main;

import java.util.Objects;

/**
 * Computer-controlled player with configurable strategy.
 */
public class ComputerPlayer extends Player {

    public enum Strategy {
        RANDOM,
        SMART
    }

    private final Strategy strategy;

    public ComputerPlayer(String name, char mark, Strategy strategy) {
        super(name, mark);
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public Strategy getStrategy() {
        return strategy;
    }
}


