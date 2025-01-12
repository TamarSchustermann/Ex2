
// Add your documentation below:

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SCell implements Cell {
    private String line;
    private int type;
    private int order; // סדר התא (במידה ויש תלות בין תאים)

    // Add your code here


    public SCell(String s) {
        // Add your code here

        setData(s);
        this.type = Ex2Utils.TEXT; // ברירת מחדל: טקסט
        this.order = 0; // ברירת מחדל: אין תלות
    }

    @Override
    public int getOrder() {
        // Add your code here

        return order;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
    public void setData(String s) {
        // Add your code here
        this.line = s;

        // בדיקה האם מדובר במספר, טקסט או נוסחה
        if (isNumber(s)) {
            this.type = Ex2Utils.NUMBER;
        } else if (isFormula(s)) {
            this.type = Ex2Utils.FORM;
        } else {
            this.type = Ex2Utils.TEXT;
        }
        /////////////////////
    }

    @Override
    public String getData() {
        return line;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here
        this.order = t;

    }

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

    //areParenthesesValid//
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
    public static boolean isCell(String str) {
        if (str == null || str.isEmpty()) {
            return false; // מחרוזת ריקה או null אינה חוקית
        }

        // תבנית לבדיקה: "=" ואחריו אות גדולה/קטנה ומספר בטווח 0-99
        String regex = "^=[A-Za-z](\\d|[1-9]\\d)$";
        return str.matches(regex);
    }

    //isTaxt//
    public static boolean isText(String str) {
        return !str.matches(String.valueOf(isFormula(str) && isNumber(str) && isCell(str)));
    }

    //computeForm//
    public static Double computeForm(String form) {
        if (form == null || form.isEmpty() || !form.startsWith("=")) {
            String Ex2Utils;
            throw new IllegalArgumentException();
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
}