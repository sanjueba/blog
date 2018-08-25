package com.example.blog.website.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * create by liangyongsen ,date:2018/8/21
 */
@Component
public class WebConst {
    public static Map<String, String> initConfig = new HashMap<>();

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;
    public static String LOGIN_SESSION_KEY = "login_user";


}
