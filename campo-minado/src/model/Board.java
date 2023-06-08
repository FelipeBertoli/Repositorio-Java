package src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import src.exception.ExplosionException;

public class Board {
    private int lines;
    private int columns;
    private int mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        createFields();
        neighborsAssociation();
        minesSort();
    }

    public void open(int line, int column) {
        try{
            fields.parallelStream()
            .filter(c -> c.getLine() == line && c.getColumn() == column)
            .findFirst().ifPresent(c ->c.open());
        } catch(ExplosionException e) {
            fields.forEach(c -> c.setOpen(true));
            throw e;
        }
    }

    public void switchMark(int line, int column) {
        fields.parallelStream()
            .filter(c -> c.getLine() == line && c.getColumn() == column)
            .findFirst().ifPresent(c ->c.switchMark());
    }
    
    private void createFields() {
        for(int line = 0; line < lines; line++) {
            for(int column = 0; column < columns; column++) {
                fields.add(new Field(line, column));
            }
        }
    }

    private void neighborsAssociation() {
        for(Field c1: fields) {
            for (Field c2: fields) {
                c1.addNeighbor(c2);
            }
        }
    }
        
    private void minesSort() {
        long armedMines = 0;
        Predicate<Field> mined = c -> c.isMined();
        do {
            armedMines = fields.stream().filter(mined).count();
            int random = (int) (Math.random() * fields.size());
            fields.get(random).mine();
        } while (armedMines < mines);
    }

    public boolean objectiveReached(){
        return fields.stream().allMatch(c -> c.objectiveReached());
    }

    public void restart(){
        fields.stream().forEach(c -> c.restart());
        minesSort();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for(int c = 0 ; c < columns; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");

        }
        sb.append("\n");

        int i = 0;
        for(int line = 0; line < lines; line++) {
            sb.append(line);
            sb.append(" ");
            for(int column = 0; column < columns; column++) {
                    sb.append(" ");
                    sb.append(fields.get(i));
                    sb.append(" ");
                    i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
