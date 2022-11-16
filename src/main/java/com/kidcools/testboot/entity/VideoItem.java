package com.kidcools.testboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoItem {
    private int platform; //0-xvideos 1-91porn
    private String imageUrl;
    private String posterVideoUrl;
    private String videoUrl;
    private String detailUrl;
    private String title;
    private String duration;
    private String views;
    private String upper;
    private String upperUrl;
    private String quality;
    private boolean isChannel;
}
