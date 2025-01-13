//package assignments.ex2;
import java.io.IOException;
// Add your documentation below:

public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1) {
            for(int j=0;j<y;j=j+1) {
                table[i][j] = new SCell(Ex2Utils.EMPTY_CELL);
            }
        }
        eval();
    }
    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }
    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;
        Cell c = get(x,y);
        if(c!=null) {ans = c.toString();}
        Cell cell = get(x, y);
        return cell != null ? cell.toString() : Ex2Utils.EMPTY_CELL;
    }
    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }
    @Override
    public Cell get(String cords) {
        Cell ans = null;
        int[] coords = Ex2Utils.evaluateCell();
        if (coords != null && isIn(coords[0], coords[1])) {
            return get(coords[0], coords[1]);
        }
        return null;
        return ans;
    }
    @Override
    public int width() {
        return table.length;
    }
    @Override
    public int height() {
        return table[0].length;
    }
    @Override
    public void set(int x, int y, String s) {
        Cell c = new SCell(s);
        table[x][y] = c;
        if (isIn(x, y)) {
            table[x][y] = new SCell(c);
        }
    }
    @Override
    public void eval() {
        int[][] dd = depth();
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                table[i][j].setValue(eval(i, j));
            }
        }
    }
    @Override
    public boolean isIn(int xx, int yy) {
        boolean ans = xx>=0 && yy>=0;
        return xx >= 0 && xx < width() && yy >= 0 && yy < height();
    }
    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        int[][] depthMatrix = new int[width()][height()];
        boolean[][] visited = new boolean[width()][height()];
        boolean[][] stack = new boolean[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                depthMatrix[i][j] = calculateDepth(i, j, visited, stack);
            }
        }
        return depthMatrix;
    }
    private int calculateDepth(int i, int j, boolean[][] visited, boolean[][] stack) {
        return i;
    }
    @Override
    public void load(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }
    @Override
    public void save(String fileName) throws IOException {
        // Add your code here

        /////////////////////
    }
    @Override
    public String eval(int x, int y) {
        String ans = null;
        if(get(x,y)!=null) {ans = get(x,y).toString();}
        if (!isIn(x, y)) return Ex2Utils.EMPTY_CELL;
        Cell cell = get(x, y);
        if (cell == null) return Ex2Utils.EMPTY_CELL;
        return Ex2Utils.evaluateCell(cell, this);
    }
}