import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactsDataBase {
    private File file;
    private long lastId;
    private long userId;
    private String userIdText;
    private HashMap<Integer, Contact> contDB;
    private boolean existData;
    private final String fileName = "ContactDB.txt";
    private final String serviceFileName = "service.txt";
    private final long initId = 1L;

    {
        contDB = new HashMap<>();
        lastId = initId;
    }

    ContactsDataBase(long userId) throws NullPointerException, IOException, DataNotFoundException {
        this.userId = userId;
        this.userIdText = String.format("%010d", userId);
        //MainPB.checkWorkDir();
        File dir = new File(MainPB.workDirName + "/" + this.userIdText);
        if(!dir.exists()){
            dir.mkdir();
        }
        file = new File(MainPB.workDirName + "/" + this.userIdText + "/" + fileName);
        if (file.exists() && file.length() > 0) {
            System.out.println("DEBUG! ContactDB for userId:" + userId + " length = " + file.length());
            /*FileReader fr = new FileReader(file);
            char[] buffer = new char[(int) file.length()];
            int countRead = fr.read(buffer);
            if (countRead > 0) {
                lastId = getLastId();
                //id \t name \t surname \t phoneNumber
                String tmp = "";
                int countField = 0;
                List<String> line = new ArrayList<>();
                for (int i = 0; i < buffer.length; i++) {
                    if (buffer[i] == '\t') {
                        if(countField == 0 && Long.parseLong(tmp) == userId) {
                            line.add(tmp);
                            tmp = "";
                            countField++;
                        }else{
                            //если не тот userId - пропускаем строку до конца
                            for (int j = ++i; j < buffer.length ; j++) {
                                if(buffer[j] == '\n'){
                                    i = j;
                                    continue;
                                }
                            }
                        }
                    } else if (buffer[i] == '\n') {
                        if (countField == 3) {
                            line.add(tmp);
                            tmp = "";
                            contDB.put(Integer.parseInt(line.get(0)), new Contact(Long.parseLong(line.get(0)),Long.parseLong(line.get(1)), line.get(2), line.get(3), line.get(4)));
                            line.clear();
                            countField = 0;
                        } else {
                            throw new DataNotFoundException("Нарушение целостности данных в БД контакта.");
                        }
                    } else {
                        tmp += buffer[i];
                    }
                }
                existData = true;
            }*/
        } else {
            if (file.createNewFile()) {
                writeLastId(lastId);
                existData = false;
            } else {
                throw new IOException("Невозможно создать файл контактов.");
            }
        }
    }

    public void addContact(Contact contact) throws IOException, DataNotFoundException, SecurityException {
        FileWriter fw = new FileWriter(file, true);
        lastId = getLastId();
        fw.write(lastId + "\t" + contact.getUserId() + "\t" + contact.getContactName() + "\t" + contact.getContactSurname() + "\t" + contact.getContactNumberText() + "\n");
        fw.close();
        writeLastId(++lastId);

    }

    public void writeLastId(long id) throws IOException, DataNotFoundException {
        File idFile = new File(MainPB.workDirName + "/" + userIdText + "/" + serviceFileName);
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

    public long getLastId() throws NullPointerException, IOException, NumberFormatException {
        try {
            MainPB.checkWorkDir();
            File idFile = new File(MainPB.workDirName + "/" + this.userIdText + "/" + serviceFileName);
            if (idFile.exists()) {
                char[] buffer = new char[(int) idFile.length()];
                FileReader fr = new FileReader(idFile);
                int countData = fr.read(buffer);
                String tmp = "";
                for (int i = 0; i < countData; i++) {
                    if (buffer[i] == '\n')
                        break;
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
}
