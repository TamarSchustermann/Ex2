import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class Ex2SheetTest {
    @Test
    public void testIsIn() {
        Ex2Sheet sheet = new Ex2Sheet(5, 5);
        assertTrue(sheet.isIn(0, 0));
        assertTrue(sheet.isIn(4, 4));
        assertTrue(sheet.isIn(2, 3));
        assertFalse(sheet.isIn(-1, 0));
        assertFalse(sheet.isIn(0, -1));
        assertFalse(sheet.isIn(5, 0));
        assertFalse(sheet.isIn(0, 5));
        assertFalse(sheet.isIn(6, 6));
    }
    @Test
    public void testEval() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 0, "5");
        sheet.set(0, 1, "=A1+3");
        sheet.set(0, 2, "=A2*2");
        sheet.set(1, 0, "=A1+A2");
        sheet.set(2, 2, "=C3");
        sheet.eval();
        assertEquals("5", sheet.value(0, 0));
        assertEquals("8.0", sheet.value(0, 1));
        assertEquals("Error", sheet.value(0, 2));
        assertEquals("5.0", sheet.value(1, 0));
        assertEquals("Error", sheet.value(2, 2));
    }
    @Test
    public void testValue() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 0, "5");
        sheet.set(1, 1, "Hello");
        sheet.set(2, 2, "=A1+2");
        assertEquals("5", sheet.value(0, 0));
        assertEquals("Hello", sheet.value(1, 1));
        assertEquals("=A1+2", sheet.value(2, 2));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(0, 1));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(2, 1));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(-1, 0));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(3, 3));
    }
    @Test
    public void testDepth() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 1, "5");
        sheet.set(0, 2, "=A1+3");
        sheet.set(0, 3, "=A1+A2");
        int[][] depthMatrix = sheet.depth();
        assertEquals(0, depthMatrix[0][1]);
        assertEquals(1, depthMatrix[0][2]);
        assertEquals(2, depthMatrix[0][3]);
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(-1, 0));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(3, 3));
    }
    @Test
    public void testStringEval() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(0, 0, "5");
        sheet.set(0, 1, "=A1+3");
        sheet.set(1, 0, "=A1+A2");
        String evalA1 = sheet.eval(0, 0);
        String evalA2 = sheet.eval(0, 1);
        String evalA3 = sheet.eval(1, 0);
        assertEquals("5", evalA1);
        assertEquals("8", evalA2);
        assertEquals("5", evalA3);
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.eval(2, 2));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.eval(-1, 0));
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.eval(3, 3));
    }
    @Test
    public void testSet() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(1, 1, "Hello");
        assertEquals("Hello", sheet.value(1, 1));
        sheet.set(0, 0, "World");
        assertEquals("World", sheet.value(0, 0));
        sheet.set(-1, -1, "OutOfBounds");
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(-1, -1));
        sheet.set(3, 3, "OutOfBounds");
        assertEquals(Ex2Utils.EMPTY_CELL, sheet.value(3, 3));
    }

    @Test
    public void testGet() {
        Ex2Sheet sheet = new Ex2Sheet(3, 3);
        sheet.set(1, 1, "Hello");
        Cell cell = sheet.get("B2");
        assertNotNull(cell);
        assertEquals("Hello", cell.toString());
        cell = sheet.get("C3");
        assertNull(cell);
        cell = sheet.get("D4");
        assertNull(cell);
        cell = sheet.get("A-1");
        assertNull(cell);
    }
}