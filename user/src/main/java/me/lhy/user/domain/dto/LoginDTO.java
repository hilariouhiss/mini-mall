package me.lhy.user.domain.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class LoginDTO implements Serializable {

    private String username;
    private String password;

    public LoginDTO(){}

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
