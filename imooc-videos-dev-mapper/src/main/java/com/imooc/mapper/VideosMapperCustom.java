package com.imooc.mapper;

import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosMapperCustom extends MyMapper<Videos> {
//    List<VideosVO> queryAllVideos();
    List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc, @Param("userId") String userId);

    public List<VideosVO> queryMyFollowVideos(String userId);
    public List<VideosVO> queryMyLikeVideos(@Param("userId") String userId);


    public void addVideoLikeCount(String videoId);
    public void reduceVideoLikeCount(String videoId);


}