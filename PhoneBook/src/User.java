public class User {
    private long id;
    private String name;
    private String login;
    private String passwordHash;

    public User(String login, String name, String passwordHash) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
    }
    public User(long id,String login, String name, String passwordHash) {
        this(login,name,passwordHash);
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
