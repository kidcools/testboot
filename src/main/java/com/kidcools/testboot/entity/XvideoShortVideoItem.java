package com.kidcools.testboot.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Auto-generated: 2022-11-14 14:58:30
 *
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
@Data
public class XvideoShortVideoItem {

    private int id;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("profile_name")
    private String profileName;
    @JsonProperty("has_banner")
    private boolean hasBanner;
    private String ratio;
    private String url;
    private String title;
    @JsonProperty("comments_enabled")
    private boolean commentsEnabled;
    @JsonProperty("comments_count")
    private int commentsCount;
    @JsonProperty("nb_views")
    private String nbViews;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("profile_pic")
    private String profilePic;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("thumb_url_vertical")
    private String thumbUrlVertical;
    @JsonProperty("thumb_url_first")
    private String thumbUrlFirst;
    @JsonProperty("thumb_url_vertical_first")
    private String thumbUrlVerticalFirst;
    @JsonProperty("mp4_url")
    private String mp4Url;
    @JsonProperty("mp4_url_idcdn")
    private int mp4UrlIdcdn;
    @JsonProperty("hls_url")
    private String hlsUrl;
    @JsonProperty("hls_url_idcdn")
    private int hlsUrlIdcdn;
    @JsonProperty("like_good_url")
    private String likeGoodUrl;
    @JsonProperty("like_bad_url")
    private String likeBadUrl;
    @JsonProperty("like_remove_url")
    private String likeRemoveUrl;


}