package com.kidcools.testboot.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2022-11-14 14:58:30
 *
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
@Data
public class XvideoShortVideoPageData {
    @JsonProperty("videos")
    private List<XvideoShortVideoItem> videos;
    private boolean result;
    private int code;


}