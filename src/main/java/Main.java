

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine engine = new RuleEngine();
        // Start game
        Board board = gameEngine.start("TikTacToeBoard");

        Scanner scan = new Scanner(System.in);
        while(!engine.isComplete(board).isOver()) {
            System.out.println("Move your Move");
            Player human = new Player("X", "Human");
            Player robo = new Player("O", "robo");
            int row = scan.nextInt();
            int col = scan.nextInt();

            Move move = new Move(new Cell(row, col), human);

            // Human move
            gameEngine.move(board, move);

            if(engine.isComplete(board).isOver()) {
                Move opponentMove = aiEngine.suggestMove(robo, board);
                gameEngine.move(board, opponentMove);
            }
        }

        System.out.println("Game Result:" + engine.isComplete(board));
    }
}
