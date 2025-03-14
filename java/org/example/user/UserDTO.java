package org.example.user;

import org.example.display.UserTypes;

public class UserDTO {
    private int id;
    private String name;
    private UserTypes role;
    private String password;

    public UserDTO(int id, String name, UserTypes role, String password){
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public int getID(){
        return id;
    }
    public String getName() {
        return name;
    }

    public UserTypes getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
    
}
