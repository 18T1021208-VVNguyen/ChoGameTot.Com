package com.example.startappdemo.websocket.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final String SIMP_SESSION_ID_CHAT_ROM = "SessionIDChatRom";
    public static final String SIMP_SESSION_ID_TRACKING_USER_ON = "SessionIDTrackingUserOn";

    public static String findSimpSessionId( String path){
        String simpSessionID = null;
        Pattern simpSessionIDPattern = Pattern.compile("/([^/]+)/websocket");
        Matcher matcherSimpSessionID = simpSessionIDPattern.matcher(path);
        if(matcherSimpSessionID.find()){
            simpSessionID = matcherSimpSessionID.group(1);
        }
        return simpSessionID;

    }
}
