package com.kidcools.testboot.service;

import com.kidcools.testboot.entity.ChannelListItem;

import com.kidcools.testboot.entity.VideoDeatil;
import com.kidcools.testboot.entity.VideoItem;

import java.util.List;

public interface IXvideoService {
    /**
     * 获取默认推荐列表信息
     * @param url  带爬取的视频URl
     * @return
     */
    List<VideoItem>  getMainPageVideoInfos(String url);
    List<ChannelListItem> getChannelList(String url);
    List<VideoItem> getChannelDetailVideoList(String url);
    List<VideoItem> getChannelDetailShortVideoList(String url);
    List<VideoItem> getSearchVideoList(String url);
    VideoDeatil getVideoDetail(String url) ;
}
