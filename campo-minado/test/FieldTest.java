package test;

import src.exception.ExplosionException;
import src.model.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class FieldTest {

    private Field field;

    @BeforeEach
    void startField() {
        field = new Field(3, 3);
    }

    @Test
    void testNeighborDistance1Left() {
        Field neighbor = new Field(3, 2);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void testNeighborDistance1Right() {
        Field neighbor = new Field(3, 4);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void testNeighborDistance1Up() {
        Field neighbor = new Field(2, 3);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void testNeighborDistance1Down() {
        Field neighbor = new Field(4, 3);
        boolean result = field.addNeighbor(neighbor);

        assertTrue(result);
    }

    @Test
    void testNeighborDistance2() {
        Field neighbor = new Field(2, 2);
        boolean result = field.addNeighbor(neighbor);
        assertTrue(result);
    }
    
    @Test
    void testNotNeighbor() {
        Field neighbor = new Field(1, 1);
        boolean result = field.addNeighbor(neighbor);
        assertFalse(result);
    }

    @Test
    void testStandardValor(){
        assertFalse(field.isMarked());
    }

    @Test
    void testSwitchMark(){
        field.switchMark();
        assertTrue(field.isMarked());
    }

    @Test
    void testSwitchMarkTwice(){
        field.switchMark();
        field.switchMark();
        assertFalse(field.isMarked());

    }

    @Test
    void testOpenNotMineNotMark(){
        assertTrue(field.open());
    }

    @Test
    void testOpenNotMineMark(){
        field.switchMark();
        assertFalse(field.open());
    }

    @Test
    void testOpenMineMark() {
        field.switchMark();
        field.mine();
        assertFalse(field.open());
    }

    @Test
    void testOpenMineNotMark() {
        field.mine(); 
        assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

    @Test
    void testOpenWNeighbors(){
        Field field11 = new Field(1,1);
        Field field12 = new Field(1, 1);
        field12.mine();

        Field field22 = new Field(2,2);
        field22.addNeighbor(field11);
        field22.addNeighbor(field12);

        field.addNeighbor(field22);
        field.open();
        assertTrue(field22.isOpen() && field11.isOpen());
    }
    
}
