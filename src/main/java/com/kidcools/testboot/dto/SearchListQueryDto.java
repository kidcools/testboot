package com.kidcools.testboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchListQueryDto {
    /**
     * 从0开始
     */
    Integer pageNo;
    /*
     * new gay shemale
     */
    String typef;
    /**
     * relevance  uploaddate  rating length views random
     */
    String sortType;
    /**
     * all today week  month 3month 6month
     */
    String datef;
    /**
     * allduration 1-3min 3-10min 10min_more 10-20min 20min_more
     */
    String durf;
    /**
     * all 720P 1080P
     */
    String quality;

    String keyword;
    String tagName;
    String monthStr;//每月最佳
}
