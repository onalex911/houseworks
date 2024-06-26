import java.util.ArrayList;
import java.util.List;

public class Square {
    private Coord sqCoord;
    private boolean complete;
    private CellArray arr;

    {
        complete = false;
        arr = new CellArray();
    }

    public Square(int a, int b, Field field) {
        this.sqCoord = new Coord(a, b);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int posX = j + a * 3;
                int posY = i + b * 3;
                arr.add(field.getCells()[posY][posX]);
            }
        }
    }

    public boolean isContains(char val) {
        for (int i = 0; i < arr.getSize(); i++) {
            if (arr.getCellByIndex(i).getState() == val)
                return true;
        }
        return false;
    }

    public CellArray getArr() {
        return arr;
    }

    public Cell getArrayCell(int ind) {
        return arr.getCellByIndex(ind);
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public static boolean isComplete(int a, int b, Field field) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int posX = j + a * 3;
                int posY = i + b * 3;
                if (!field.getCells()[posY][posX].isComplete()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCorrect(int a, int b, Field field) {
        if (isComplete(a, b, field)) {
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int posX = j + a * 3;
                    int posY = i + b * 3;
                    Cell curCell = field.getCells()[posY][posX];
                    if (values.contains((int) curCell.getState()))
                        return false;
                    values.add((int) curCell.getState());
                }
            }
            return true;
        }
        return false;
    }

    public static Coord checkNumber(int a, int b, char val, Field field) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int posX = j + a * 3;
                int posY = i + b * 3;
                if (field.getCells()[posY][posX].getState() == val) {
                    x = posX;
                    y = posY;
                    break;
                }
            }
        }
        return new Coord(x, y);
    }

    public static CoordArray checkHyps(int a, int b, String hypStr, Field field) {
        int x = -1;
        int y = -1;
        CoordArray coords = new CoordArray();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int posX = j + a * 3;
                int posY = i + b * 3;
                if (!field.getCells()[posY][posX].isComplete() && field.getCells()[posY][posX].getHypStr().equals(hypStr)) {
                    coords.add(new Coord(posX, posY));
                    //break;
                }
            }
        }
        return coords;
    }

    public static int removeDoubleHyps(int a, int b, CoordArray coords, Field field) {
        int removes = 0;
        int cX = coords.getArr()[0].getX();
        int cY = coords.getArr()[0].getY();
        Hypothesis fixHips = field.getCells()[cY][cX].getHyps();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int posX = j + a * 3;
                int posY = i + b * 3;
                if (field.getCells()[posY][posX].isComplete()) continue;
                Coord curCoord = new Coord(posX, posY);

                for (int m = 0; m < coords.getSize(); m++) {
                    if (Coord.compareCoords(curCoord, coords.getArr()[m])) continue;

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


    public CellArray getFreeCells() {
        CellArray out = new CellArray();

        int k = 0;
        for (int i = 0; i < arr.getSize(); i++) {
            if (!arr.getArr()[i].isComplete())
                out.add(arr.getArr()[i]);
        }
        return out;
    }

    public static Coord getSquareCoordByCell(Cell cell) {
        int a = cell.getCoord().getX() / 3;
        int b = cell.getCoord().getY() / 3;
        return new Coord(a, b);
    }

//    public void prnSquare(char[] arr){
//        for (int i = 0; i < 9; i+=3) {
//            System.out.printf("%s | %s | %s\n",arr[idsCoord[i]],arr[idsCoord[i+1]],arr[idsCoord[i+2]]);
//            if(i<8)
//                System.out.println("------------");
//        }
//    }
}
