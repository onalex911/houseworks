public class Field {
    private final int numSticks = 10;      // исходное кол-во палочек
    private final int minSticksTake = 1;  // минимальное количество палочек, которое можно брать
    private final int maxSticksTake = 3;  // максимальное количество палочек, которое можно брать
    private final int sticksToLose = 1;   // кол-во оставшихся палочек, означающих проигрыш
    private int sticksRest;     //кол-во оставшихся палочек в игре


    public String drawSticks() {
        String out = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < this.sticksRest; j++) {
                out += " #  ";
            }
            out += "\n";
        }
        out += "Осталось палочек: " + this.sticksRest;
        return out;
    }

    Field() {
        this.sticksRest = this.numSticks;
    }

    // "взять" указанное кол-во палочек
    public void takeSticks(int number) throws Exception {
        if (number < this.minSticksTake || number > this.maxSticksTake || number >= this.sticksRest)
            throw new Exception("Указано недопустимое количество палочек (" + number + ")");
        this.sticksRest -= number;
    }

    public int getMinSticksTake() {
        return minSticksTake;
    }

    public int getMaxSticksTake() {
        return maxSticksTake;
    }

    public int getSticksToLose() {
        return sticksToLose;
    }

    public int getSticksRest() {
        return this.sticksRest;
    }

    //кол-во палочек, которое нужно оставить сопернику, чтобы он проиграл
    public int getWinNumber() {
        return this.minSticksTake + this.maxSticksTake + this.sticksToLose;
    }
}
