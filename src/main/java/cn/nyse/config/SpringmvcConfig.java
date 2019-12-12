package cn.nyse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc配置：
 * 1.开启包扫描扫描controller层
 * 2.开启mvc支持
 * 3.静态资源放行
 */
@Configuration
@ComponentScan(basePackages = "cn.nyse.controller")
@EnableWebMvc
public class SpringmvcConfig implements WebMvcConfigurer {

    /*
    * 设置静态资源放行，使用默认servlet实现     替代mvc:default-servlet-handler
    * */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //替代逐个静态资源映射配置
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }*/
}
