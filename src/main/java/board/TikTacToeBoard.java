package board;


import game.Board;
import game.Cell;

public class TikTacToeBoard extends Board {

    private String[][] cells = new String[3][3];


    public TikTacToeBoard() {

    }

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }





}