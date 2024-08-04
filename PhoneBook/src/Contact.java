import java.util.stream.IntStream;

public class Contact {
    private long userId;
    private long contactId;
    private String contactName;
    private String contactSurname;
    private String contactNumberText;
    private long contactNumber;

    public Contact(long userId, long contactId, String contactName, String contactSurname, String contactNumberText) {
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactNumberText = contactNumberText;
        this.contactNumber = strToLong(contactNumberText);
    }

    public long getUserId() {
        return userId;
    }

    public long getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public String getContactNumberText() {
        return contactNumberText;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public void setContactNumber(String contactNumberText) {
        this.contactNumberText = contactNumberText;
        this.contactNumber = strToLong(contactNumberText);
    }

    static long strToLong(String str){
        IntStream streamFromString = str.chars();
        String txtResult = streamFromString
                .filter(Character::isDigit)
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("",String::concat);

        return Long.parseLong(txtResult);
    }
}
