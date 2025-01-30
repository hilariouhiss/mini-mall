package me.lhy.user.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.util.Result;
import me.lhy.user.domain.dto.LoginDto;
import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理", description = "用户管理相关接口")
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册结果
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserDto user) {
        log.info("请求注册，username: {}", user.getUsername());
        userService.add(user);
        return Result.success();
    }

    /**
     * 使用username登录
     * @param loginDTO 登录信息
     * @return token信息
     */
    @Operation(summary = "使用username登录")
    @PostMapping("/login-username")
    public Result<SaTokenInfo> loginByUsername(@RequestBody LoginDto loginDTO) {
        log.info("请求登录，username: {}", loginDTO.getUsername());
        SaTokenInfo tokenInfo = userService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword());
        return Result.success(tokenInfo);
    }

    /**
     * 使用手机号登录
     * @param loginDto 登录信息
     * @return token信息
     */
    @Operation(summary = "使用手机号登录")
    @PostMapping("/login-phone")
    public Result<SaTokenInfo> loginByPhone(@RequestBody LoginDto loginDto){
        log.info("请求登录，phone: {}", loginDto.getPhoneNumber());
        SaTokenInfo tokenInfo = userService.loginByPhone(loginDto.getPhoneNumber(), loginDto.getPassword());
        return Result.success(tokenInfo);
    }

    /**
     * 分页查询所有用户
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return 用户列表
     */
    @Operation(summary = "分页查找所有用户")
    @GetMapping("/all")
    public Result<Page<UserDto>> selectAllWithPagination(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("请求查询所有用户");
        return Result.success(userService.selectAll(currentPage, pageSize));
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

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户
     */
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

    /**
     * 更新用户信息
     * @param user 新的用户信息
     * @return 更新后的用户信息
     */
    @Operation(summary = "根据主键更新用户信息")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody UserDto user) {
        log.info("请求更新用户，id: {}", user.getId());
        return Result.success();
    }
}
