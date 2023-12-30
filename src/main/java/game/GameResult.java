package game;

public class GameResult {

    private boolean isOver;
    private String symbol;

    public GameResult(boolean isOver, String player) {
        this.isOver = isOver;
        this.symbol = player;
    }

    public GameResult(boolean isOver) {
        this.isOver = isOver;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
