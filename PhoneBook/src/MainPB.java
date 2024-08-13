import java.io.File;

public class MainPB {

    public static String workDirName = "DB";

    static void checkWorkDir() throws NullPointerException, SecurityException {
        File dir = new File(workDirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void main(String[] args) {
        try {
            MenuHandler mh = new MenuHandler("LoginMenu");
            mh.execute();
        } catch (Exception ex) {
            System.out.println(MenuHandler.errMsg + ex.getMessage() + "Программа прервана!");
        }
        System.out.println("\nДо новых встреч!");
    }
}
