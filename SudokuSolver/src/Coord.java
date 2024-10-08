public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x=" + x + "; y=" + y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public boolean isSet() {
        if (x != -1 || y != -1)
            return true;
        return false;
    }public static boolean isStatused(Coord cell) {
        if (cell.getX() != -1 || cell.getY() != -1)
            return true;
        return false;
    }

    public static boolean compareCoords(Coord c1, Coord c2){
        return c1.getX() == c2.getX() && c1.getY() == c2.getY();
    }

}
