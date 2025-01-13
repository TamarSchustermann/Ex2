import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
        public void testIsNumberWithNull () {
            assertFalse(SCell.isNumber(null), "Null should return false");
        }

        public void testIsNumberWithEmptyString () {
            assertFalse(SCell.isNumber(""), "Empty string should return false");
        }

        public void testIsNumberWithValidIntegers () {
            assertTrue(SCell.isNumber("123"), "Valid integer should return true");
            assertTrue(SCell.isNumber("-123"), "Negative integer should return true");
        }

        public void testIsNumberWithValidDecimals () {
            assertTrue(SCell.isNumber("123.45"), "Valid decimal number should return true");
            assertTrue(SCell.isNumber("-123.45"), "Negative decimal number should return true");
            assertTrue(SCell.isNumber("0.0"), "Zero as a decimal should return true");
        }

        public void testIsNumberWithValidScientificNotation () {
            assertTrue(SCell.isNumber("1e10"), "Scientific notation should return true");
            assertTrue(SCell.isNumber("-1e-10"), "Negative scientific notation should return true");
        }

        public void testIsNumberWithWhitespace () {
            assertTrue(SCell.isNumber("  123  "), "Number with leading/trailing spaces should return true");
            assertTrue(SCell.isNumber("\t123.45\n"), "Number with tabs and newlines should return true");
        }

        public void testIsNumberWithInvalidStrings () {
            assertFalse(SCell.isNumber("abc"), "Non-numeric string should return false");
            assertFalse(SCell.isNumber("123abc"), "String with numbers and letters should return false");
            assertFalse(SCell.isNumber("1.2.3"), "String with multiple dots should return false");
            assertFalse(SCell.isNumber(""), "Empty string should return false");
        }

        public void testIsNumberWithSpecialCharacters () {
            assertFalse(SCell.isNumber("$123"), "String with special characters should return false");
            assertFalse(SCell.isNumber("123,45"), "String with comma instead of dot should return false");
        }
    }



