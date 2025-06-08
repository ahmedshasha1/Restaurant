package com.resturant.util;

import java.util.UUID;

public class UserCode {

    public static String userCode(){
        return UUID.randomUUID().toString();
    }
}
