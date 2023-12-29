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

    public void makeMove(Board board , Move move) {

        if(board instanceof TikTacToeBoard) {
            TikTacToeBoard tikTacToeBoard = (TikTacToeBoard) board;
            tikTacToeBoard.setCell(move.getCell(), move.getPlayer().getSymbol());
        } else {
            throw new IllegalArgumentException();
        }

    }

    public GameResult isComplete(Board board) {
        return new GameResult();
    }
}


