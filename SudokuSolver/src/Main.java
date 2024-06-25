import java.util.Random;

class Field {
    public static final int sizeX = 9;
    public static final int sizeY = 9;
    private Cell[][] cells;// = new Cell[sizeX][sizeY];
    {
        cells = new Cell[sizeX][sizeY];
    }

    public void addCell(Cell cell){
        cells[cell.getCoord().getY()][cell.getCoord().getX()] = cell;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static final char[] digits = {'1','2','3','4','5','6','7','8','9'};

//    public static Cell getRandomCell() {
//
//        Random rnd = new Random();
//        while (true) {
//            int x = rnd.nextInt(sizeX) + 1;
//            int y = rnd.nextInt(sizeY) + 1;
//            if (Cells[x][y].getState() == '.') {
//                return Cells[x][y];
//            }
//        }
//    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

//    public static CellArray findVal(char val){
//        CellArray out = new CellArray();
//        for (int i = 0; i < sizeY; i++) {
//            for (int j = 0; j < sizeX; j++) {
//                if(cells[j][i].getState() == val)
//                    out.add(cells[j][i]);
//            }
//        }
//        return out;
//    }

//    public static CoordArray findSquaresWithVal(char val){
//        CellArray arr = findVal(val);
//        CoordArray out = new CoordArray();
//        if(arr.getSize() > 0) {
//            int a = -1;
//            int b = -1;
//            for (int i = 0; i < arr.getSize(); i++) {
//                Cell cell = arr.getCellByIndex(i);
//                a = cell.getCoord().getX()/3;
//                b = cell.getCoord().getY()/3;
//                out.add(new Coord(a,b));
//            }
//        }
//        return out;
//    }
}

public class Main {

    static void prnFldCells(Cell[][] a) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.printf(" %s ", a[i][j].getState());
                if (j < 8) {
                    if (j % 3 == 2)
                        System.out.print("H");
                    else
                        System.out.print("|");
                }
            }
            if (i < 8) {
                if (i % 3 == 2)
                    System.out.println("\n===========H===========H===========");
                else
                    System.out.println("\n---+---+---H---+---+---H---+---+---");
            }
        }
    }

    static void prnArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] field = new char[][]{
                {'1', '8', '2', '.', '.', '6', '.', '.', '.'},
                {'9', '7', '4', '1', '.', '.', '.', '5', '.'},
                {'.', '3', '.', '.', '7', '.', '1', '9', '.'},
                {'.', '9', '7', '2', '.', '4', '5', '.', '.'},
                {'.', '.', '.', '3', '.', '5', '.', '.', '.'},
                {'.', '.', '1', '6', '.', '7', '8', '2', '.'},
                {'.', '1', '9', '.', '6', '.', '.', '4', '.'},
                {'.', '4', '.', '.', '.', '9', '3', '6', '1'},
                {'.', '.', '.', '5', '.', '.', '9', '8', '7'},
        };
        /*char[][] field = new char[][]
                {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };*/
//        System.out.println(field.length);
        int toReduce = 0;
        Field cells = new Field();
        for (int i = 0; i < Field.sizeY; i++) {
            for (int j = 0; j < Field.sizeX; j++) {
                Cell curCell = new Cell(j,i,field[i][j]);
                if (field[i][j] != '.') {
                    curCell.setComplete(true);
                } else
                    toReduce++;
                cells.addCell(curCell);
            }
        }

        System.out.println("Не заполнено: " + toReduce);
        boolean notFirst = false;
        int count = 0;
        int startX,startY,endX,endY,inc;
        do {
            int oldCount = toReduce;
            //if(count%2 == 0){
                startX = startY = 0;
                endX = endY = 9;
                inc = 1;
//            }else{
//                startX = startY = 9;
//                endX = endY = 0;
//                inc = -1;
//            }
            for (int i = startY; i < endY; i++) {
                for (int j = startX; j < endX; j++) {
                    Cell curCell = cells.getCell(i,j);
                    if (!curCell.isComplete()) {
                        if (curCell.reduceHip(notFirst,cells)) {
                            System.out.println("Уст: " + curCell.getState() + "! reduced: " + ++Cell.countReducedHips);
                            toReduce--;
                        }
                        System.out.printf("x = %d, y = %d: ", curCell.getCoord().getX(), curCell.getCoord().getY());
                        System.out.println(curCell.getHip());
                    }
                }
            }
            if (oldCount == toReduce) {
                System.out.println("НЕ произошло уменьшение вариантов!");
                break; //какой-то затык...
            }
            notFirst = true;
            count++;
        } while (toReduce > 0);
        System.out.println("toReduce: " + toReduce);
        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println("x="+j+", y="+i+": "+cells.getCells()[i][j].printHip());
            }

        }*/

        prnFldCells(cells.getCells());


//        char[] fieldSiquenced = new char[81];
//        int k = 0;
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                fieldSiquenced[k++] = field[j][i];
//            }
//        }
//        Random rnd = new Random();
//        int[] field = new int[81];
//        for (int i = 0; i < field.length; i++) {
//            field[i] = rnd.nextInt(99) + 1;
//        }
        //Square square = new Square(1, 2);
        //square.prnSquare(fieldSiquenced);

//        System.out.println("\n17 " + getXY(17) + " = " + field[17]);
//        System.out.println("56 " + getXY(56) + " = " + field[56]);
//        System.out.println("70" + getXY(70) + " = " + field[70]);
    }


}