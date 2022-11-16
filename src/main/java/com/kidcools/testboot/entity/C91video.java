package com.kidcools.testboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 91视频对象 c91video
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class C91video
{
    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long id;

    /** 视频标题 */
    private String title;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;

    /** 时长 */
    private Long duration;

    /** 时长字符串 */
    private String durationStr;

    /** 封面url */
    private String poster;

    /** 视频ID */
    private Long videoId;

    /** 预览视频url */
    private String previewUrl;

    /** 播放次数 */
    private Long views;

    /** 上传者 */
    private String upper;

    /** 视频描述 */
    private String desc;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

}
