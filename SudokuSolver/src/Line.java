public class Line {
    private int num; //позиция линии по горизонтали или по вертикалих [0-8]
    private boolean complete;
    private boolean isVert;
    private Cell[] line;

    public Line(int num, boolean isVert) {
        this.num = num;
        this.isVert = isVert;

    }

    public static Coord checkNumber(int pos, boolean isVert, char val,Field field) {
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

    public static CellArray getFreeCells(int pos,boolean isVert,Cell[][] field){
        int count=0;
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
