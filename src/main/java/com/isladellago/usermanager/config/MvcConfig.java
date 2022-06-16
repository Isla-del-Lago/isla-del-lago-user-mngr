package com.isladellago.usermanager.config;

import com.isladellago.usermanager.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns(PathUtils.User.ROOT_PATH + "/**")
                .addPathPatterns(PathUtils.Token.ROOT_PATH + "/**")
                .excludePathPatterns(PathUtils.Health.ROOT_PATH + PathUtils.Health.HEALTH_PATH)
                .excludePathPatterns(PathUtils.Token.ROOT_PATH + PathUtils.Token.LOGIN)
                .excludePathPatterns(PathUtils.User.ROOT_PATH + PathUtils.User.CREATE_USER);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Autowired
    public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }
}
