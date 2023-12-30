package api;

import game.Board;
import game.GameResult;

/**
 * This class contains the rules and complete logic
 * If any state need to be changed or verified this class is the responsible for that
 */
public class RuleEngine {

    public GameResult getState(Board board) {
        return new GameResult();
    }
}
