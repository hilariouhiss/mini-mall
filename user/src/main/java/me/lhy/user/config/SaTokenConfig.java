package me.lhy.user.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/swagger-ui.html")
                .addExclude("/user/register")
                .addExclude("/user/login")
                .addExclude("/druid/*")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证
                    SaSameUtil.checkCurrentRequestToken();
                })
                .setError(e -> SaResult.error(e.getMessage()));
    }
}
