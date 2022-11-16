package com.kidcools.testboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDetailListQueryDto {
    /**
     * 从0开始
     */
    Integer pageNo;
    /*
     * new rating best comments
     */
    String sortType;
    /**
     *
     */
    String channelUrl;
    String channelId;
}
