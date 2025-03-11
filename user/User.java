package user;

import display.UserTypes;

public class User {
    private String name;
    private UserTypes type;
    private String password;

    public User(String name, UserTypes type, String password){
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
