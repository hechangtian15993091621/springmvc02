package cn.buaa;

public class User {
    private final int id;
    private final String username;
    private final String psw;

    public User(int id, String username, String psw) {
        this.id = id;
        this.username = username;
        this.psw = psw;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }
}
