import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

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
        }catch(Exception ex){
            System.out.println(MenuHandler.errMsg+ex.getMessage()+"Программа будет прервана!");
        }

        /*Scanner scn = new Scanner(System.in);
        System.out.print("Enter mask: ");
        String str = scn.nextLine();
        System.out.println(Contact.strToLong(str)+", "+MenuHandler.checkPhoneNumber(str));*/

//        String template = mask; //"^"+mask.trim()+"$";
//        template = template.replace(".","\\".concat("."));
//        template = template.replace("+","\\".concat("+"));
//        template = template.replace("(","\\".concat("("));
//        template = template.replace(")","\\".concat(")"));
//        template = template.replace("_",".");
//        template = template.replace("*",".*");
//        template = template.replace(" ","\\".concat("s"));
//
//        System.out.println(template+": "+ str.matches(template));
        //System.out.println(MenuHandler.checkPhoneNumber("31"));
        //System.out.println("|"+MenuHandler.insertName("Alibaba")+"|");
        System.out.println("\n\nДо новых встреч!");
    }
}
