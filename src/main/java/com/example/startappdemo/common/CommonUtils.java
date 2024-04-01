package com.example.startappdemo.common;

import java.sql.Timestamp;
import java.util.List;

public class CommonUtils {
    public static final List<String> POSSIBLE_IP_HEADERS = List.of(
            "X-Forwarded-For",
            "HTTP_FORWARDED",
            "HTTP_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_CLIENT_IP",
            "HTTP_VIA",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "REMOTE_ADDR"
    );

    public static int disconnectionInterval = 3;

    public static Boolean getIsOnline(Timestamp timeDisconect){

        if(timeDisconect == null){
            return true;
        }
//        Long time =   timeDisconect.getTime();
//        Long timeNow = new Timestamp(System.currentTimeMillis()).getTime();
//        if(Math.abs(timeNow - time) / 1000  <= disconnectionInterval){
//            return true;
//        }
        return false;
    }
}
