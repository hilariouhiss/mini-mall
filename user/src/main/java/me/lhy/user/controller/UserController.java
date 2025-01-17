package me.lhy.user.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.Result;
import me.lhy.user.domain.dto.LoginDto;
import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.service.UserService;
import me.lhy.user.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理", description = "用户管理相关接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // 注册
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserDto user) {
        log.info("请求注册，username: {}", user.getUsername());
        userService.register(user);
        return Result.success();
    }

    // 登录
    @Operation(summary = "使用username登录")
    @PostMapping("/login-username")
    public Result<SaTokenInfo> loginByUsername(@RequestBody LoginDto loginDTO) {
        log.info("请求登录，username: {}", loginDTO.getUsername());
        SaTokenInfo tokenInfo = userService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword());
        return Result.success(tokenInfo);
    }

    @PostMapping("/login-phone")
    public Result<SaTokenInfo> loginByPhone(@RequestBody LoginDto loginDto){
        log.info("请求登录，phone: {}", loginDto.getPhoneNumber());
        SaTokenInfo tokenInfo = userService.loginByPhone(loginDto.getPhoneNumber(), loginDto.getPassword());
        return Result.success(tokenInfo);
    }


    // TODO: 需要分页
    // 查找所有用户
    @Operation(summary = "查找所有用户")
    @GetMapping("/all")
    public Result<List<UserDto>> selectAll() {
        log.info("请求查询所有用户");
        return Result.success(userService.selectAll());
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    @Operation(summary = "根据id查询用户")
    @GetMapping("/id/{id}")
    public Result<UserDto> selectById(@PathVariable Long id) {
        log.info("请求查询用户，id: {}", id);
        return Result.success(userService.selectById(id));
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/name/{name}")
    public Result<UserDto> selectByName(@PathVariable String name) {
        log.info("请求查询用户，name: {}", name);
        return Result.success(userService.selectByUsername(name));
    }

    /**
     * 逻辑删除用户
     * @param id 用户id
     * @return 删除结果
     */
    @Operation(summary = "逻辑删除用户")
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        log.info("请求删除用户，id: {}", id);
        userService.deleteById(id);
        return Result.success();
    }
}
