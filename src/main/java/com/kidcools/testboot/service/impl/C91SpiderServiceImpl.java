package com.kidcools.testboot.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.kidcools.testboot.Constant;
import com.kidcools.testboot.entity.C91video;
import com.kidcools.testboot.entity.VideoDeatil;
import com.kidcools.testboot.entity.VideoItem;
import com.kidcools.testboot.mapper.C91videoMapper;
import com.kidcools.testboot.service.IC91SpiderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class C91SpiderServiceImpl implements IC91SpiderService {
    @Autowired
    private C91videoMapper c91videoMapper;
    private List<VideoItem> getPageVideoItems(Document doc){
        return doc.getElementsByClass("colVideoList").stream().map(item -> {
            VideoItem videoItem = new VideoItem();
            String title = item.getElementsByClass("video-elem").get(0).getElementsByClass("img").get(0).attr("title");
            if(StrUtil.isEmpty(title)){
                title =  item.getElementsByClass("text-truncate title text-sub-title mt-2").get(0).text();
            }
            videoItem.setTitle(title);
            String poster = item.getElementsByClass("video-elem").get(0).getElementsByClass("img").get(0).attr("style");
            poster = poster.substring(poster.indexOf("'") + 1, poster.lastIndexOf("'"));
            if(!poster.startsWith("https:")){
               poster =  poster.replace("//","https://");
            }
            videoItem.setImageUrl(poster.replace(".jpg",".webp"));
            Long videoId = Long.parseLong(poster.substring(poster.lastIndexOf("/") + 1, poster.lastIndexOf(".")));
            //videoItem.set(videoId);
            videoItem.setPosterVideoUrl("https://vthumb.killcovid2021.com/thumb/" + videoId + ".mp4");
            String durationStr = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(0).text();
            DateTime dateTime = new DateTime(durationStr, "mm:ss");
            Integer duration = ((dateTime.hour(true) * 60 + dateTime.minute()) * 60 + dateTime.second());
            videoItem.setDuration(durationStr);
            //videoItem.setDuration(duration.longValue());
            String upper = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(1).getElementsByClass("text-dark").text().replace("热度", "").trim();
            videoItem.setUpper(upper);
            videoItem.setUpperUrl(upper);
            String info = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(1).getElementsByClass("text-muted").get(1).text().trim();
            String[] split = info.split("\\|");
            String upperDateStr = split[0].trim();
            String viewStr = split[1].trim();
            videoItem.setViews(viewStr);
            String deatilUrl = item.getElementsByClass("video-elem").get(0).getElementsByTag("a").get(0).attr("href");
            videoItem.setDetailUrl(deatilUrl);
            videoItem.setPlatform(1);
            return videoItem;
        }).collect(Collectors.toList());
    }
    @Override
    public List<C91video> getPageC91Video(String url) {
        List<C91video> videos = new ArrayList<C91video>();
        try {
            Document doc = Jsoup.connect(url).headers(Constant.C91VideoConstant.getHeaders()).get();
             videos = doc.getElementsByClass("colVideoList").stream().map(item -> {
                C91video c91video = new C91video();
                String title = item.getElementsByClass("video-elem").get(0).getElementsByClass("img").get(0).attr("title");
                c91video.setTitle(title);
                String poster = item.getElementsByClass("video-elem").get(0).getElementsByClass("img").get(0).attr("style");
                poster = poster.substring(poster.indexOf("'") + 1, poster.lastIndexOf("'"));
                c91video.setPoster(poster);
                Long videoId = Long.parseLong(poster.substring(poster.lastIndexOf("/") + 1, poster.lastIndexOf(".")));
                c91video.setVideoId(videoId);
                c91video.setPreviewUrl("https://vthumb.killcovid2021.com/thumb/" + videoId + ".mp4");
                String durationStr = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(0).text();
                DateTime dateTime = new DateTime(durationStr, "mm:ss");
                Integer duration = ((dateTime.hour(true) * 60 + dateTime.minute()) * 60 + dateTime.second());
                c91video.setDurationStr(durationStr);
                c91video.setDuration(duration.longValue());
                String upper = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(1).getElementsByClass("text-dark").text().replace("热度", "").trim();
                c91video.setUpper(upper);
                String info = item.getElementsByClass("video-elem").get(0).getElementsByTag("small").get(1).getElementsByClass("text-muted").get(1).text().trim();
                String[] split = info.split("\\|");
                String upperDateStr = split[0].trim();
                String viewStr = split[1].trim();
                c91video.setUploadDate(LocalDate.now());
                c91video.setCreatedAt(LocalDateTime.now());
                c91video.setUpdatedAt(LocalDateTime.now());
                String desc = item.getElementsByClass("video-elem").get(0).getElementsByTag("a").get(0).attr("href");
                c91video.setDesc(desc);
                return c91video;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videos;
    }

    @Override
    public List<VideoItem> getMainPageVideoItems(String url) {
        List<VideoItem> videos = new ArrayList<VideoItem>();
        try {
            Document doc = Jsoup.connect(url).headers(Constant.C91VideoConstant.getHeaders()).get();
            videos =getPageVideoItems(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videos;
    }

    @Override
    public VideoDeatil getVideoDetail(String url) {
        VideoDeatil videoDeatil = new VideoDeatil();
        try {
            Document doc = Jsoup.connect(url).headers(Constant.C91VideoConstant.getHeaders()).get();
            videoDeatil.setRelativeVideos(getPageVideoItems(doc));
            videoDeatil.setViews(doc.selectXpath("//*[@id=\"videoShowTabAbout\"]/div/div[2]").text().trim());
            videoDeatil.setTags(new ArrayList<>());
            videoDeatil.setVideoPlayUrl(doc.selectXpath("//*[@id=\"video-play\"]").attr("data-src"));
            videoDeatil.setTitle(doc.selectXpath("//*[@id=\"videoShowPage\"]/div[1]/div/h4").text());
            System.out.println(videoDeatil);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoDeatil;
    }

    @Override
    public List<VideoItem> getChannelDetailVideoList(String url) {
       return  getMainPageVideoItems(url);
    }

    public static void main(String[] args) {
        C91SpiderServiceImpl c91SpiderService = new C91SpiderServiceImpl();
        List<VideoItem> videoItems = c91SpiderService.getMainPageVideoItems("https://91porny.com/search?keywords=%E9%AA%9A%E9%80%BC&page=1");
        videoItems.forEach(item->{
            System.out.println(item);
        });
        //c91SpiderService.getVideoDetail("https://91porny.com/video/view/37402d6efb9066ac2aeb");
    }
}
