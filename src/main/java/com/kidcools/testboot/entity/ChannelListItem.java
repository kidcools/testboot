package com.kidcools.testboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelListItem {
    private String channelId;
    private String title;
    private String imageUrl;
    private String posterVideoUrl;
    private String channelDetailUrl;
    private String desc;
    private String subnumber;
    private String videoNum;
}
