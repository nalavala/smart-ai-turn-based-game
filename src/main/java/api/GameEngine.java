package api;

import game.GameResult;
import game.Move;
import board.TikTacToeBoard;
import game.Board;


public class GameEngine {

    public Board start(String gameType) {
        if(gameType.equals("TikTacToeBoard")) {
            return new TikTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board , Move move) {

        if(board instanceof TikTacToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }

    }

}


