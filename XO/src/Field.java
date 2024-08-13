public class Field {
    private int dimX;
    private int dimY;

    private int mainArray[];
    private String[] positionNumbers;

    {
        dimX = 3;
        dimY = 3;
        mainArray = new int[dimX * dimY];
        positionNumbers = new String[]{"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3",};
    }

    public String draw() {
        String out = "";
        out += "   1   2   3\n";
        out += "A    |   |  \n";
        out += "  ---+---+---\n";
        out += "B    |   |  \n";
        out += "  ---+---+---\n";
        out += "C    |   |  \n";
        return out;
    }
    public String draw(boolean f) {
        String out = "";
        out += "   1   2   3\n";
        out += "A  "+Game.getSign(this.mainArray[0])+" | "+Game.getSign(this.mainArray[1])+" | "+Game.getSign(this.mainArray[2])+"\n";
        out += "  ---+---+---\n";
        out += "B  "+Game.getSign(this.mainArray[3])+" | "+Game.getSign(this.mainArray[4])+" | "+Game.getSign(this.mainArray[5])+"\n";
        out += "  ---+---+---\n";
        out += "C  "+Game.getSign(this.mainArray[6])+" | "+Game.getSign(this.mainArray[7])+" | "+Game.getSign(this.mainArray[8])+"\n";

        return out;
    }

    public void initMainArray() {
        for (int i = 0; i < mainArray.length; i++) {
            mainArray[i] = -1;
        }
    }

    Field() {
        this.initMainArray();
    }
    public int getMainArraySize(){
        return mainArray.length;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getFieldIndex(String coords) {
        for (int i = 0; i < this.positionNumbers.length; i++) {
            if (this.positionNumbers[i].equals(coords))
                return i;
        }
        return -1;
    }

    public boolean setPosition(int index,int signId){
        if(this.mainArray[index] == -1) {
            this.mainArray[index] = signId;
            return true;
        }
        return false;
    }

    public boolean isWinCombination(int sign){
        //int[] tempArr;
        if(mainArray[0] == sign){
            if(mainArray[1] == sign && mainArray[2] == sign)
                return true;
            if(mainArray[3] == sign && mainArray[6] == sign)
                return true;
            if(mainArray[4] == sign && mainArray[8] == sign)
                return true;
        }
        if(mainArray[1] == sign) {
            if (mainArray[4] == sign && mainArray[7] == sign)
                return true;
        }
        if(mainArray[2] == sign) {
            if (mainArray[4] == sign && mainArray[6] == sign)
                return true;
        }
        if(mainArray[3] == sign) {
            if (mainArray[4] == sign && mainArray[5] == sign)
                return true;
        }
        if(mainArray[6] == sign) {
            if (mainArray[7] == sign && mainArray[8] == sign)
                return true;
        }
        return false;
    }
}
