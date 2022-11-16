package com.kidcools.testboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontPageQueryDto {
    /**
     * 从0开始
     */
    Integer pageNo;
    /**
     * 检索类型 可选 new  gay shemale
     * 91视频  latest ori recent-favorite hot-list（当前最热）
     */
    String type;
}
