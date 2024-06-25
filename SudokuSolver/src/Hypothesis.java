public class Hypothesis {
    private char[] hyps;
    private String hypStr;

    private int size;

    {
        hyps = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        hypStr = "123456789";
        size = hyps.length;
    }

    public char[] getHyps() {
        return hyps;
    }

    public String printHyps() {
        String out = "";
        for (int i = 0; i < size; i++) {
            out += hyps[i] + " ";
        }
        return out;
    }

    public char getLastHyp() {
        return size == 1 ? hyps[0] : '.';
    }


    public int delHyp(char val) {
        for (int i = 0; i < size; i++) {
            if (hyps[i] == val) {
                for (int j = i; j < size-1; j++) {
                    hyps[j] = hyps[j + 1];
                }
                size--;
                makeHypStr();
                break;
            }
        }
        return size;
    }

    public boolean isValInHyps(char val) {
        for (int i = 0; i < size; i++) {
            if (hyps[i] == val)
                return true;
        }
        return false;
    }

    private void makeHypStr() {
        String out = "";
        for (int i = 0; i < size; i++) {
            out += hyps[i];
        }
        hypStr = out;
    }

    public String getHypStr() {
        return hypStr;
    }

    public int getSize() {
        return size;
    }
}
