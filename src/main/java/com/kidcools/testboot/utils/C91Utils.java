package com.kidcools.testboot.utils;

import cn.hutool.core.net.url.UrlBuilder;
import com.kidcools.testboot.Constant;

public class C91Utils {

    public static  String getPageUrl(String type, int pageNo){
        return "https://91porny.com/video/category/"+type+"/"+pageNo;
    }

    /**
     * 根据上传者获取upper的url
     * @param upperName
     * @param pageNo
     */
    public static String getUpperPageUrl(String upperName, Integer pageNo) {
        String pre = Constant.C91VideoConstant.C91_HOST_URL + "/author/" + upperName;
        return UrlBuilder.of(pre).addQuery("page",pageNo+1).toString();
    }

    public static String getSearchUrl(String keyWords, Integer pageNo) {
        String pre = Constant.C91VideoConstant.C91_HOST_URL + "/search";
        return UrlBuilder.of(pre).addQuery("keywords",keyWords).addQuery("page",pageNo).toString();
    }
}
