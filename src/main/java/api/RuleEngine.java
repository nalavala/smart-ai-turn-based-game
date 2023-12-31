package api;

import board.TikTacToeBoard;
import game.Board;
import game.Cell;
import game.GameState;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class contains the rules and complete logic
 * If any state need to be changed or verified this class is the responsible for that
 */
public class RuleEngine {

    public GameState getState(Board board) {
        final TikTacToeBoard tikTacToeBoard = (TikTacToeBoard) board;
        if(board instanceof TikTacToeBoard) {

            // Row Check
            BiFunction<Integer,Integer, String> getNextRow = (i,j) -> tikTacToeBoard.getSymbol(i, j);
            GameState rowWin = findVictory(getNextRow);
            if(rowWin.isOver()) {
                return rowWin;
            }

            // Col Check
            BiFunction<Integer,Integer, String> getNextColumn = (i,j) -> tikTacToeBoard.getSymbol(j,i);
            GameState colWin = findVictory(getNextColumn);
            if(colWin.isOver()) {
                return colWin;
            }

            // Diagonal
            Function<Integer, String> getNextDiagonal = i -> tikTacToeBoard.getSymbol(i,i);
            GameState diagonalVictory = findDiagonalVictory(getNextDiagonal);
            if(diagonalVictory.isOver()) {
                return diagonalVictory;
            }

            // Reverse Diagonal
            Function<Integer, String> getNextReverseDiagonal = i -> tikTacToeBoard.getSymbol(i,i);
            GameState reverseDiagonalVictory = findDiagonalVictory(getNextDiagonal);
            if(reverseDiagonalVictory.isOver()) {
                return reverseDiagonalVictory;
            }

        }

        return new GameState(false);
    }


    private GameState findDiagonalVictory(Function<Integer, String> next) {
        GameState state = new GameState(false);
        if(traversal(next)) {
            state = new GameState(true, next.apply(0));
        }
        return state;
    }

    private boolean traversal(Function<Integer, String> next) {
        for(int i=0;i<3;i++) {
            if(next.apply(0) != null  && next.apply(0).equalsIgnoreCase(next.apply(i))) {
                return  false;
            }
        }
        return true;
    }

    private GameState findVictory(BiFunction<Integer, Integer, String> next) {

        GameState state = new GameState(false);
        for(int i=0;i<3;i++) {
            int finalI = i;
            Function<Integer, String> traversal = j -> next.apply(finalI,j);
            if(traversal(traversal)) {
                state =  new GameState(true, next.apply(i,0));
            }
        }
        return state;
    }

    private GameState isVictory(TikTacToeBoard tikTacToeBoard, Iterator<Cell> iterator, Iterator<Cell> firstCharIterator) {
        while (firstCharIterator.hasNext()) {
            boolean isPossibleStreak = true;
            Cell firstCell = firstCharIterator.next();
            int firstCol = firstCell.getCol();
            int firstRow = firstCell.getRow();
            String firstSymbol = tikTacToeBoard.getSymbol(firstRow, firstCol);
            if(firstSymbol != null) {
                while (iterator.hasNext()) {
                    Cell cell = iterator.next();
                    int row = cell.getRow();
                    int col = cell.getCol();
                    String currentSymbol =  tikTacToeBoard.getSymbol(row, col);
                    if(currentSymbol != null && !firstSymbol.equalsIgnoreCase(currentSymbol)) {
                        isPossibleStreak = false;
                        break;

                    }
                }

            }

            if(isPossibleStreak) {
                return new GameState(true, firstSymbol);
            }

        }
        return null;
    }



}
