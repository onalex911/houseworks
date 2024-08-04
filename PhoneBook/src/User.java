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

    /*public User(String login) {
        try {
            UserDataBase userDB = new UserDataBase();
            User user = userDB.getUserByLogin(login);
            this.id = user.getId();
            this.name = user.getName();
            this.login = login;
            this.passwordHash = user.getPasswordHash();
        }catch(Exception ex){
            this.id = 0;
        }
    }*/

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
