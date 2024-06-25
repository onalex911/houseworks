import java.util.Random;

public class Cell {
    int x;
    int y;
    private Coord coord;
    private char state;
    private boolean complete;
    private char[] hip;

    private static int countReduce = 0;
    public static int countReducedHips = 0;

    {
        hip = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};// Field.digits;
        complete = false;
        state = '.';
        //countReduce = 0;
    }

    public Cell(int x, int y, char state) {
        this.x = x;
        this.y = y;
        this.coord = new Coord(x, y);
        this.state = state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Coord getCoord() {
        return coord;
    }

    public char getState() {
        return state;
    }

    public boolean isComplete() {
        return complete;
    }

    public char[] getHip() {
        return hip;
    }

    public String printHip() {
        String out = "";
        for (int i = 0; i < 9; i++) {
            out += hip[i] + " ";
        }
        return out;
    }

    private void checkLastHip() {
        if (!complete) {
            int hips = 0;
            char out = ' ';
            for (int i = 0; i < hip.length; i++) {
                if (hip[i] != '.') {
                    hips++;
                    out = hip[i];
                }
            }
            if (hips == 1) {
                state = out;
                complete = true;
            }
        }
    }

    private void delHip(char val) {
        for (int i = 0; i < hip.length; i++) {
            if (hip[i] == val) {
                hip[i] = '.';
                break;
            }
        }
        checkLastHip();
    }

    private boolean isValInHip(char val) {
        for (int i = 0; i < hip.length; i++) {
            if (hip[i] == val)
                return true;
        }
        return false;
    }

    private boolean isNeighborsHip(char val, Field field) {
        int k = 0;
        Cell nc;
        for (int i = 0; i < 3; i++) {
            if (i != x % 3) {
                nc = field.getCell(x - x % 3 + i, y);
                if(!nc.isComplete() && nc.isValInHip(val)) k++;
            }
            if (i != y % 3) {
                nc = field.getCell(x, y - y % 3 + i);
                if(!nc.isComplete() && nc.isValInHip(val)) k++;
            }
        }
        if (k > 0) return true;
        return false;
    }

    private char getUniqHip(Field field) {
        for (int i = 0; i < hip.length; i++)
            if (hip[i] != '.' && !isNeighborsHip(hip[i], field))
                return hip[i];
        return '.';
    }

    public boolean reduceHip(boolean notFirst, Field field) {
        if (++Cell.countReduce >= 10) return false; //заготовка для рекурсии

        if (!complete) {
            for (int i = 0; i < hip.length; i++) {
                char curVal = hip[i];
                int a = x / 3;
                int b = y / 3;
                if (Coord.isStatused(Line.checkNumber(y, false, curVal, field))) {
                    delHip(curVal);
                } else if (Coord.isStatused(Line.checkNumber(x, true, curVal, field))) {
                    delHip(curVal);
                } else if (Coord.isStatused(Square.checkNumber(a, b, curVal, field))) {
                    delHip(curVal);
                }
                Square curSquare = new Square(a, b, field);
                //Cell[] freeCells = curSquare.getFreeCells().getArr();
                if (curSquare.getFreeCells().getSize() == 1) {
                    curSquare.getArrayCell(0).checkLastHip();
                    if (complete)
                        countReducedHips++;
                    System.out.println("Уст: " + state + "!!!");
                } else {
//                    if (notFirst && curVal != '.') {
//                        System.out.println("проверка соседей x="+x+", y="+y+" на "+curVal);
//                        char uniq = getUniqHip(field);
//                        if (uniq != '.' && curSquare.isContains(uniq)){
//                            state = uniq;
//                            complete = true;
//                        }
//                    }
                }
            }
        }
        countReduce--;
        return complete;
    }

    public static int countIncompleteLines(Cell[][] field) {
        int count = 0;
        for (int i = 0; i < Field.sizeY; i++) {
            if (Line.getFreeCells(i, false, field).getSize() > 0)
                count++;
        }
        return count;
    }


}
