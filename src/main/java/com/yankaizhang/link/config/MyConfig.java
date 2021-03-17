package com.yankaizhang.link.config;

import com.yankaizhang.spring.context.annotation.Bean;
import com.yankaizhang.spring.context.annotation.Configuration;
import com.yankaizhang.spring.context.annotation.EnableAspectJAutoProxy;
import com.yankaizhang.spring.web.ViewResolver;
import com.yankaizhang.spring.web.view.JspView;

import java.util.regex.Pattern;

@Configuration
@EnableAspectJAutoProxy
public class MyConfig {

    @Bean
    public ViewResolver internalResourceViewResolver(){
        ViewResolver resolver = new ViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setViewClass(JspView.class);
        return resolver;
    }

    @Bean
    public Pattern urlPattern(){
        return Pattern.compile("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 这里修改短链接基础地址
     */
    @Bean
    public String baseSite(){
        return "http://localhost:8080/s?l=";
    }
}
