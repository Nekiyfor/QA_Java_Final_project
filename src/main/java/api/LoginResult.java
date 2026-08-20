package api;

import lombok.Getter;

@Getter
public class LoginResult {
    private final String accessToken;
    private final UserProfile user;

    public LoginResult(String accessToken, UserProfile user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}

