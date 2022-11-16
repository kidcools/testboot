package com.kidcools.testboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelPageQueryDto {
    /**
     * 从0开始
     */
    Integer pageNo;
    /*
     * "直男", "男同", "双性"
     */
    String type;
    /**
     * ["全球", "中国", "香港", "亚洲"];
     */
    String country;
    /**
     * ["热门", "新建"]
     */
    String order;
}
