package com.imooc.pojo.vo;

public class PublisherVideo {

	public UserVo publisher;
	public boolean userLikeVideo;
	public UserVo getPublisher() {
		return publisher;
	}
	public void setPublisher(UserVo publisher) {
		this.publisher = publisher;
	}
	public boolean isUserLikeVideo() {
		return userLikeVideo;
	}
	public void setUserLikeVideo(boolean userLikeVideo) {
		this.userLikeVideo = userLikeVideo;
	}
}