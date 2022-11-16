package com.kidcools.testboot.utils;

import java.util.Random;

public class IPUtils2 {
    private static Random random = new Random();
    public static String getRandomIp(){
        String ip = String.format("%d.%d.%d.%d" ,random.nextInt(254)+1,random.nextInt(254)+1,random.nextInt(254)+1,random.nextInt(254)+1);
        return ip;
    }
}