package com.kidcools.testboot.controller;
import cn.hutool.core.util.StrUtil;
import com.kidcools.testboot.Constant;
import com.kidcools.testboot.dto.*;
import com.kidcools.testboot.entity.*;
import com.kidcools.testboot.utils.ResponseHelper;
import com.kidcools.testboot.utils.ResponseVo;
import com.kidcools.testboot.utils.XvideosUtils;
import com.kidcools.testboot.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  com.kidcools.testboot.service.IXvideoService;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2022-10-05
 */
@Controller
@Slf4j
@RequestMapping("/xvideos")
public class XvideosSpiderController {

    @Autowired
    private IXvideoService xvideoService;


    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/list")
    @ResponseBody
    public ResponseVo list(@RequestBody FrontPageQueryDto dto) {
        log.info("url123:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getPageUrl(dto.getType(), dto.getPageNo());
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getMainPageVideoInfos(pageUrl);
            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
           pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/channle/list")
    @ResponseBody
    public ResponseVo channleList(@RequestBody ChannelPageQueryDto dto) {
        log.info("param:{}",dto);
        PageInfo<ChannelListItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getChannelPageUrl(dto.getType(),dto.getCountry(),dto.getOrder(), dto.getPageNo());
        if(StrUtil.isNotBlank(pageUrl)){
            List<ChannelListItem> listReslts = xvideoService.getChannelList(pageUrl);
            if(!(listReslts.size()%2==0)){
                ChannelListItem channelListItem = new ChannelListItem();
                channelListItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                channelListItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                channelListItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                listReslts.add(channelListItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }

    /**
     * 频道详情列表接口
     * @param dto
     * @return
     */
    @PostMapping("/channledetail/list")
    @ResponseBody
    public ResponseVo channleDetailList(@RequestBody ChannelDetailListQueryDto dto) {
        log.info("param:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getChannelDetailVideoListUrl(dto.getPageNo(),dto.getSortType(),dto.getChannelUrl());
        log.info("channelpageUrl"+pageUrl);
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getChannelDetailVideoList(pageUrl);

            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    @PostMapping("/channleShortdetail/list")
    @ResponseBody
    public ResponseVo channleShortdetail(@RequestBody ChannelDetailListQueryDto dto) {
        log.info("param:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getChannelDetailShortVideoListUrl(dto.getPageNo(),dto.getChannelId());
        log.info("channelpageUrl"+pageUrl);
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getChannelDetailShortVideoList(pageUrl);
            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    @PostMapping("/search/list")
    @ResponseBody
    public ResponseVo searchList(@RequestBody SearchListQueryDto dto) {
        log.info("param:{}",dto);
        dto = rebuildSearchDto(dto);
        log.info("param:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getPageUrlByKeyword(dto.getKeyword(),dto.getTypef(),dto.getPageNo(),dto.getSortType(),dto.getDatef(),dto.getDurf(),dto.getQuality());
        System.out.println(pageUrl);
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getMainPageVideoInfos(pageUrl);
            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    @PostMapping("/tagDetail/list")
    @ResponseBody
    public ResponseVo tagDetailList(@RequestBody SearchListQueryDto dto) {
        log.info("param:{}",dto);
        dto = rebuildSearchDtoForTagSearch(dto);
        log.info("param:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getPageUrlByTag(dto.getTagName(),dto.getPageNo(),dto.getSortType(),dto.getDatef(),dto.getDurf(),dto.getQuality());
        System.out.println(pageUrl);
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getMainPageVideoInfos(pageUrl);
            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    @PostMapping("/best/list")
    @ResponseBody
    public ResponseVo bestList(@RequestBody SearchListQueryDto dto) {
        log.info("param:{}",dto);
        PageInfo<VideoItem> pageresult = new PageInfo<>();
        String pageUrl = XvideosUtils.getBestPageUrl(dto.getMonthStr(),dto.getPageNo());
        System.out.println(pageUrl);
        if(StrUtil.isNotBlank(pageUrl)){
            List<VideoItem> listReslts = xvideoService.getMainPageVideoInfos(pageUrl);
            if(!(listReslts.size()%2==0)){
                VideoItem adItem = new VideoItem();
                adItem.setImageUrl("https://gw.alicdn.com/bao/uploaded/i4/2200779840212/O1CN01vCXFxH1DR8k58hPDr_!!0-item_pic.jpg_300x300q90.jpg_.webp");
                adItem.setPosterVideoUrl("https://tbm-auth.alicdn.com/8713a9bf85650d60/95e17de8b09ff9d7/20220726_154b0d04fd439e8d_370507254809_mp4_264_hd_taobao.mp4?auth_key=1665763645-0-0-d8edcc4c20a273dd74aff8ba27636114&t=213fc9eb16657609451058637ed3af&b=video_plus&p=cloudvideo_http_seller_sucai_vod_publish");
                adItem.setTitle("2022新款RGB20S开源游戏机复古GBA口袋妖怪便携PS1街机");
                adItem.setUpper("平台广告");
                adItem.setViews("1W次观看");
                listReslts.add(adItem);
            }
            pageresult.setPagedata(listReslts);
            pageresult.setPageSize(listReslts.size());
            pageresult.setCurPageNum(dto.getPageNo());
        }
        return ResponseHelper.success(pageresult);
    }
    @PostMapping("/video/detail")
    @ResponseBody
    public ResponseVo videoDetail(@RequestBody VideoDetailQueryDto dto) {
        log.info("param:{}",dto);
        //dto = rebuildSearchDto(dto);
        if(!dto.getUrl().startsWith("https://")){
            dto.setUrl(Constant.XvideosConstant.XVIDEOS_HOST_URL+dto.getUrl());
        }
        log.info("param:{}",dto);
        VideoDeatil videoDetail = xvideoService.getVideoDetail(dto.getUrl());
        return ResponseHelper.success(videoDetail);
    }
    private SearchListQueryDto rebuildSearchDto(SearchListQueryDto searchListQueryDto){
        if(searchListQueryDto.getTypef().equals("直男")){
            searchListQueryDto.setTypef("new");
        }else if(searchListQueryDto.getTypef().equals("男同")){
            searchListQueryDto.setTypef("gay");
        }else{
            searchListQueryDto.setTypef("shemale");
        }

        if(searchListQueryDto.getSortType().equals("关联")){
            searchListQueryDto.setSortType("relevance");
        }else if(searchListQueryDto.getSortType().equals("上传日期")){
            searchListQueryDto.setSortType("uploaddate");
        }else if(searchListQueryDto.getSortType().equals("评级")){
            searchListQueryDto.setSortType("rating");
        }else if(searchListQueryDto.getSortType().equals("长度")){
            searchListQueryDto.setSortType("length");
        }else if(searchListQueryDto.getSortType().equals("观看次数")){
            searchListQueryDto.setSortType("views");
        }else{
            searchListQueryDto.setSortType("random");
        }
        if(searchListQueryDto.getDatef().equals("任何时间")){
            searchListQueryDto.setDatef("all");
        }else if(searchListQueryDto.getDatef().equals("过去三天")){
            searchListQueryDto.setDatef("today");
        }else if(searchListQueryDto.getDatef().equals("本周")){
            searchListQueryDto.setDatef("week");
        }else if(searchListQueryDto.getDatef().equals("本月")){
            searchListQueryDto.setDatef("month");
        }else if(searchListQueryDto.getDatef().equals("过去三月")){
            searchListQueryDto.setDatef("3month");
        }else{
            searchListQueryDto.setDatef("6month");
        }
        if(searchListQueryDto.getDurf().equals("所有时长")){
            searchListQueryDto.setDurf("allduration");
        }else if(searchListQueryDto.getDurf().equals("1-3min")){
            searchListQueryDto.setDurf("1-3min");
        }else if(searchListQueryDto.getDurf().equals("3-10min")){
            searchListQueryDto.setDurf("3-10min");
        }else if(searchListQueryDto.getDurf().equals("10min+")){
            searchListQueryDto.setDurf("10min_more");
        }else if(searchListQueryDto.getDurf().equals("10-20min")){
            searchListQueryDto.setDurf("10-20min");
        }else{
            searchListQueryDto.setDurf("20min_more");
        }

        if(searchListQueryDto.getQuality().equals("all")){
            searchListQueryDto.setQuality("all");
        }else if(searchListQueryDto.getQuality().equals("720P")){
            searchListQueryDto.setQuality("720P");
        }else{
            searchListQueryDto.setQuality("1080P");
        }
        return searchListQueryDto;
    }
    private SearchListQueryDto rebuildSearchDtoForTagSearch(SearchListQueryDto searchListQueryDto){
        if(searchListQueryDto.getSortType().equals("关联")){
            searchListQueryDto.setSortType("/s:relevance");
        }else if(searchListQueryDto.getSortType().equals("上传日期")){
            searchListQueryDto.setSortType("/s:uploaddate");
        }else if(searchListQueryDto.getSortType().equals("评级")){
            searchListQueryDto.setSortType("/s:rating");
        }else if(searchListQueryDto.getSortType().equals("长度")){
            searchListQueryDto.setSortType("/s:length");
        }else if(searchListQueryDto.getSortType().equals("观看次数")){
            searchListQueryDto.setSortType("/s:views");
        }else{
            searchListQueryDto.setSortType("s:random");
        }
        if(searchListQueryDto.getDatef().equals("任何时间")){
            searchListQueryDto.setDatef("/m:all");
        }else if(searchListQueryDto.getDatef().equals("过去三天")){
            searchListQueryDto.setDatef("/m:today");
        }else if(searchListQueryDto.getDatef().equals("本周")){
            searchListQueryDto.setDatef("/m:week");
        }else if(searchListQueryDto.getDatef().equals("本月")){
            searchListQueryDto.setDatef("/m:month");
        }else if(searchListQueryDto.getDatef().equals("过去三月")){
            searchListQueryDto.setDatef("/m:3month");
        }else{
            searchListQueryDto.setDatef("/m:6month");
        }
        if(searchListQueryDto.getDurf().equals("所有时长")){
            searchListQueryDto.setDurf("/d:allduration");
        }else if(searchListQueryDto.getDurf().equals("1-3min")){
            searchListQueryDto.setDurf("/d:1-3min");
        }else if(searchListQueryDto.getDurf().equals("3-10min")){
            searchListQueryDto.setDurf("/d:3-10min");
        }else if(searchListQueryDto.getDurf().equals("10min+")){
            searchListQueryDto.setDurf("/d:10min_more");
        }else if(searchListQueryDto.getDurf().equals("10-20min")){
            searchListQueryDto.setDurf("/d:10-20min");
        }else{
            searchListQueryDto.setDurf("/d:20min_more");
        }

        if(searchListQueryDto.getQuality().equals("all")){
            searchListQueryDto.setQuality("/q:all");
        }else if(searchListQueryDto.getQuality().equals("720P")){
            searchListQueryDto.setQuality("/q:720P");
        }else{
            searchListQueryDto.setQuality("/q:1080P");
        }
        return searchListQueryDto;
    }
}
