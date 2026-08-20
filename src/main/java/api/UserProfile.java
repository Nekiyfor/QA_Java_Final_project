package api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfile {
    public int id;
    public String name;
    public String email;
    public Object avatar;
    public boolean admin;

}
