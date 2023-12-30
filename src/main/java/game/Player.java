package game;

public class Player {


    private String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public Player flip() {
        if(this.symbol == "X") {
            return new Player("O");
        } else {
            return new Player("X");
        }
    }
}
