package hw1507;

public class MaskParts {
    private int type;
    private String part;

    public MaskParts(int type, String part) {
        this.type = type; // 0 - конец маски, 1 - единичный символ, 2 - множество символов
        this.part = part; // может быть пустая - тогда всё определяет символ маски
    }

    public int getType() {
        return type;
    }

    public String getPart() {
        return part;
    }

    @Override
    public String toString() {
        return type + " : " + part;
    }
}
