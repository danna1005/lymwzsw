package com.thiscc.tools.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BizFactory {

    private static ClassPathXmlApplicationContext context;

    public static Object getBean(String paramString) {
        if (context == null)
            context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        return context.getBean(paramString);
    }
}