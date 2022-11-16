package com.kidcools.testboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDeatil implements Serializable {
    private String title;
    private List<XTag> tags;
    private String videoPlayUrl;
    private String views;
    private String upperId;
    private List<VideoItem> relativeVideos;
}
