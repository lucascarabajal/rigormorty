package com.disenio.rigormorty.security;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Autowired
    private Environment env;

    public String getTokenSecret(){
        return env.getProperties().getProperty("tokenSecret");
    }
}
