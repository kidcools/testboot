package com.kidcools.testboot.utils;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.kidcools.testboot.Constant;
import org.apache.tomcat.util.buf.StringUtils;

public class XvideosUtils {
    /**
     * xvideos
     * 获取默认列表url
     * @param type
     * @param pageNo
     * @return
     */
    public static String  getPageUrl(String type,Integer pageNo){
        String url = Constant.XvideosConstant.XVIDEOS_HOST_URL;
        if(0==pageNo){//处理首页的url
            if(type.equals(Constant.XvideosConstant.XVIDEOS_TYPE_NORMAL)){
                return url;
            }else if(type.equals("gay")){
                return url+Constant.XvideosConstant.XVIDEOS_TYPE_GAY;
            }else if(type.equals("shemale")){
                return url+Constant.XvideosConstant.XVIDEOS_TYPE_SHEMALE;
            }
        }else{//处理后面的url
            if(type.equals("new")){
                return url+Constant.XvideosConstant.XVIDEOS_TYPE_NORMAL+"/"+pageNo;
            }else if(type.equals("gay")){
                return url+Constant.XvideosConstant.XVIDEOS_TYPE_GAY+"/"+pageNo;
            }else if(type.equals("shemale")){
                return url+Constant.XvideosConstant.XVIDEOS_TYPE_SHEMALE+"/"+pageNo;
            }
        }
        return url;
    }
    /**
     * xvideos
     * 获取默认列表url
     * @param type
     * @param pageNo
     * @return
     */
    public static String  getChannelPageUrl(String type,String country,String order,Integer pageNo){
        String url = Constant.XvideosConstant.XVIDEOS_HOST_URL;
        if("直男".equals(type)){
            url = url+"/channels-index";
        }else if("男同".equals(type)){
            url = url+"/channels-gay";
        }else if("双性".equals(type)){
            url = url+"/channels-trans";
        }else{

        }
        if("新建".equals(order)){
            url = url+"/new";
        }
        if("全球".equals(country)){
            //url = url+"/from/worldwide";
        }else if("中国".equals(country)){
            url = url+"/china";
        }else if("香港".equals(country)){
            url = url+"/hong_kong";
        }else if("亚洲".equals(country)){
            url = url+"/asia";
        }else{

        }
        if("热门".equals(order)){
            url = url+"/top";
        }else{

        }
        url+="/"+pageNo;
        return url;
    }

    /**
     * 搜索页面url https://www.xvideos.com/?k=%E5%A4%A7&sort=relevance&datef=today&typef=gay&quality=1080P
     * @param type  类型 new gay shemale
     * @param pageNo 页码
     * @param sortType 排序类型 relevance  uploaddate  rating length views random
     * @param datef 上传时间  all today week  month 3month 6month
     * @param durf 时长 allduration 1-3min 3-10min 10min_more 10-20min 20min_more
     * @param quality  视频质量 all hd 1080P
     * @return
     */
    public static String  getPageUrlByKeyword(String keyword,String type,Integer pageNo,String sortType,String datef,String durf,String quality){
        keyword = keyword.trim().replace(" ","+");
        UrlBuilder builder = UrlBuilder.create().setScheme(Constant.XvideosConstant.XVIDEOS_SCHEMAL).setHost(Constant.XvideosConstant.XVIDEOS_HOST).addQuery("k", keyword);
        if(StrUtil.isNotBlank(type)){
            builder.addQuery("typef",type);
        }
        if(StrUtil.isNotBlank(sortType)){
            builder.addQuery("sort",sortType);
        }
        if(StrUtil.isNotBlank(datef)){
            builder.addQuery("datef",datef);
        }
        if(StrUtil.isNotBlank(quality)){
            builder.addQuery("quality",quality);
        }
        if(StrUtil.isNotBlank(durf)){
            builder.addQuery("durf",durf);
        }
        if(ObjectUtil.isNotNull(pageNo)){
            builder.addQuery("p",pageNo);
        }
        return builder.build();
    }

    /**
     *
     * @param tagname
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param quality
     * @return
     */
    public static String  getPageUrlByTag(String tagname,Integer pageNo,String sortType,String datef,String durf,String quality){
        tagname = tagname.trim().replace(" ","+");
        String url = Constant.XvideosConstant.XVIDEOS_HOST_URL;
        url+="/tags";

        if(StrUtil.isNotBlank(sortType)){
            url+=sortType;
        }
        if(StrUtil.isNotBlank(datef)){

            url+=datef;
        }
        if(StrUtil.isNotBlank(quality)){
            url+=quality;
        }
        if(StrUtil.isNotBlank(durf)){
            url+=durf;
        }
        url+="/"+tagname;
        if(ObjectUtil.isNotNull(pageNo)){
            url+="/"+pageNo;
        }
        return url;
    }
    public static String  getBestPageUrl(String monthStr,Integer pageNo){
        StringBuilder url = new StringBuilder(Constant.XvideosConstant.XVIDEOS_HOST_URL);
        url.append("/best/").append(monthStr+"/").append(pageNo);
        return url.toString();
    }
    /**
     *
     * @param pageNo
     * @param sortType new rating best comment
     * @param channelUrl
     * @return
     */
    public static String  getChannelDetailVideoListUrl(Integer pageNo,String sortType,String channelUrl){
        String url = Constant.XvideosConstant.XVIDEOS_HOST_URL;
        url = url+channelUrl+"/videos/"+sortType+"/"+pageNo;
        return url;
    }
    /**
     * https://www.xvideos.com/lang/chinese/
     * https://www.xvideos.com/lang/chinese/1
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByLang(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }
    /**
     * https://www.xvideos.com/c/Milf-19
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByC(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }

    /**
     * https://www.xvideos.com/amateur-channels/desiremodel#_tabVideos
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByAmateurChannelQuery(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }

    /**
     * https://www.xvideos.com/channels/ferame#_tabVideos
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByChannelQuery(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }

    /**
     *
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByprofile(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }

    /**
     * https://www.xvideos.com/pornstar-channels/steve_rickz#_tabVideos
     * @param type
     * @param pageNo
     * @param sortType
     * @param datef
     * @param durf
     * @param qulity
     * @return
     */
    public static String  getPageUrlByPornStarChannel(String type,Integer pageNo,String sortType,String datef,String durf,String qulity){
        return "";
    }
    public static String getChannelDetailShortVideoListUrl(Integer pageNo,String channleId){
        return Constant.XvideosConstant.XVIDEOS_HOST_URL+"/quickies-api/profilevideos/horizontal/straight/C/"+channleId+"/"+pageNo;
    }
    public static void main(String[] args) {
        //   String pageUrlByKeyword = getPageUrlByKeyword("大", "new", 10, "random", "6month", "allduration", "all");
//        System.out.println(pageUrlByKeyword);
//        String pageUrlByKeyword = getPageUrlByTag("china", 0, "/s:uploaddate", "/m:today", "/d:allduration", "/q:all");
//        System.out.println(pageUrlByKeyword);
//        String aNew = getPageUrl("gay", 0);
//        System.out.println(aNew);
//        String channelPageUrl = getChannelPageUrl("双性", "亚洲", "新建", 0);
//        System.out.println(channelPageUrl);
        System.out.println(1583655136419386213L & 63);
    }
}
