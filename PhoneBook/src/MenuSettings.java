public class MenuSettings{
    private String menuText;
    private int maxValue;
    private int exitValue;

    public MenuSettings(String menuText, int maxValue, int exitValue) {
        this.menuText = menuText;
        this.maxValue = maxValue;
        this.exitValue = exitValue;
    }

    public String getMenuText() {
        return menuText;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getExitValue() {
        return exitValue;
    }
}
