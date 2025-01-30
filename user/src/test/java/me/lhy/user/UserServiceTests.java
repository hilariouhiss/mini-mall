package me.lhy.user;

import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        // 创建用户
        UserDto userDto = new UserDto();
        // 设置用户名和密码
        userDto.setUsername("testUser");
        userDto.setPassword("asdfghjkl");

        // 注册
        userService.add(userDto);
    }
}
