package FileTest;

import java.io.File;

public class MainFileTest {
    public static void main(String[] args) {
        File cur = new File(".");
        System.out.println(cur.getAbsolutePath());
    }
}
