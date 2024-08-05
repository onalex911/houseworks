import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ContactsDataBase {
    private File file;
    private long lastId;
    private long userId;
    private String userIdText;
    private List<Contact> contactDB;
    private boolean existData;
    private final String fileName = "ContactDB.txt";
    private final String settingsFileName = "settings.txt";
    private final long initId = 1L;

    {
        contactDB = new ArrayList<>();
        lastId = initId;
    }

    ContactsDataBase(long userId) throws NullPointerException, IOException, DataNotFoundException {
        this.userId = userId;
        this.userIdText = String.format("%010d", userId);
        //MainPB.checkWorkDir();
        File dir = new File(MainPB.workDirName + "/" + this.userIdText);
        if (!dir.exists()) {
            dir.mkdir();
        }
        file = new File(MainPB.workDirName + "/" + this.userIdText + "/" + fileName);
        if (file.exists() && file.length() > 0) {
            System.out.println("DEBUG! ContactDB for userId:" + userId + " length = " + file.length());
        } else {
            if (file.createNewFile()) {
                writeLastId(lastId);
                existData = false;
            } else {
                throw new IOException("Невозможно создать файл контактов.");
            }
        }
    }

    private void getContactDB() throws FileNotFoundException, IOException, DataNotFoundException {
        FileReader fr = new FileReader(file);
        char[] buffer = new char[(int) file.length()];
        int countRead = fr.read(buffer);
        if (countRead > 0) {
            //lastId = getLastId();
            //id \t name \t surname \t phoneNumber \t phoneNumberLong
            String tmp = "";
            int countField = 0;
            List<String> line = new ArrayList<>();
            for (int i = 0; i < countRead; i++) {
                if (buffer[i] == '\t') {
                    line.add(tmp);
                    tmp = "";
                    countField++;
                } else if (buffer[i] == '\n') {
                    if (countField == 3) {
                        line.add(tmp);
                        tmp = "";
                        contactDB.add(new Contact(Long.parseLong(line.get(0)), line.get(1), line.get(2), line.get(3)));
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
        }
    }
static int Num = 1;
    public String getContactsForPrint(String mask) throws DataNotFoundException, IOException {
        String out = "";
//        int num = 1;
        if(contactDB.isEmpty())
            getContactDB();
        if(mask.isEmpty()){
            for(Contact contact:contactDB){
                out += (Num++)+". "+contact.getContactName()+" "+contact.getContactSurname()+" тел.: "+ contact.getContactNumberText()+"\n";
            }
        }else{
            contactDB.stream()
                    .filter(s ->{
//                        String template = mask;
                        String template = mask;//"^"+mask+"$";
                        template = template.replace(".","\\".concat("."));
                        template = template.replace("+","\\".concat("+"));
                        template = template.replace("(","\\".concat("("));
                        template = template.replace(")","\\".concat(")"));
                        template = template.replace("_",".");
                        template = template.replace("*",".*");
                        template = template.replace(" ","\\".concat("s"));
                        return s.getContactNumberText().matches(template);
                    })
                    .forEach(c->{
                        System.out.println((Num++)+". "+c.getContactNumberText()+" "+c.getContactName()+" "+c.getContactSurname());
                    } );
        }
        return out;
    }

    public void addContact(Contact contact) throws IOException, DataNotFoundException, SecurityException {
        FileWriter fw = new FileWriter(file, true);
        lastId = getLastId();
//        fw.write(lastId + "\t" + contact.getUserId() + "\t" + contact.getContactName() + "\t" + contact.getContactSurname() + "\t" + contact.getContactNumberText() + "\t" + contact.getContactNumber() + "\n");
        fw.write(lastId + "\t" + contact.getContactName() + "\t" + contact.getContactSurname() + "\t" + contact.getContactNumberText() + "\t" + contact.getContactNumber() + "\n");
        fw.close();
        writeLastId(++lastId);
        System.out.println("\nНовый контакт успешно записан!");
    }

    public void writeLastId(long id) throws IOException, DataNotFoundException {
        File idFile = new File(MainPB.workDirName + "/" + userIdText + "/" + settingsFileName);
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
            File idFile = new File(MainPB.workDirName + "/" + this.userIdText + "/" + settingsFileName);
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
