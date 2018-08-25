package com.example.blog.website.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
/**
 * 向mvc中添加自定义组件
 * Created by BlueT on 2018/8/25.
 */
@Component
public class WebMvcConfig  extends WebMvcConfigurerAdapter {
    @Resource
    private BaseInterceptor baseInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }

}
