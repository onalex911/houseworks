class Field {
    public static final int sizeX = 9;
    public static final int sizeY = 9;
    private Cell[][] cells;// = new Cell[sizeX][sizeY];

    {
        cells = new Cell[sizeX][sizeY];
    }

    public void addCell(Cell cell) {
        cells[cell.getCoord().getY()][cell.getCoord().getX()] = cell;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static final char[] digits = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

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

    public static int checkLines(Field field) {
        int out = 0;
        for (int i = 0; i < 9; i++) {
            System.out.print("Проверка строки " + (i + 1) + ": ");
            switch (Line.isCorrect(i, false, field)) {
                case -1:
                    System.out.println("не заполнена");
                    //System.out.println(Line.print(i, field));
                    //out = false;
                    break;
                case 0:
                    System.out.println("ОШИБКА!");
                    //System.out.println(Line.print(i, field));
                    out = -1;
                    break;
                case 1:
                    System.out.println("Ok!");
                    out = 1;
            }
            System.out.println(Line.print(i, field));
            if(out < 0) return out;
        }
        return out;
    }

    public static CoordArray checkSquares(Field field) {
        CoordArray out = new CoordArray();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //System.out.print("Проверка квадрата " + j + ", " + i + ": ");
//                switch (Square.isCorrect(j, i,field)) {
//                    case -1:
//                        System.out.println("не заполнен");
                        //System.out.println(Line.print(i, field));
                        if(Square.isCorrect(j, i,field) <0)
                            out.add(new Coord(j,i));
//                        break;
//                    case 0:
//                        System.out.println("ОШИБКА!");
//
//                        break;
//                    case 1:
//                        System.out.println("Ok!");
//                }
//                System.out.println(Square.print(j,i, field));
            }
        }
        return out;
    }

    static void deleteHypsLnSq(Cell cell,Field field,boolean sqToo){
        int x = cell.getCoord().getX();
        int y = cell.getCoord().getY();
        char state = cell.getState();
        for (int i = 0; i < 9; i++) {
            int posXHor = i; int posYHor = y;
            int posXVert = x; int posYVert = i;
            int posA = x-x%3+i%3; int posB=y-y%3+i/3;

            Cell curCell1 = field.getCell(posXHor,posYHor);
            if(!curCell1.isComplete() && posXHor != x) curCell1.getHyps().delHyp(state);
            Cell curCell2 = field.getCell(posXVert,posYVert);
            if(!curCell2.isComplete() && posYVert != y) curCell2.getHyps().delHyp(state);
            if(sqToo) {
                Cell curCell3 = field.getCell(posA, posB);
                if (!curCell3.isComplete() && posA != x && posB != y) curCell3.getHyps().delHyp(state);
            }
        }
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
   //     System.out.println((char)48);
/* EXPERT
        char[][] field = new char[][]{
                {'5', '6', '2', '7', '.', '.', '.', '8', '.'},
                {'3', '1', '.', '5', '6', '8', '.', '2', '.'},
                {'.', '.', '7', '.', '.', '.', '5', '6', '3'},
                {'.', '.', '3', '4', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '5', '2', '.', '.'},
                {'7', '4', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '4', '.', '.', '7', '.'},
                {'.', '3', '.', '.', '.', '7', '4', '9', '2'},
        };
*/
//Hevy
       /* char[][] field = new char[][]{
                {'8', '1', '.', '.', '.', '.', '.', '.', '9'},
                {'.', '7', '.', '.', '.', '.', '1', '.', '.'},
                {'5', '.', '.', '9', '.', '1', '.', '.', '2'},
                {'6', '8', '.', '3', '1', '.', '.', '9', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '9', '.', '.', '5', '2', '.', '1', '3'},
                {'1', '.', '.', '7', '.', '3', '.', '.', '6'},
                {'.', '.', '6', '.', '.', '.', '.', '8', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '4', '7'},
        };*/
        /*char[][] field = new char[][]{
//                0    1    2    3    4    5    6    7    8
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//0
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//1
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//2
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//3
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//4
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//5
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//6
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//7
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},//8
        };*/
        //Middle
       /* char[][] field = new char[][]{
////                0    1    2  | 3    4    5  | 6    7    8
                {'4', '8', '3', '.', '6', '9', '.', '.', '1'},//0
                {'.', '6', '9', '.', '.', '7', '.', '2', '4'},//1
                {'.', '.', '2', '4', '.', '.', '.', '.', '8'},//2_
                {'.', '2', '6', '.', '.', '.', '8', '.', '.'},//3
                {'3', '4', '.', '.', '.', '.', '.', '1', '9'},//4
                {'.', '.', '7', '.', '.', '.', '6', '4', '.'},//5_
                {'2', '.', '.', '.', '.', '4', '.', '.', '.'},//6
                {'8', '.', '.', '1', '.', '.', '2', '9', '.'},//7
                {'6', '.', '.', '8', '.', '.', '4', '7', '3'},//8
        };*/
        /* OK! char[][] field = new char[][]{
                {'1', '8', '2', '.', '.', '6', '.', '.', '.'},
                {'9', '7', '4', '1', '.', '.', '.', '5', '.'},
                {'.', '3', '.', '.', '7', '.', '1', '9', '.'},
                {'.', '9', '7', '2', '.', '4', '5', '.', '.'},
                {'.', '.', '.', '3', '.', '5', '.', '.', '.'},
                {'.', '.', '1', '6', '.', '7', '8', '2', '.'},
                {'.', '1', '9', '.', '6', '.', '.', '4', '.'},
                {'.', '4', '.', '.', '.', '9', '3', '6', '1'},
                {'.', '.', '.', '5', '.', '.', '9', '8', '7'},
        };*/
          char[][] field = new char[][]
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
                };
//        System.out.println(field.length);
        int toReduce = 0;
        Field cells = new Field();
        for (int i = 0; i < Field.sizeY; i++) {
            for (int j = 0; j < Field.sizeX; j++) {
                Cell curCell = new Cell(j, i, field[i][j]);
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
        int startX, startY, endX, endY, inc;
        boolean enforce = false;
        do {
            System.out.println("Итерация :" + (count) + " ... ");
            int oldCount = toReduce;
            startX = startY = 0;
            endX = endY = 9;
            int checkLineState = 0;
            inc = 1;
            for (int i = startY; i < endY; i++) {
                for (int j = startX; j < endX; j++) {
                    Cell curCell = cells.getCell(j, i);
                    if (!curCell.isComplete()) {
//                        System.out.printf("x = %d, y = %d before: ", curCell.getCoord().getX(), curCell.getCoord().getY());
//                        System.out.println(curCell.getHypStr());
                        if (curCell.reduceHyps(notFirst,enforce, cells) && curCell.getHyps().getLastHyp() != '.') {
                            curCell.setState(curCell.getHyps().getLastHyp());
                            Field.deleteHypsLnSq(curCell,cells,true);
                            System.out.println("Уст: " + curCell.getState() + "! reduced: " + ++Cell.countReducedHips);
                            checkLineState = Field.checkLines(cells);
                            if(checkLineState < 0)
                                break;
                            CoordArray squaresCoords = Field.checkSquares(cells);
                            if(squaresCoords.getSize() > 0) {
                                for (int k = 0; k < squaresCoords.getSize(); k++) {
                                    Square.forceFill(squaresCoords.getArr()[k].getX(),squaresCoords.getArr()[k].getY(), cells);
                                }
                            }
                            toReduce--;
                        }
                        System.out.printf("x = %d, y = %d after: ", curCell.getCoord().getX(), curCell.getCoord().getY());
                        System.out.println(curCell.getHypStr());
                    }
                }
                if(checkLineState < 0) break;
            }
            if (count > 0 && oldCount == toReduce && checkLineState>=0) {
                if(!enforce){
                    enforce = true;
                }else {
                    System.out.println("НЕ произошло уменьшение вариантов после " + (count + 1) + "-го цикла!");
                    break; //какой-то затык...
                }
            }else if(checkLineState < 0)
                break;
            notFirst = true;
            count++;
        } while (toReduce > 0);
        System.out.println("toReduce: " + toReduce + "/" + Cell.countReducedHips);
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