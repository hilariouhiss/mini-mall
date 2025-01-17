package me.lhy.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "角色管理", description = "角色管理相关接口")
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
}
