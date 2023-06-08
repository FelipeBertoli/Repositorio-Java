package src.model;

import java.util.ArrayList;
import java.util.List;
import src.exception.*;

public class Field {

    private final int line;
    private final int column;

    private boolean open = false;
    private boolean mined = false;
    private boolean marked = false;

    private List<Field> neighbors = new ArrayList<>();
    public boolean addNeighbor;

    public Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public boolean addNeighbor(Field neighbor) {
        boolean diffLine = line != neighbor.line;
        boolean diffColumn = line != neighbor.column;
        boolean diagonal = diffLine && diffColumn;

        int deltaLine = Math.abs(line - neighbor.line);
        int deltaColumn = Math.abs(line - neighbor.column);
        int deltaGeral = deltaColumn + deltaLine;

        if (deltaGeral == 1 && !diagonal) {
            neighbors.add(neighbor);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        } else {
            return false;
        }
    }

    public void switchMark() {
        if (!open) {
            marked = !marked;
        }
    }

    public boolean open() {
        if (!open && !marked) {
            open = true;
            if (mined) {
                throw new ExplosionException();
            }

            if (neighborhoodSecurity()) {
                neighbors.forEach(v -> v.open());
            }
            return true;
        } else {
            return false;
        }

    }

    boolean neighborhoodSecurity() {
        return neighbors.stream().noneMatch(v -> v.mined);
    }

    public void mine() {
        mined = true;
    }

    public boolean isMined(){
        return mined;
    }
    
    public boolean isMarked() {
        return marked;
    }

    void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return !isOpen();
    }

    public int getLine() {
        return line;
    }

    public int getColumn(){
        return column;
    }

    boolean objectiveReached(){
        boolean uncovered = !mined && open;
        boolean covered = mined && marked;
        return uncovered || covered;
    }

    long neighborhoodMines() {
        return neighbors.stream().filter(v -> v.mined).count(); 
    }

    void restart(){
        open = false;
        mined = false;
        marked = false;
    }

    public String toString(){
        if(marked) {
            return "x";
        } else if(open && mined) {
            return "*";
        } else if(open && neighborhoodMines() > 0) {
            return Long.toString(neighborhoodMines());
        } else if(open) {
            return " ";
        } else {
            return "?";
        }
    }
}
