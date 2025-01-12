/**
 * ArielU. Intro2CS, Ex2: https://docs.google.com/document/d/1-18T-dj00apE4k1qmpXGOaqttxLn-Kwi/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * DO NOT CHANGE THIS INTERFACE!!
 * This interface represents a simple 2D cell index (as in a spreadsheet),
 * The index "c12" represents the cell [2][12].
 */
public interface Index2D {
    /**
     *
     * @return the cell index representation in form of a spreadsheet (e.g., "B3").
     */
    public String toString();

    /**
     * checks of the string representation of this index is valid "XY" as X is a letter "A-Z" (or "a-z"), and Y is an integer [0-99].
     * @return true iff this is a valid 2D index.
     */
    public boolean isValid();

    /**
     *
     * @return the x value (integer) of this index
     */
    public int getX();
    /**
     * @return the y value (integer) of this index
     */
    public int getY();
}































































////package assignments.ex2;
//
/////**
// * ArielU. Intro2CS, Ex2: https://docs.google.com/document/d/1-18T-dj00apE4k1qmpXGOaqttxLn-Kwi/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
// * DO NOT CHANGE THIS INTERFACE!!
// * This interface represents a simple 2D cell index (as in a spreadsheet),
// * The index "c12" represents the cell [2][12].
// */
//public interface Index2D {
//    /**
//     * @return the cell index representation in form of a spreadsheet (e.g., "B3").
//     * לייצג את האינדקס בצורה של אות ומספר לדוג [B3]
//     */
   // public class Cell {
        //    private int rowIndex;  // אינדקס השורה (מבוסס 0)
        //    private int colIndex;  // אינדקס העמודה (מבוסס 0)
        //    private boolean isUppercase; // האם להשתמש באותיות גדולות או קטנות
        //
        //    // בנאי
        //    public tamar.Cell(int rowIndex, int colIndex, boolean isUppercase) {
        //        this.rowIndex = rowIndex;
        //        this.colIndex = colIndex;
        //        this.isUppercase = isUppercase;
        //    }
        //
        //    /**
        //     * ממיר אינדקס של שורה ועמודה לייצוג של גיליון אלקטרוני
        //     *
        //     * @return ייצוג אינדקס בפורמט של [ColumnRow], לדוגמה [B3]
        //     */
        //    @Override
        //    public String toString() {
        //        // ממיר את אינדקס העמודה לאותיות (לדוגמה: 0 -> A או a, 1 -> B או b, ...)
        //        StringBuilder column = new StringBuilder();
        //        int col = colIndex + 1; // ממיר אינדקס מבוסס 0 לאינדקס מבוסס 1 לייצוג בגיליון
        //        while (col > 0) {
        //            col--; // התאמה לאינדקס אלפביתי מבוסס 1
        //            char letter = (char) ((isUppercase ? 'A' : 'a') + (col % 26)); // קובע אם האות תהיה גדולה או קטנה
        //            column.insert(0, letter);
        //            col /= 26; // ממשיך לחישוב הבא לבסיס 26
        //        }
        //
        //        // ממיר את אינדקס השורה למבוסס 1 (לדוגמה: 0 -> 1)
        //        int row = rowIndex + 1;
        //
        //        // מחזיר את הייצוג בפורמט [ColumnRow], לדוגמה [B3] או [b3]
        //        return "[" + column + row + "]";
        //    }
//    }
//
//
//    public String toString();
//
//    /**
//     * checks of the string representation of this index is valid "XY" as X is a letter "A-Z" (or "a-z"), and Y is an integer [0-99].
//     *
//     * @return true iff this is a valid 2D index.
//     * בדיקות של ייצוג המחרוזת של אינדקס זה חוקיות "XY" שכן X היא אות "A-Z" (או "a-z"), ו-Y הוא מספר שלם [0-99].
//     * * @return true אם זהו אינדקס דו-ממדי חוקי.
//     */
//
//    public class IndexValidator {
        //
        //    /**
        //     * בודק אם המחרוזת מייצגת אינדקס דו-ממדי חוקי מהצורה "XY",
        //     * כאשר X הוא אות (גדולה או קטנה) ו-Y הוא מספר בטווח [0-99].
        //     *
        //     * @param index המחרוזת לבדיקה
        //     * @return true אם המחרוזת חוקית, אחרת false
        //     */
        //    public static boolean isValid2DIndex(String index) {
        //        if (index == null || index.isEmpty()) {
        //            return false; // בדיקה למחרוזת ריקה או null
        //        }
        //
        //        // בדיקה אם המחרוזת מתאימה לתבנית הנדרשת
        //        return index.matches("^[A-Za-z](\\d|[1-9]\\d)$");
        //    }
//    }
//
//    public boolean isValid();
//
//    /**
//     * @return the x value (integer) of this index
//     * * @return ערך x (מספר שלם) של אינדקס זה
//     */
//    public class Index {
//        private String index; // מחרוזת המייצגת את האינדקס (לדוגמה: "B3")
//
//        // בנאי
//        public Index(String index) {
//            this.index = index;
//        }
//
//        /**
//         * מחזיר את ערך ה-x (ערך עמודה) של אינדקס זה.
//         * ערך ה-x מיוצג על ידי האות הראשונה במחרוזת ומומר למספר שלם
//         * לדוגמה: "A" -> 0, "B" -> 1, "C" -> 2 וכן הלאה.
//         *
//         * @return ערך ה-x (מספר שלם)
//         * @throws IllegalArgumentException אם האינדקס אינו חוקי
//         */
//        public int getX() {
//        }
//
//        public int getY() {
//    }
//
//   public int getX();
//
//        /**
//         * @return the y value (integer) of this index
//         * * @return את ערך y (מספר שלם) של אינדקס זה
//         */
//    public class Index {
//        private String index; // מחרוזת המייצגת את האינדקס (לדוגמה: "B3")
//
//        // בנאי
//        public Index(String index) {
//            this.index = index;
//        }
//
//        /**
//         * מחזיר את ערך ה-y (ערך השורה) של אינדקס זה.
//         * ערך ה-y מיוצג על ידי החלק המספרי של המחרוזת.
//         *
//         * @return ערך ה-y (מספר שלם)
//         * @throws IllegalArgumentException אם האינדקס אינו חוקי
//         */
//        public int getY() {
//                int ans = 1;
////            if (index == null || index.isEmpty() || !index.matches("^[A-Za-z]\\d+$")) {
////                throw new IllegalArgumentException("Invalid index format: " + index);
//            return ans;
//            }
//
//            // מוציא את החלק המספרי מהמחרוזת וממיר למספר שלם
//            String numberPart = index.substring(1); // החלק שמתחיל אחרי האות הראשונה
//            return Integer.parseInt(numberPart); // ממיר את המחרוזת למספר שלם
//      }
//   }

//}
