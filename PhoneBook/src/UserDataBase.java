import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDataBase {
    private File file;
    private long lastId;
    private HashMap<Long, User> userDB;
    private boolean existData;
    private final String fileName = "UsersDB.txt";
    private final String settingsFileName = "settings.txt";
    private final long initId = 1L;

    {
        userDB = new HashMap<>();
        lastId = initId;
    }

    UserDataBase() throws NullPointerException, IOException, DataNotFoundException {

        try {
            MainPB.checkWorkDir();
            file = new File(MainPB.workDirName + "/" + fileName);
            if (file.exists() && file.length() > 0) {
                System.out.println("DEBUG! UsersDB length = " + file.length());
                FileReader fr = new FileReader(file);
                char[] buffer = new char[(int) file.length()];
                int countRead = fr.read(buffer);
                if (countRead > 0) {
                    lastId = getLastId();

                    //id \t login \t name \t password
                    String tmp = "";
                    int countField = 0;
                    List<String> line = new ArrayList<>();
                    for (int i = 0; i < buffer.length; i++) {
                        if (buffer[i] == '\t') {
                            line.add(tmp);
                            tmp = "";
                            countField++;
                        } else if (buffer[i] == '\n') {
                            if (countField == 3) {
                                line.add(tmp);
                                tmp = "";
                                long id = Long.parseLong(line.get(0));
                                userDB.put(id, new User(id, line.get(1), line.get(2), line.get(3)));
                                line.clear();
                                countField = 0;
                            } else {
                                throw new DataNotFoundException("Нарушение целостности данных в БД пользователей.");
                            }
                        } else {
                            tmp += buffer[i];
                        }
                    }
                    existData = true;
                }
            } else {
                if (file.createNewFile()) {
                    writeLastId(lastId);
                    existData = false;
                } else {
                    throw new IOException("Невозможно создать файл пользователей.");
                }
            }
        } catch (NullPointerException | IOException npe) {
            throw npe;
        }
    }

    public String getUsersNames() {
        String out = "";
        long count = 0;
        if (existData) {
            for (Long id : userDB.keySet()) {
                User curUser = userDB.get(id);
                out += ++count + ". " + curUser.getName() + "\n";
            }
        }
        return out;
    }

    public boolean isLoginExists(String login) {
        if (existData) {
            for (Long id : userDB.keySet()) {
                User curUser = userDB.get(id);
                if (curUser.getLogin().equals(login))
                    return true;
            }
        }
        return false;
    }

    public User getUserByLogin(String login) {
        if (existData) {
            for (Long id : userDB.keySet()) {
                User curUser = userDB.get(id);
                if (curUser.getLogin().equals(login))
                    return curUser;
            }
        }
        return null;
    }

    public void addUser(User user) throws IOException, DataNotFoundException, SecurityException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(lastId + "\t" + user.getLogin() + "\t" + user.getName() + "\t" + user.getPasswordHash() + "\n");
        fw.close();
        File userDir = new File(String.valueOf(lastId));
        if (!userDir.exists()) {
            if (!userDir.mkdir())
                throw new SecurityException("Невозможно создать директорию для файлов пользователя.");
        }
        writeLastId(++lastId);

    }

    public long getLastId() throws NullPointerException, IOException, NumberFormatException {
        try {
            MainPB.checkWorkDir();
            File idFile = new File(MainPB.workDirName + "/" + settingsFileName);
            if (idFile.exists()) {
                char[] buffer = new char[(int) idFile.length()];
                FileReader fr = new FileReader(idFile);
                int countData = fr.read(buffer);
                String tmp = "";
                for (int i = 0; i < countData; i++) {
                    if (buffer[i] == '\n')
                        break;
//                    else if (buffer[i] >= 48 && buffer[i] <= 57)
                    else if (Character.isDigit(buffer[i]))
                        tmp += buffer[i];
                }
                return Long.parseLong(tmp);
            } else {
                writeLastId(initId);
                return initId;
            }
        } catch (NullPointerException | IOException | NumberFormatException ex) {
            throw ex;
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLastId(long id) throws IOException, DataNotFoundException {
        File idFile = new File(MainPB.workDirName + "/" + settingsFileName);
        if (idFile.exists()) {
            FileWriter fw = new FileWriter(idFile, false);
            fw.write(id + "\n");
            fw.close();
        } else {
            if (idFile.createNewFile()) {
                FileWriter fw = new FileWriter(idFile);
                fw.write(id + "\n");
                fw.close();
            } else {
                throw new IOException("Невозможно создать служебный файл для пользователя.");
            }
        }
    }
}
