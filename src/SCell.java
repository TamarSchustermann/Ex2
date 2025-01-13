
// Add your documentation below:

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SCell implements Cell {
    private String line;
    private int type;
    private int order;
    public SCell(String s) {
        setData(s);
        this.type = Ex2Utils.TEXT;
        this.order = 0;
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
        this.line = s;
        if (isNumber(s)) {
            this.type = Ex2Utils.NUMBER;
        } else if (isFormula(s)) {
            this.type = Ex2Utils.FORM;
        } else {
            this.type = Ex2Utils.TEXT;
        }
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
            return false;
        }
        String formula = str.substring(1);
        String regex = "^([A-Za-z]\\d{1,2}|\\d+(\\.\\d+)?|\\((.+)\\))([+\\-*/]([A-Za-z]\\d{1,2}|\\d+(\\.\\d+)?|\\((.+)\\)))*$";
        if (!formula.matches(regex)) {
            return false;
        }
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
                    return false;
                }
            }
        }
        return balance == 0;
    }

    //isCell//
    public static boolean isCell(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
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
        String expression = form.substring(1).replaceAll("\\s+", "");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result = engine.eval(expression);
            return result instanceof Number ? ((Number) result).doubleValue() : null;
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Error evaluating formula: " + form, e);
        }
    }
}