package board;


import game.Board;
import game.Cell;
import game.Move;

public class TikTacToeBoard extends Board {

    private String[][] cells = new String[3][3];


    public TikTacToeBoard() {

    }

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }


    @Override
    public void move(Move move) {
        if(cells[move.getCell().getRow()][move.getCell().getCol()] != null) {
            throw new IllegalStateException();
        }
        setCell(move.getCell(), move.getPlayer().getSymbol());
    }

    public Board clone() {
        TikTacToeBoard board = new TikTacToeBoard();
        for(int i=0;i<3;i++) {
            System.arraycopy(cells[i],0, board.cells[i], 0, 3);
        }

        return board;
    }
}
