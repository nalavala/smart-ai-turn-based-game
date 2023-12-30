package api;

import game.Cell;
import game.Move;
import game.Player;
import board.TikTacToeBoard;
import game.Board;

public class AIEngine {


    private  RuleEngine ruleEngine = new RuleEngine();
    public Move suggestMove(Player player, Board board) {

        if(board instanceof TikTacToeBoard) {

            TikTacToeBoard tikTacToeBoard = (TikTacToeBoard) board;
            if(isStarting(tikTacToeBoard, 2)){
                basicMove(tikTacToeBoard, player);
            } else {
                getSmartMove(tikTacToeBoard, player);
            }
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    if (tikTacToeBoard.getSymbol(i, j) == null) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }


    private boolean isStarting(TikTacToeBoard board, int threshold) {
        int count = 0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return  count <= threshold;
    }


    private Move basicMove(TikTacToeBoard board, Player player) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    return move;
                }
            }
        }
        throw new IllegalStateException();

    }

    /*
    1. Can AI win with this move
           - Make the move
    2. Will human win with there next move
            - Block human from winning
     */
    private Move getSmartMove(TikTacToeBoard board, Player player) {

        // Victorous Move
        TikTacToeBoard copyBoard = (TikTacToeBoard) board.clone();
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);

                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver()) {
                        return move;
                    }
                }
            }
        }

        // Defensive Move
        copyBoard = (TikTacToeBoard) board.clone();
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver()) {
                        return move;
                    }
                }
            }
        }


        return basicMove(board, player);

    }
}
