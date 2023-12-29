

import api.AIEngine;
import api.GameEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        // Start game
        Board board = gameEngine.start("TikTacToeBoard");

        Scanner scan = new Scanner(System.in);
        while(!gameEngine.isComplete(board).isOver()) {
            System.out.println("Move your Move");
            Player human = new Player("X", "Human");
            Player robo = new Player("O", "robo");
            int row = scan.nextInt();
            int col = scan.nextInt();

            Move move = new Move(new Cell(row, col), human);

            // Human move
            gameEngine.makeMove(board, move);

            if(gameEngine.isComplete(board).isOver()) {
                Move opponentMove = aiEngine.suggestMove(robo, board);
                gameEngine.makeMove(board, opponentMove);
            }
        }

        System.out.println("Game Result:" + gameEngine.isComplete(board));
    }
}
