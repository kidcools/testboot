package com.kidcools.testboot;

import com.kidcools.testboot.utils.IPUtils2;

import java.util.HashMap;

public  class Constant {
    public static class XvideosConstant{
        public static  final String XVIDEOS_SCHEMAL = "https";
        public static  final String XVIDEOS_HOST = "www.xvideos.com";
        public static  final String XVIDEOS_HOST_URL = "https://www.xvideos.com";
        public static  final String XVIDEOS_TYPE_NORMAL = "/new";
        public static  final String XVIDEOS_TYPE_GAY = "/gay";
        public static  final String XVIDEOS_TYPE_SHEMALE = "/shemale";
        //关键词搜索相关
        public static  final String XVIDEOS_SEARCH_SORT_RELEVANCE = "relevance";
        public static  final String XVIDEOS_SEARCH_SORT_UPLOADDATE = "uploaddate";
        public static  final String XVIDEOS_SEARCH_SORT_LENGTH = "length";
        public static  final String XVIDEOS_SEARCH_SORT_VIEWS = "views";
        public static  final String XVIDEOS_SEARCH_SORT_RANDOM = "random";
        public static  volatile  HashMap  headers;
        public static HashMap getHeaders(){
            if(headers==null){
                synchronized (XvideosConstant.class){
                    if(headers==null) {
                        headers = new HashMap<>();
                        headers.put("Accept-Language","zh-CN,zh;q=0.9");
                        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
                        headers.put("Referer", "https://www.xvideos.com/");
                        headers.put("Host", "www.xvideos.com");
                        headers.put("x-forwarded-for", IPUtils2.getRandomIp());
                        headers.put("Sec-Fetch-Site","same-origin");
                    }
                }
            }
            return headers;
        }
    }
    public static class C91VideoConstant{
        public static  final String C91_SCHEMAL = "https";
        public static  final String C91_HOST = "www.91porny.com";
        public static  final String C91_HOST_URL = "https://www.91porny.com";
        public static  final String C91_TYPE_NORMAL = "/video/category/latest";
        public static  final String C91_TYPE_HOT = "/video/category/hot-list";
        public static  final String C91_TYPE_MONTH_DISSCUSS = "/video/category/month-discuss";
        //关键词搜索相关
        public static  volatile  HashMap  headers;
        public static HashMap getHeaders(){
            if(headers==null){
                synchronized (XvideosConstant.class){
                    if(headers==null) {
                        headers = new HashMap<>();
                        headers.put("Accept-Language","zh-CN,zh;q=0.9");
                        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
                        headers.put("Referer", "https://91porny.com/");
                        headers.put("origin", "https://91porny.com/");
                        headers.put("x-forwarded-for", IPUtils2.getRandomIp());
                        headers.put("Sec-Fetch-Site","same-origin");
                    }
                }
            }
            return headers;
        }
    }
}
