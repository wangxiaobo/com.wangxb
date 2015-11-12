package com.xst.utils;

import java.util.UUID;

public class CommonUtil {
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
    
}
