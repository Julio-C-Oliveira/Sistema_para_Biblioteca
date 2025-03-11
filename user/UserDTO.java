package user;

import display.UserTypes;

public class UserDTO {
    private int id;
    private String name;
    private UserTypes type;
    private String password;

    public UserDTO(int id, String name, UserTypes type, String password){
        this.id = id;
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public int getID(){
        return id;
    }
    public String getName() {
        return name;
    }

    public UserTypes getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    
}
