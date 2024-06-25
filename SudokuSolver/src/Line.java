public class Line {
    private int num; //позиция линии по горизонтали или по вертикалих [0-8]
    private boolean complete;
    private boolean isVert;
    private Cell[] line;

    public Line(int num, boolean isVert) {
        this.num = num;
        this.isVert = isVert;

    }

    public static Coord checkNumber(int pos, boolean isVert, char val, Field field) {
        int x = -1;
        int y = -1;
        int limit = isVert ? Field.sizeX : Field.sizeY;
        for (int i = 0; i < limit; i++) {
            int posX = isVert ? pos : i;
            int posY = isVert ? i : pos;
            if (field.getCells()[posY][posX].getState() == val) {
                x = posX;
                y = posY;
                break;
            }
        }
        return new Coord(x, y);
    }

    public static CoordArray checkHyps(int pos, boolean isVert, String hypStr, Field field) {
        int x = -1;
        int y = -1;
        CoordArray coords = new CoordArray();
        int limit = isVert ? Field.sizeX : Field.sizeY;
        for (int i = 0; i < limit; i++) {
            int posX = isVert ? pos : i;
            int posY = isVert ? i : pos;
            if (!field.getCells()[posY][posX].isComplete()) {
                String curStr = field.getCells()[posY][posX].getHypStr();
                if (curStr.equals(hypStr)) {
                    coords.add(new Coord(posX, posY));
                    //break;
                }
            }
        }
        return coords;
    }

    public static int removeDoubleHyps(int pos, boolean isVert, CoordArray coords, Field field) {
        int limit = isVert ? Field.sizeX : Field.sizeY;
        int removes = 0;
        //if (coords.length < 2) return 0;
        Hypothesis fixHips = field.getCells()[coords.getArr()[0].getX()][coords.getArr()[0].getY()].getHyps();
        for (int i = 0; i < limit; i++) {
            int posX = isVert ? pos : i;
            int posY = isVert ? i : pos;
            if(field.getCells()[posY][posX].isComplete()) continue;
            Coord curCoord = new Coord(posX, posY);
            for (int j = 0; j < coords.getSize(); j++) {
                if (!Coord.compareCoords(coords.getArr()[j], curCoord)) {
                    Hypothesis curCellHyps = field.getCells()[posY][posX].getHyps();
                    for (int k = 0; k < fixHips.getSize(); k++) {
                        if (curCellHyps.isValInHyps(fixHips.getHyps()[k])) {
                            curCellHyps.delHyp(fixHips.getHyps()[k]);
                            removes++;
                        }
                    }
                }
            }
        }
        return removes;
    }

    public static CellArray getFreeCells(int pos, boolean isVert, Cell[][] field) {
        int count = 0;
        CellArray out = new CellArray();
        int limit = isVert ? Field.sizeX : Field.sizeY;
        for (int i = 0; i < limit; i++) {
            int posX = isVert ? pos : i;
            int posY = isVert ? i : pos;
            if (!field[posY][posX].isComplete()) {
                out.add(field[posY][posX]);
            }
        }
        return out;
    }

//    public static boolean checkNeighborLines(int pos, boolean isVert, char val) {
//        int posInSquare = pos % 3;
//        int match = 0;
//        for (int i = 0; i < 3; i++) {
//            int curPos = i + pos - posInSquare;
//            if (curPos != pos) {
//                if (Coord.isStatused(Line.checkNumber(curPos, isVert, val)))
//                    match++;
//            }
//        }
//        if (match == 2) return true;
//        return false;
//    }


}
