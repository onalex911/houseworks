public class User {
    private String name;
    private String login;
    private String passwordHash;

    public User(String login, String name, String passwordHash) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
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
