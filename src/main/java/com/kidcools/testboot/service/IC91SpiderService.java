package com.kidcools.testboot.service;

import com.kidcools.testboot.entity.C91video;
import com.kidcools.testboot.entity.VideoDeatil;
import com.kidcools.testboot.entity.VideoItem;

import java.util.List;

public interface IC91SpiderService {
    List<C91video> getPageC91Video(String url);
    List<VideoItem> getMainPageVideoItems(String url);
    VideoDeatil getVideoDetail(String url) ;
    List<VideoItem> getChannelDetailVideoList(String url);
}
