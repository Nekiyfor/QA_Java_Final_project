package api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegData {
    private String email;
    private String password;
    private String submitPassword;

    public UserRegData(String email, String password) {
        this.email = email;
        this.password = password;
        this.submitPassword = password;
    }

}
