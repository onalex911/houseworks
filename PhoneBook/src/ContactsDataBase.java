import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContactsDataBase {
    private final File file;
    private int lastId;
    private final long userId;
    private final String userIdText;
    private final List<Contact> contactDB;
    private boolean existData;
    private List<Contact> foundContacts;

    private final String fileName = "ContactDB.txt";
    private final String settingsFileName = "settings.txt";
    private final int initId = 1;
    private final int posInPage = 5;
    private static int Num = 1;

    {
        contactDB = new ArrayList<>();
        lastId = initId;
        existData = false;
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
        if (file.exists()) {
            System.out.println("DEBUG! ContactDB for userId:" + userId + " length = " + file.length());
        } else {
            if (file.createNewFile()) {
                writeLastId(lastId);
                //existData = false;
            } else {
                throw new IOException("Невозможно создать файл контактов.");
            }
        }
        if(file.length() > 0)
            existData = true;
    }

    public boolean isExistData() {
        return existData;
    }

    public int getFoundContactsSize() {
        return foundContacts == null ? 0 : foundContacts.size();
    }
    public int getFoundContactId() {
        return getFoundContactsSize() != 0 ? foundContacts.getFirst().getId() : 0;
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
                    if (++countField == Contact.feldsLenght) {
                        line.add(tmp);
                        tmp = "";
                        contactDB.add(new Contact(userId, Integer.parseInt(line.get(0)), line.get(1), line.get(2), line.get(3)));
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

    //fromDB = true - получаем ConatctDB, затем получаем из нее запись
    //fromDB = false - получаем запись напрямую из файла
    public Contact getContactById(int id, boolean fromDB) throws FileNotFoundException, IOException, DataNotFoundException {

        if (fromDB) {
            if (contactDB.isEmpty()) {
                getContactDB();
            }
            return contactDB.stream().filter(c -> c.getId() == id).findFirst().get();
        } else {
            FileReader fr = new FileReader(file);
            char[] buffer = new char[(int) file.length()];
            int countRead = fr.read(buffer);
            if (countRead > 0) {

                String tmp = "";
                int countField = 0;
                List<String> line = new ArrayList<>();
                boolean found = false;
                for (int i = 0; i < countRead; i++) {
                    if (buffer[i] == '\t') {
                        if (found || Integer.parseInt(tmp) == id) {
                            line.add(tmp);
                            tmp = "";
                            countField++;
                            found = true;
                        } else {
                            tmp = "";
                            while (buffer[i] != '\n')
                                i++;
                        }
                        continue;
                    } else if (buffer[i] == '\n' && found) {
                        if (++countField == Contact.feldsLenght) {
                            line.add(tmp);
                            existData = true;
                            return new Contact(userId, Integer.parseInt(line.get(0)), line.get(1), line.get(2), line.get(3));
                        } else {
                            throw new DataNotFoundException("Нарушение целостности данных в БД контакта.");
                        }
                    } else {
                        tmp += buffer[i];
                    }
                }
            }
        }
        return null;
    }

    public void editContact(Contact contact) throws IOException, DataNotFoundException {
        if(contactDB.isEmpty()) {
            getContactDB();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(buildStringForWrite(contact));
        contactDB.stream().filter(s->s.getId() != contact.getId()).forEach(c-> {
            try {
                fw.write(buildStringForWrite(c));
            } catch (IOException e) {
                throw new RuntimeException(MenuHandler.errMsg + "Невозможно записать отредактированные данные: " + e.getMessage());
            }
        });
        fw.close();

    }
    public void printByMask(String mask) throws DataNotFoundException, IOException {

        Num = 1;
        /*if (contactDB.isEmpty()) {
            getContactDB();
        }

        contactDB.stream()
                .filter(s -> {
                    String template = "";
                    //String strForSearch = s.getContactName().concat(s.getContactSurname().concat(s.getContactNumberText()));
                    if (mask.isEmpty()) {
                        template = ".*";
                    } else {
                        template = mask.toLowerCase();//"^"+mask+"$";
                        template = template.replace(".", "\\".concat("."));
                        template = template.replace("+", "\\".concat("+"));
                        template = template.replace("(", "\\".concat("("));
                        template = template.replace(")", "\\".concat(")"));
                        template = template.replace("_", ".");
                        template = template.replace("*", ".*");
                        template = template.replace(" ", "\\".concat("s"));

                    }
                    return s.getName().toLowerCase().matches(template) ||
                            s.getSurname().toLowerCase().matches(template) ||
                            s.getNumberText().toLowerCase().matches(template) ||
                            String.valueOf(s.getNumber()).toLowerCase().matches(template);
                })
                .forEach(c -> {
                    System.out.println((Num++) + ".\t" + c.getName() + "\t" + c.getSurname() + "\t" + c.getNumberText());
                });*/
        getContactsByMask(mask, "");
        foundContacts.stream().forEach(c -> System.out.println((Num++) + ".\t" + c.getName() + "\t" + c.getSurname() + "\t" + c.getNumberText()));

    }
    public List<Contact> getAllContacts() throws DataNotFoundException, IOException {
        getContactsByMask("*","");
        return contactDB;
    }

    public void getContactsByMask(String mask, String field) throws DataNotFoundException, IOException {

        if (contactDB.isEmpty()) {
            getContactDB();
        }
        foundContacts = contactDB.stream()
                .filter(s -> {
                    String template = "";
                    //String strForSearch = s.getContactName().concat(s.getContactSurname().concat(s.getContactNumberText()));
                    if (mask.isEmpty()) {
                        template = ".*";
                    } else {
                        template = mask.toLowerCase();//"^"+mask+"$";
                        template = template.replace(".", "\\".concat("."));
                        template = template.replace("+", "\\".concat("+"));
                        template = template.replace("(", "\\".concat("("));
                        template = template.replace(")", "\\".concat(")"));
                        template = template.replace("_", ".");
                        template = template.replace("*", ".*");
                        template = template.replace(" ", "\\".concat("s"));

                    }
                    switch (field) {
                        case "name":
                            return s.getName().toLowerCase().matches(template);
                        case "surname":
                            return s.getSurname().toLowerCase().matches(template);
                        case "number":
                            return s.getNumberText().toLowerCase().matches(template) ||
                                    String.valueOf(s.getNumber()).toLowerCase().matches(template);
                        default:
                            return s.getName().toLowerCase().matches(template) ||
                                    s.getSurname().toLowerCase().matches(template) ||
                                    s.getNumberText().toLowerCase().matches(template) ||
                                    String.valueOf(s.getNumber()).toLowerCase().matches(template);
                    }
                })
                .toList();

//        return out;

    }

    public void printContactsByMask(String mask) throws DataNotFoundException, IOException {
        if (foundContacts == null) {
            getContactsByMask(mask, "");
        }
        foundContacts.forEach(c -> System.out.println(c.toString()));

    }

    public void deleteFoundContacts(int id) throws DataNotFoundException, IOException {
        if (getFoundContactsSize() == 0) {
            throw new DataNotFoundException("Нет данных для удаления.");
        }

        if (contactDB.isEmpty()) {
            getContactDB();
        }

        if (id > 0 && contactDB.stream().noneMatch(c -> c.getId() == id)) {
            throw new DataNotFoundException("Указанная запись не содержится в списке контактов.");
        }

        List<Contact> contForSave = new ArrayList<>();
        for (Contact contact : contactDB) {
            boolean doAdd = true;
            if (id == 0) {
                for (Contact c : foundContacts) {
                    if (contact.getId() == c.getId()) {
                        doAdd = false;
                        break;
                    }
                }
            } else {
                if (contact.getId() == id)
                    doAdd = false;
            }
            if (doAdd)
                contForSave.add(contact);
        }
        if (!contForSave.isEmpty()) {
            FileWriter fw = new FileWriter(file, false);
            for (Contact contact : contForSave) {
                fw.write(buildStringForWrite(contact));
            }
            fw.close();
        } else {
            throw new DataNotFoundException("Нет данных для записи в файл.");
        }
    }

    public List<Contact> getContactListButId(int id) throws DataNotFoundException, IOException {
        if(contactDB.isEmpty())
            getContactDB();

        List<Contact> out = new ArrayList<>();
        for (Contact contact : contactDB) {
            if (contact.getId() != id)
                out.add(contact);
        }
        return out;
    }

    public void addContact(Contact contact) throws IOException, DataNotFoundException, SecurityException {
        FileWriter fw = new FileWriter(file, true);
        lastId = getLastId();
//        fw.write(lastId + "\t" + contact.getUserId() + "\t" + contact.getContactName() + "\t" + contact.getContactSurname() + "\t" + contact.getContactNumberText() + "\t" + contact.getContactNumber() + "\n");
        contact.setId(lastId);
        fw.write(buildStringForWrite(contact));
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

    public int getLastId() throws NullPointerException, IOException, NumberFormatException {
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
                return Integer.parseInt(tmp);
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

    public void printPaged() {
        printPaged(foundContacts, 1);
    }

    public void printPaged(List<Contact> contacts, int page) {

        int start = --page * posInPage;
        Num = start + 1;
        int delta = contacts.size() % posInPage > 0 ? 1 : 0;
        int totalPages = contacts.size() / posInPage + delta;

        contacts.stream().skip(start)
                .limit(posInPage)
                .forEach(c -> System.out.println((Num++) + ".\t" + c.getName() + "\t" + c.getSurname() + "\t" + c.getNumberText()));

        System.out.println(MenuHandler.separator);

        if (totalPages > 1) {
            System.out.print("Страницы: ");
            for (int i = 1; i <= totalPages; i++) {
                System.out.printf("%d ", i);
            }
            while (true) {
                System.out.print("\n\nВыберите номер страницы для просмотра (0 - выход): ");
                Scanner scn = new Scanner(System.in);
                try {
                    int nextPage = scn.nextInt();
                    if (nextPage == 0)
                        return;
                    if (nextPage > totalPages) {
                        System.out.println(MenuHandler.warnMsg + MenuHandler.wrongValue + MenuHandler.tryAgain);
                        continue;
                    }
                    printPaged(contacts, nextPage);
                    break;
                } catch (InputMismatchException imex) {
                    System.out.println(MenuHandler.warnMsg + MenuHandler.wrongValue + MenuHandler.tryAgain);
                }
            }
        }
    }


    public static String buildStringForWrite(Contact contact) {
        return contact.getId() + "\t" + contact.getName() + "\t" + contact.getSurname() + "\t" + contact.getNumberText() + "\n";
    }
}
