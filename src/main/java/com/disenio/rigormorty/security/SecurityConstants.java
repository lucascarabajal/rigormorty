package com.disenio.rigormorty.security;

import com.disenio.rigormorty.SpringApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class SecurityConstants {
    public static final long EXPIRATION_DATE = 864000000; // 10 días
    public static final String LOGIN_URL = "/user/login";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }

}
