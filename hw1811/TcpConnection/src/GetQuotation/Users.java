package GetQuotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Users {
    private static final String AUTH_PATH = "data/auth.txt";
    private HashMap<String,String> usersList;

    {
        usersList = new HashMap<>();
    }

    public Users() {
        try {
            File file = new File(AUTH_PATH);
            if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str;

            String[] authPair = new String[2];
            while((str = reader.readLine()) != null ){
                if(!str.isEmpty()){
                    authPair = str.split(":");
                    usersList.put(authPair[0],authPair[1]);
                }}
            } else {
                    throw new IOException("Не найден файл пользователей.");
            }
        } catch (IOException ioex) {
            System.out.println("Ошибка ввода-вывода: " + ioex.getMessage());
        }
    }

    public Boolean checkUser(String name){
        return usersList.containsKey(name);
    }
    public String getUserPass(String name){
        return usersList.get(name);
    }

    public Boolean checkUserPass(String user, String password){
        return usersList.get(user).equals(password);
    }
}
