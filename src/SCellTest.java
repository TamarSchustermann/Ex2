import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SCellTest {

    @Test
    void testSetData_Number() {
        SCell cell = new SCell("123.45");
        assertEquals(Ex2Utils.NUMBER, cell.getType());
        assertEquals("123.45", cell.getData());
    }

    @Test
    void testSetData_Text() {
        SCell cell = new SCell("Hello World");
        assertEquals(Ex2Utils.TEXT, cell.getType());
        assertEquals("Hello World", cell.getData());
    }

    @Test
    void testSetData_Formula() {
        SCell cell = new SCell("=A1+2");
        assertEquals(Ex2Utils.FORM, cell.getType());
        assertEquals("=A1+2", cell.getData());
    }

    @Test
    void testSetData_InvalidFormula() {
        SCell cell = new SCell("=A1++");
        assertEquals(Ex2Utils.TEXT, cell.getType());
        assertEquals("=A1++", cell.getData());
    }

    @Test
    void testSetOrder() {
        SCell cell = new SCell("123");
        cell.setOrder(5);
        assertEquals(5, cell.getOrder());
    }

    @Test
    void testToString() {
        SCell cell = new SCell("Example");
        assertEquals("Example", cell.toString());
    }

    @Test
    void testIsNumber_Valid() {
        assertTrue(SCell.isNumber("123.45"));
        assertTrue(SCell.isNumber("-987"));
        assertTrue(SCell.isNumber("0"));
    }

    @Test
    void testIsNumber_Invalid() {
        assertFalse(SCell.isNumber("abc"));
        assertFalse(SCell.isNumber(""));
        assertFalse(SCell.isNumber(null));
    }

    @Test
    void testIsFormula_Valid() {
        assertTrue(SCell.isFormula("=A1+2"));
        assertTrue(SCell.isFormula("=3*(2+4)/A3"));
    }

    @Test
    void testIsFormula_Invalid() {
        assertFalse(SCell.isFormula("A1+2"));
        assertFalse(SCell.isFormula("=A1++"));
        assertFalse(SCell.isFormula(null));
    }

    @Test
    void testIsCell_Valid() {
        assertTrue(SCell.isCell("=A1"));
        assertTrue(SCell.isCell("=Z99"));
    }

    @Test
    void testIsCell_Invalid() {
        assertFalse(SCell.isCell("A1"));
        assertFalse(SCell.isCell("=A100"));
        assertFalse(SCell.isCell("=123"));
    }

    @Test
    void testComputeForm_Valid() {
        assertEquals(7.0, SCell.computeForm("=3+4"));
        assertEquals(14.0, SCell.computeForm("=2*(3+4)"));
        assertEquals(2.5, SCell.computeForm("=5/2"));
    }

    @Test
    void testComputeForm_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> SCell.computeForm("3+4"));
        assertThrows(IllegalArgumentException.class, () -> SCell.computeForm("=3/0"));
        assertThrows(IllegalArgumentException.class, () -> SCell.computeForm(null));
    }
}
