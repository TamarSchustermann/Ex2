import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
    //isTaxt
    public static boolean isText(String str) {
        return !str.matches(String.valueOf(isFormula(str) && isNumber(str) && isCell(str)));
    }
//computeForm
public static Double computeForm(String form) {
    if (form == null || form.isEmpty() || !form.startsWith("=")) {
        throw new IllegalArgumentException(Ex2Utils.EMPTY_CELL);
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
    public void main() {
    }