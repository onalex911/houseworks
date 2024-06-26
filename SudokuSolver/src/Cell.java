public class Cell {
    int x;
    int y;
    private Coord coord;
    private char state;
    private boolean complete;
    //private char[] hip;
    private Hypothesis hyps;

    private static int countReduce = 0;
    public static int countReducedHips = 0;

    {
        //hip = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};// Field.digits;
        complete = false;
        hyps = new Hypothesis();
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

    /*public char[] getHip() {
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
    }*/

    private boolean isValInHyps(char val) {
        for (int i = 0; i < hyps.getSize(); i++) {
            if (hyps.getHyps()[i] == val)
                return true;
        }
        return false;
    }

    private boolean isNeighborsHyps(char val, Field field) {
        Cell nc;
        for (int i = 0; i < 3; i++) {
            if (i != x % 3) {
                nc = field.getCell(x - x % 3 + i, y);
                if (!nc.isComplete() && nc.isValInHyps(val)) return true;
            }
            if (i != y % 3) {
                nc = field.getCell(x, y - y % 3 + i);
                if (!nc.isComplete() && nc.isValInHyps(val)) return true;
            }
        }
        return false;
    }

    private char getUniqHyp(Field field) {
        for (int i = 0; i < hyps.getSize(); i++)
            if (!isNeighborsHyps(hyps.getHyps()[i], field))
                return hyps.getHyps()[i];
        return '.';
    }

    public boolean reduceHyps(boolean notFirst, Field field) {
        if (++Cell.countReduce >= 10) return false; //заготовка для рекурсии

        if (!complete) {
            for (int i = 0; i < hyps.getSize(); ) {
                char curVal = hyps.getHyps()[i];
                int a = x / 3;
                int b = y / 3;
                int remaindHyps = Field.sizeX;
                if (Coord.isStatused(Line.checkNumber(y, false, curVal, field))) {
                    remaindHyps = hyps.delHyp(curVal);
                } else if (Coord.isStatused(Line.checkNumber(x, true, curVal, field))) {
                    remaindHyps = hyps.delHyp(curVal);
                } else if (Coord.isStatused(Square.checkNumber(a, b, curVal, field))) {
                    remaindHyps = hyps.delHyp(curVal);
                }

                char uniq = getUniqHyp(field);
                if (uniq != '.') {// && curSquare.isContains(uniq)){
                    state = uniq;
                    complete = true;
                    System.out.println("Уст. " + uniq + " на основании анализа соседей x=" + x + ", y=" + y);
                    countReducedHips++;
                    return true;
                }
                if (notFirst) {
//                    CoordArray coordsHor = Line.checkHyps(y, false, getHypStr(), field);
//                    int removedInHor = 0;
//                    if (coordsHor.getSize() > 1 && coordsHor.getSize() == hyps.getSize()) {
//                        removedInHor = Line.removeDoubleHyps(y, false, coordsHor, field);
//                    }
//                    int removedInVert = 0;
//                    CoordArray coordsVert = Line.checkHyps(x, true, getHypStr(), field);
//                    if (coordsVert.getSize() > 1 && coordsVert.getSize() == hyps.getSize()) {
//                        removedInVert = Line.removeDoubleHyps(x, true, coordsVert, field);
//                    }
                    int removedInSquare = 0;
                    String curHypStr = getHypStr();
                    CoordArray coords = Square.checkHyps(a, b, curHypStr, field);
                    if (coords.getSize() > 1 && coords.getSize() == hyps.getSize()) {
                        removedInSquare = Square.removeDoubleHyps(a, b, coords, field);
                    }
//                    if (removedInHor > 0)
//                        System.out.printf("удалено неочевидных гипотез в горизонтали %d - %d", y, removedInHor);
//                    if (removedInVert > 0)
//                        System.out.printf("удалено неочевидных гипотез в вертикали %d - %d", x, removedInVert);
                    if (removedInSquare > 0) {
                        System.out.printf("удалено неочевидных гипотез в квадрате (%d,%d) - %d", a, b, removedInSquare);
                        return true;
                    }

                }
                if (remaindHyps == 1) {
                    state = hyps.getLastHyp();
                    complete = true;
                    return true;
                }

                if (remaindHyps == Field.sizeX) {
                    i++;
                }
            }
        }
        countReduce--;
        return complete;
    }

//    public static int countIncompleteLines(Cell[][] field) {
//        int count = 0;
//        for (int i = 0; i < Field.sizeY; i++) {
//            if (Line.getFreeCells(i, false, field).getSize() > 0)
//                count++;
//        }
//        return count;
//    }

    public String getHypStr() {
        return hyps.getHypStr();
    }

    public Hypothesis getHyps() {
        return hyps;
    }
}
