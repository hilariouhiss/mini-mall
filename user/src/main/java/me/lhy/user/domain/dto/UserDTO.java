package me.lhy.user.domain.dto;

import lombok.Getter;
import lombok.Setter;
import me.lhy.user.domain.po.User;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private Boolean deleted;
}
