package cn.hhu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @author tlj
 */
@Configuration
public class crosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")

                // 设置放行那些原始域
                .allowedOriginPatterns("*")
                // 是否需要凭证
                .allowCredentials(true)
                // 放行那些请求方式
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                // .allowedMethods("*")     // 放行全部
                // 放行那些原始请求头部信息
                .allowedHeaders("*")
                // 暴露那些原始请求头部信息
                .exposedHeaders("*");


    }
}
