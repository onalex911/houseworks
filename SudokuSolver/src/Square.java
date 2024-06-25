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
                int posX = j+a*3;
                int posY = i+b*3;
                arr.add(field.getCells()[posY][posX]);
            }
        }
    }

    public boolean isContains(char val){
        for (int i = 0; i < arr.getSize(); i++) {
            if(arr.getCellByIndex(i).getState() == val)
                return true;
        }
        return false;
    }

    public CellArray getArr() {
        return arr;
    }
    public Cell getArrayCell(int ind){
        return arr.getCellByIndex(ind);
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

//    private Cell[] add(Cell[] array,Cell cell){
//        int newLength = array.length+1;
//        Cell[] out = new Cell[newLength];
//        for (int i = 0; i < array.length; i++) {
//            out[i] = array[i];
//        }
//        out[array.length] = cell;
//        return out;
//    }

    public static Coord checkNumber(int a, int b, char val,Field field) {
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

    public CellArray getFreeCells() {
        CellArray out = new CellArray();

        int k=0;
        for (int i = 0; i < arr.getSize(); i++) {
            if(!arr.getArr()[i].isComplete())
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
