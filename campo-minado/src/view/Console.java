package src.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import src.exception.ExitException;
import src.exception.ExplosionException;
import src.model.Board;

public class Console {
    private Board board;
    private Scanner read = new Scanner(System.in);

    public Console(Board board) {
        this.board = board;
        execute();
    }

    private void execute() {
        try {
            boolean proceed = true;

            while (proceed) {
                gameCicle();
                System.out.println("--> OUTRA PARTIDA (S/n) ");
                String answer = read.nextLine();

                if ("n".equalsIgnoreCase(answer)) {
                    proceed = false;
                } else {
                    board.restart();
                }
            }

        } catch (ExitException e) {
            System.out.println("--> FINALIZADO!!!");
        } finally {
            read.close();
        }
    }

    private void gameCicle() {
        try {
            while (!board.objectiveReached()) {

                System.out.println(board);
                String typed = captureTypedValor("--> DIGITE (x, y): ");
                Iterator<Integer> xy = Arrays.stream(typed.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();
                        typed = captureTypedValor("--> 1. ABRIR ou 2. (DES)MARCAR: ");
                
                        if("1".equals(typed)) {
                            board.open(xy.next(), xy.next());
                        } else if("2".equals(typed)) {
                            board.switchMark(xy.next(), xy.next());
                        }

            }

            System.out.println("--> VOCÊ GANHOU!!!");

        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("--> VOCÊ PERDEU!!!");

        }
    }

    private String captureTypedValor(String text) {
        System.out.print(text);
        String typed = read.nextLine();

        if ("exit".equalsIgnoreCase(typed)) {
            throw new ExitException();
        }

        return typed;
    }

}
