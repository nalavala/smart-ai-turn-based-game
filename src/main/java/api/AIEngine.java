package api;

import game.Cell;
import game.Move;
import game.Player;
import board.TikTacToeBoard;
import game.Board;

public class AIEngine {

    public Move suggestMove(Player player, Board board) {

        if(board instanceof TikTacToeBoard) {

            TikTacToeBoard tikTacToeBoard = (TikTacToeBoard) board;
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    if (tikTacToeBoard.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
