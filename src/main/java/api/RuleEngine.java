package api;

import board.TikTacToeBoard;
import game.Board;
import game.GameResult;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class contains the rules and complete logic
 * If any state need to be changed or verified this class is the responsible for that
 */
public class RuleEngine {

    public GameResult getState(Board board) {






        final TikTacToeBoard tikTacToeBoard = (TikTacToeBoard) board;
        if(board instanceof TikTacToeBoard) {
            // Row Check

            Function<Integer, String> firstRowValueSupplier = i -> tikTacToeBoard.getSymbol(i, 0);
            Function<Integer, String> firstColumnValueSupplier = i -> tikTacToeBoard.getSymbol(0, i);

            BiFunction<Integer,Integer, String> getNextRow = (i,j) -> tikTacToeBoard.getSymbol(i, j);

            BiFunction<Integer,Integer, String> getNextColumn = (i,j) -> tikTacToeBoard.getSymbol(j,i);



            boolean isSomeoneWon = true;
            GameResult rowWin = isVictory(getNextRow);
            if(rowWin != null) {
                return rowWin;
            }

            GameResult colWin = isVictory(getNextColumn);
            if(colWin != null) {
                return colWin;
            }

            isSomeoneWon = true;
            for(int i=0;i<3;i++) {
                String firstCharacter = firstColumnValueSupplier.apply( i);
                if(firstCharacter != null) {
                    for(int j=1;j<3;j++) {
                        if(!firstCharacter.equalsIgnoreCase(getNextColumn.apply(j,i))) {
                            isSomeoneWon = false;
                            break;
                        }
                    }
                }

                if(isSomeoneWon) {
                    return new GameResult(true, firstCharacter);
                }

            }
            // Column check

            // Diagonal check
            // Reverse diagonal check
        }



        return new GameResult(false);
    }


    private GameResult isVictory(BiFunction<Integer, Integer, String> next) {
        boolean isPossibleStreak = true;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(next.apply(i,j) != null && !next.apply(i,0).equalsIgnoreCase(next.apply(i, j))) {
                    isPossibleStreak = false;
                    break;
                }
            }

            if(isPossibleStreak) {
                return new GameResult(true, next.apply(i,0));
            }

        }
        return null;
    }




}
