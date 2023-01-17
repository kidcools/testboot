package com.kidcools.testboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kidcools.testboot.Constant;
import com.kidcools.testboot.dto.TestDto;
import com.kidcools.testboot.entity.*;
import com.kidcools.testboot.service.IXvideoService;
import com.kidcools.testboot.utils.XvideosUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

@Slf4j
@Service
public class XvideoServiceImpl implements IXvideoService {

    @Override
    public List<VideoItem> getMainPageVideoInfos(String url) {
        List<VideoItem> items = new ArrayList<>();
        try {
            long start0 = System.currentTimeMillis();
            Document doc = Jsoup.connect(url).headers(Constant.XvideosConstant.getHeaders()).get();
            long start = System.currentTimeMillis();
            log.info("http duration:{}", start - start0);
            String className = "mozaique cust-nb-cols";
            if (CollectionUtil.isNotEmpty(doc.getElementsByClass(className))) {
                Elements eles = doc.getElementsByClass(className).get(0).getElementsByTag("div");
                for (Element ele : eles) {
                    if (StrUtil.isNotEmpty(ele.attr("id")) && ele.attr("id").startsWith("video_")) {
                        if (StrUtil.isNotEmpty(ele.attr("data-id"))) {//发现目标开始解析
                            String imageUrl = ele.getElementsByTag("img").get(0).attr("data-src").replace("thumbs169ll/", "thumbs169lll/");
                            imageUrl = imageUrl.replace("THUMBNUM", "16");
                            boolean ischannel = null != ele.attr("data-is-channel") && ele.attr("data-is-channel").equals("1");
                            Long videoId = Long.parseLong(ele.attr("data-id"));
                            String posterVideoUrl = getPosterVideoUrl(imageUrl);
                            String detailUrl = Constant.XvideosConstant.XVIDEOS_HOST_URL + ele.getElementsByTag("a").get(0).attr("href").replaceAll("THUMBNUM","");
                            String title = ele.getElementsByClass("thumb-under").get(0).getElementsByTag("a").get(0).attr("title");
                            String duration = ele.getElementsByClass("thumb-under").get(0).getElementsByTag("a").get(0).getElementsByTag("span").get(0).text();
                            Elements qulityspan = ele.getElementsByTag("a").get(0).getElementsByTag("span");
                            String quality = "";
                            String upper = "";
                            String views = "";
                            String upperUrl = "";
                            if (qulityspan.size() > 0) {
                                quality = qulityspan.get(0).text();
                            }
                            if (ele.getElementsByClass("name").size() > 0) {
                                upper = ele.getElementsByClass("name").get(0).text();
                                upperUrl = ele.getElementsByClass("bg").get(0).getElementsByTag("a").attr("href");
                            }
                            items.add(new VideoItem(0,imageUrl, posterVideoUrl, "", detailUrl, title, duration, views, upper, upperUrl, quality, ischannel));

                        }
                    } else {
                        continue;
                    }
                }
            } else {
                return items;
            }
            long diff = System.currentTimeMillis() - start;
            log.info("diff:{}", diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<ChannelListItem> getChannelList(String url) {
        List<ChannelListItem> items = new ArrayList<>();
        try {
            long start0 = System.currentTimeMillis();
            Document doc = Jsoup.connect(url).headers(Constant.XvideosConstant.getHeaders()).get();
            long start = System.currentTimeMillis();
            log.info("http duration:{}", start - start0);
            String className = "thumb-block thumb-block-profile  title-in-thumb";
            String channelInfojs  = doc.getElementsByTag("head").get(0).getElementsByTag("script").get(0).html();
            channelInfojs = channelInfojs.substring(channelInfojs.indexOf("{\""),channelInfojs.lastIndexOf(";"));
            JSONObject jsonObject = JSONUtil.parseObj(channelInfojs).getJSONObject("dyn").getJSONObject("subs_users");
            if (CollectionUtil.isNotEmpty(doc.getElementsByClass(className))) {
                Elements eles = doc.getElementsByClass(className);
                System.out.println(eles.size());
                for (Element ele : eles) {
                    Element left = ele.getElementsByClass("thumb").get(0);
                    String html = left.getElementsByTag("script").get(0).html();
                    html = html.replace("document.write(xv.thumbs.replaceThumbUrl('", "");
                    html = html.substring(0, html.length() - 4);
                    String imageUrl = Jsoup.parse(html).getElementsByTag("img").attr("src").replace("thumbs169ll/", "thumbs169lll/");
                    ;
                    imageUrl = imageUrl.replace("THUMBNUM", "16");
                    String posterVideoUrl = getPosterVideoUrl(imageUrl);
                    String title = left.getElementsByClass("profile-name").get(0).text();
                    String channelDetailUrl = left.getElementsByTag("a").get(0).attr("href");
                    String videoNum = ele.getElementsByClass("thumb-under").get(0).getElementsByClass("with-sub").get(0).text();
                    if(channelDetailUrl.endsWith("/straight")){
                        channelDetailUrl =  channelDetailUrl.replace("/straight","");
                    }
                    String endfix = channelDetailUrl.substring(channelDetailUrl.lastIndexOf("/")+1,channelDetailUrl.length());
                    String channelId = jsonObject.getJSONObject(endfix).getStr("id");
                    String subNum = jsonObject.getJSONObject(endfix).getStr("subs_s");
                    items.add(new ChannelListItem(channelId,title, imageUrl, posterVideoUrl, channelDetailUrl, "", subNum, videoNum));
                }
            } else {
                return items;
            }
            long diff = System.currentTimeMillis() - start;
            log.info("diff:{}", diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * 获得pornstar list
     * @param url
     * @return
     */
    @Override
    public List<ChannelListItem> getPornStarList(String url) {
        List<ChannelListItem> items = new ArrayList<>();
        try {
            long start0 = System.currentTimeMillis();
            Document doc = Jsoup.connect(url).headers(Constant.XvideosConstant.getHeaders()).get();
            long start = System.currentTimeMillis();
            log.info("http duration:{}", start - start0);
            String className = "thumb-block thumb-block-profile  tb_full_init";
            String channelInfojs  = doc.getElementsByTag("head").get(0).getElementsByTag("script").get(0).html();
            channelInfojs = channelInfojs.substring(channelInfojs.indexOf("{\""),channelInfojs.lastIndexOf(";"));
            JSONObject jsonObject  = JSONUtil.parseObj(channelInfojs).getJSONObject("dyn").getJSONObject("subs_users");
            if (CollectionUtil.isNotEmpty(doc.getElementsByClass(className))) {
                Elements eles = doc.getElementsByClass(className);
                System.out.println(eles.size());
                for (Element ele : eles) {
                    Element left = ele.getElementsByClass("thumb").get(0);
                    String html = left.getElementsByTag("script").get(0).html();
                    html = html.replace("document.write(xv.thumbs.replaceThumbUrl('", "");
                    html = html.substring(0, html.length() - 4);
                    String imageUrl = Jsoup.parse(html).getElementsByTag("img").attr("src").replace("thumbs169ll/", "thumbs169lll/");
                    ;
                    imageUrl = imageUrl.replace("THUMBNUM", "16");
                    String posterVideoUrl = getPosterVideoUrl(imageUrl);
                    String title = left.getElementsByClass("profile-name").get(0).text();
                    String channelDetailUrl = left.getElementsByTag("a").get(0).attr("href");
                    String videoNum = ele.getElementsByClass("thumb-under").get(0).getElementsByClass("with-sub").get(0).text();
                    if(channelDetailUrl.endsWith("/straight")){
                        channelDetailUrl =  channelDetailUrl.replace("/straight","");
                    }
                    String endfix = channelDetailUrl.substring(channelDetailUrl.lastIndexOf("/")+1,channelDetailUrl.length());
                    String channelId = jsonObject.getJSONObject(endfix).getStr("id");
                    String subNum = jsonObject.getJSONObject(endfix).getStr("subs_s");
                    items.add(new ChannelListItem(channelId,title, imageUrl, posterVideoUrl, channelDetailUrl, "", subNum, videoNum));
                }
            } else {
                return items;
            }
            long diff = System.currentTimeMillis() - start;
            log.info("diff:{}", diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<VideoItem> getChannelDetailVideoList(String url) {
        ChannelVideoList channelVideoList = null;
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(10000).ignoreContentType(true).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //s = UnicodeUtil.toString(s);
        System.out.println(response.body());
        channelVideoList = JSONUtil.toBean(response.body(), ChannelVideoList.class);
        List<VideoItem> collect = new ArrayList();
        if (null != channelVideoList && CollectionUtil.isNotEmpty(channelVideoList.getVideos())) {
            collect = channelVideoList.getVideos().stream().map((item) -> {
                VideoItem videoItem = new VideoItem();
                videoItem.setTitle(item.getTf());
                videoItem.setDuration(item.getD());
                videoItem.setViews(item.getN() + "");
                videoItem.setUpperUrl(item.getPu());
                videoItem.setUpper(item.getPn());
                videoItem.setDetailUrl(item.getU());
                String imgUrl = item.getI().replace("thumbs169/", "thumbs169lll/");
                videoItem.setImageUrl(imgUrl);
                String posterVideoUrl = getPosterVideoUrl(imgUrl);
                videoItem.setPosterVideoUrl(posterVideoUrl);
                videoItem.setPlatform(0);
                return videoItem;
            }).collect(Collectors.toList());
        }
        return collect;
    }

    /**
     * 获取频道短视频接口
     * @param url
     * @return
     */
    @Override
    public List<VideoItem> getChannelDetailShortVideoList(String url) {
        List<VideoItem> items = new ArrayList<VideoItem>();
        Connection.Response response = null;
        try {
            long start0 = System.currentTimeMillis();
            response = Jsoup.connect(url)
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                    .timeout(10000).ignoreContentType(true).execute();
            long start = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        try {
            XvideoShortVideoPageData pageData = JSONUtil.toBean(response.body(), XvideoShortVideoPageData.class);
            System.out.println(pageData.getVideos().size());
            items = pageData.getVideos().stream().map((item)->{
                VideoItem videoItem = new VideoItem();
                videoItem.setTitle(item.getTitle());
                videoItem.setDuration("");
                videoItem.setViews(item.getNbViews());
                videoItem.setUpperUrl(item.getProfileUrl());
                videoItem.setUpper(item.getDisplayName());
                videoItem.setDetailUrl(item.getUrl());
                videoItem.setImageUrl(item.getThumbUrlVertical());
                videoItem.setPosterVideoUrl(item.getHlsUrl());
                videoItem.setPlatform(0);
                return videoItem;
            }).collect(Collectors.toList());
            return items;
        }catch (Exception e){
            log.error("数据转换失败:data{}",response.body());
        }
        return  items;
    }

    @Override
    public List<VideoItem> getSearchVideoList(String url) {
        List<VideoItem> mainPageVideoInfos = getMainPageVideoInfos(url);
        return mainPageVideoInfos;
    }

    private String getPosterVideoUrl(String imageurl) {
        String posterVideoUrl = imageurl.replace("thumbs169lll", "videopreview").substring(0, imageurl.lastIndexOf("/"));
        if (posterVideoUrl.endsWith("-2")) {
            posterVideoUrl = posterVideoUrl.replace("-2", "_169-2.mp4");
        } else if (posterVideoUrl.endsWith("-1")) {
            posterVideoUrl = posterVideoUrl.replace("-1", "_169-1.mp4");
        } else {
            posterVideoUrl += "_169.mp4";
        }
        //System.out.println(posterVideoUrl);
        return posterVideoUrl;
    }

    public VideoDeatil getVideoDetail(String url) {
        VideoDeatil videoDeatil = new VideoDeatil();
        try {
            long start0 = System.currentTimeMillis();
            Document doc = Jsoup.connect(url).headers(Constant.XvideosConstant.getHeaders()).get();
            long start = System.currentTimeMillis();
//            List<Element> collect = doc.getElementsByTag("script").stream().filter(item -> {
//                //System.out.println(item.html());
//                return item.html().trim().startsWith("logged_user");
//            }).collect(Collectors.toList());
//            for (Element element : collect) {
//                System.out.println(element.html());
//            }
            String channelId = doc.getElementsByClass("user-subscribe").attr("data-user-id");
            //log.info("channelId"+channelId);
            videoDeatil.setUpperId(channelId);
            doc.getElementsByTag("script").stream().filter(item -> {
                return item.html().trim().startsWith("logged_user");
            }).findFirst().ifPresent(i -> {

                String[] split = ReUtil.findAll("html5player.setVideoTitle\\('(.+)'\\);\n" +
                        "\t    html5player.setSponsors", i.html(), 0, new ArrayList<String>()).get(0).split("\\'");
                if(split.length>0){
                    videoDeatil.setTitle(split[1]);
                }
                String videoUrl = "";
                String[] videosplit = ReUtil.findAll("html5player.setVideoHLS\\('(.+)'\\);\n" +
                        "\t    html5player.setThumbUrl", i.html(), 0, new ArrayList<String>()).get(0).split("\\'");
                if(videosplit.length>0){
                    videoUrl = videosplit[1];
                }
                log.info("捕获VideoPlayUrl{}",videoUrl);
                videoDeatil.setVideoPlayUrl(videoUrl);

            });
            doc.getElementsByTag("script").stream().filter(item->{
                return item.html().startsWith("var video_related=");
            }).findFirst().ifPresent(i->{
                String relativeStr = i.html();
                relativeStr = relativeStr.replaceFirst("var video_related=", "");
                relativeStr = relativeStr.substring(0, relativeStr.lastIndexOf(";w"));
                List<Videos> videos = JSONUtil.toList(relativeStr, Videos.class);
                List<VideoItem> reelativeVideos = videos.stream().map(item -> {
                    VideoItem videoItem = new VideoItem();
                    videoItem.setTitle(item.getTf());
                    videoItem.setDuration(item.getD());
                    videoItem.setViews(item.getN() + "");
                    videoItem.setUpperUrl(item.getPu());
                    videoItem.setUpper(item.getPn());
                    String imgUrl = item.getI().replace("thumbs169/", "thumbs169lll/");
                    videoItem.setImageUrl(imgUrl);
                    String posterVideoUrl = getPosterVideoUrl(imgUrl);
                    videoItem.setPosterVideoUrl(posterVideoUrl);
                    videoItem.setDetailUrl(item.getU());
                    videoItem.setQuality(getVideoQuality(item));
                    return videoItem;
                }).collect(Collectors.toList());
                videoDeatil.setRelativeVideos(reelativeVideos);
            });
            Elements tagEles = doc.getElementsByClass("video-metadata video-tags-list ordered-label-list cropped").get(0).getElementsByClass("is-keyword btn btn-default");
            List<XTag> tags = tagEles.stream().map(item -> {
                return new XTag(item.text(),item.attr("href"));
            }).collect(Collectors.toList());
            videoDeatil.setTags(tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoDeatil;
    }
    String getVideoQuality(Videos item){
        String quality = "";
        if(item.getHp()==1){
            quality = "1080P";
        }else if(item.getH()==1){
            quality = "720P";
        }else if(item.getHm() == 1){
            quality = "360P";
        }
        return quality;
    }
    public static void main(String[] args) {
        // XvideoServiceImpl xvideoService = new XvideoServiceImpl();
        //xvideoService.getMainPageVideoInfos("https://www.xvideos.com/best/2022-10/0").forEach((videoItem -> System.out.println(videoItem)));
        //VideoDeatil videoDetail = xvideoService.getVideoDetail("https://www.xvideos.com/video28735831/big_ass_latina_claudia_bavel");
        //System.out.println(videoDetail);
//        String channelPageUrl = XvideosUtils.getChannelPageUrl("直男", "全球", "热门", 0);
//        System.out.println(channelPageUrl);
//        List<ChannelListItem> channelList = xvideoService.getChannelList("https://www.xvideos.com/channels-index/10");
//        channelList.forEach(item->{System.out.println(item);});
        //String url = XvideosUtils.getChannelDetailVideoListUrl(0, "comments", "/channels/hentai-cosplay-1");
//        List<VideoItem> channelDetailShortVideoList = xvideoService.getChannelDetailShortVideoList("https://www.xvideos.com/quickies-api/profilevideos/horizontal/straight/C/11311304/0");
//        channelDetailShortVideoList.forEach(item->{System.out.println(item);});
//        List<ChannelListItem> channelList = xvideoService.getChannelList("https://www.xvideos.com/channels-index");
//        channelList.forEach(item->{
//            System.out.println(item);
//        });
//        BigDecimal total = new BigDecimal("0.00");
//        List<TestDto> items = new ArrayList<>();
//        items.add(new TestDto("milestar",new BigDecimal("10.008")));
//        items.add(new TestDto("milestar1",new BigDecimal("100.008")));
//        double sum = items.stream().flatMapToDouble(item -> {
//            return DoubleStream.of(item.getMoney().doubleValue());
//        }).sum();
//        System.out.println(new BigDecimal(sum).setScale(2, RoundingMode.DOWN));
        String  a = "1,2,3,4";
        List<String> strings = Arrays.asList(a.split(",").clone());
        List<Long> collect = strings.stream().map(item -> new Long(item)).collect(Collectors.toList());
        System.out.println(collect);
    }
}
