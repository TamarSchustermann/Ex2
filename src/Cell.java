public class Cell {

    /**
     * this function checks if a given string is a number
     * @param str
     * @return true when str is number
     */

    //isNumber//
    public static boolean isNumber(String str){
    return true;
    }

    //isFormula//

    /**
     *
     * @param str
     * @return
     */
    public static boolean isFormula(String str) {
        if (!str.matches("[A-Z0-9+\\-*/().\\s]*")) {
            return false;

        }
    }


    //isCell//

    /**
     *
     * @param str
     * @return
     */
    public static boolean isCell(String str) {
        return str.matches("[A-Z]\\d+");
    }

    //isTaxt

    /**
     *
     * @param str
     * @return
     */
    public static boolean isText(String str) {
        return !str.matches(String.valueOf(isFormula(str) && isNumber(str)));
    }

}
