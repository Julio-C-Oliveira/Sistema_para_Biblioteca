package login;
import database.User.GetUser;
import user.User;
import user.UserDTO;


public class Login {

    private Boolean validateLogin(User user){
        UserDTO userDTO = GetUser.getUser(user.getName(), user.getType(), user.getPassword());
        if (user.getName() == userDTO.getName() && user.getPassword() == userDTO.getPassword()) {
            return true;
        }
        return false;
    }

}
