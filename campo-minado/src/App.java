package src;

import src.model.Board;
import src.view.Console;

public class App {
    public static void main(String[] args) {
        Board board = new Board(6, 6, 3);   
        new Console(board);
    }
}
