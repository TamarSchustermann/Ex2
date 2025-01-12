package tamar;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Cell {

    /**
     * this function checks if a given string is a number
     * @param str
     * @return true when str is number
     */

    //isNumber//
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //isFormula//

    /**
     *
     * @param str
     * @return
     */
    public static boolean isFormula(String str) {
        if (str == null || str.isEmpty() || !str.startsWith("=")) {
            return false; // חייב להתחיל ב"="
        }
        // מסירים את ה"=" לבדיקה נוחה יותר
        String formula = str.substring(1);
        // תבנית ל-RegEx שמכסה את הדרישות
        String regex = "^([A-Za-z]\\d{1,2}|\\d+(\\.\\d+)?|\\((.+)\\))([+\\-*/]([A-Za-z]\\d{1,2}|\\d+(\\.\\d+)?|\\((.+)\\)))*$";
        // בדיקת התאמה ל-RegEx
        if (!formula.matches(regex)) {
            return false;
        }
        // בדיקת תקינות סוגריים
        return areParenthesesValid(formula);
    }
    /**
     * בודק אם סוגריים במחרוזת הם תקינים (פותח וסוגר במיקומים נכונים).
     *
     * @param str המחרוזת לבדיקה
     * @return true אם הסוגריים תקינים, אחרת false
     */
    private static boolean areParenthesesValid(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return false; // סוגר סוגריים לפני פותח
                }
            }
        }
        return balance == 0; // חייב להסתיים עם מספר מאוזן של סוגריים
    }

    //isCell//

    /**
     *
     * @param str
     * @return
     */
    public static boolean isCell(String str) {
            if (str == null || str.isEmpty()) {
                return false; // מחרוזת ריקה או null אינה חוקית
            }

            // תבנית לבדיקה: "=" ואחריו אות גדולה/קטנה ומספר בטווח 0-99
            String regex = "^=[A-Za-z](\\d|[1-9]\\d)$";
            return str.matches(regex);
        }
    //isTaxt
    /**
     *
     * @param str
     * @return
     */
    public static boolean isText(String str) {
        return !str.matches(String.valueOf(isFormula(str) && isNumber(str) && isCell(str)));
    }

}
//computeForm
/**
 * מחשב את הערך של פורמולה נתונה.
 *
 * @param form נוסחה בפורמט String, מתחילה ב"="
 * @return הערך המחושב של הפורמולה
 * @throws IllegalArgumentException אם הפורמולה אינה חוקית
 */
public static Double computeForm(String form) {
    if (form == null || form.isEmpty() || !form.startsWith("=")) {
        throw new IllegalArgumentException(Ex2Utils);
    }

    // הסר את סימן ה"=" מהנוסחה
    String expression = form.substring(1).replaceAll("\\s+", ""); // מסיר רווחים מיותרים

    // יצירת מנוע לחישוב ביטויים מתמטיים
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    try {
        // חישוב הביטוי
        Object result = engine.eval(expression);
        return result instanceof Number ? ((Number) result).doubleValue() : null;
    } catch (ScriptException e) {
        throw new IllegalArgumentException("Error evaluating formula: " + form, e);
    }
}

