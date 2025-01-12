//package assignments.ex2;
// Add your documentation below:

public class CellEntry  implements Index2D {

    @Override
    public boolean isValid() {
            // בדיקה אם המחרוזת היא בפורמט התקין
        String cellIndex = "";
        if (cellIndex == null || cellIndex.isEmpty()) {
                return false;
            }
            // שימוש בביטוי רגולרי כדי לבדוק את התקינות
            return cellIndex.matches("^[A-Za-z][0-9]{1,2}$");
    }
    @Override
    public int getX() {
        String index = "";
        if (index == null || index.isEmpty() || !index.matches("^[A-Za-z]\\d+$")) {
            throw new IllegalArgumentException("Invalid index format: " + index);
        }

        char letter = index.charAt(0); // האות הראשונה במחרוזת
        if (Character.isUpperCase(letter)) {
            return letter - 'A'; // ממיר אות גדולה לאינדקס מבוסס 0
        } else {
            return letter - 'a'; // ממיר אות קטנה לאינדקס מבוסס 0
        }
    }

    @Override
    public int getY() {
        String index = "";
        if (index == null || index.isEmpty() || !index.matches("^[A-Za-z]\\d+$")) {
            throw new IllegalArgumentException("Invalid index format: " + index);
        }
        return Ex2Utils.ERR;
    }
}