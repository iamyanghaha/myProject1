package com.ssm.myProject1.util;

/**
 * 常量类
 *
 */
public class Constants {
	
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";//返回消息类型：文本
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";//返回消息类型：音乐
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";//返回消息类型：图文
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";//请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";//请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_LINK = "link";//请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";//请求消息类型：地理位置
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";//请求消息类型：音频
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";//请求消息类型：推送
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";//事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";//事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_CLICK = "CLICK";//事件类型：CLICK(自定义菜单点击事件)
	public static final String ADD_WRONG_MESSAGE = "该分组已存在，请更改用户名";
	public static final String UPDATE_WRONG_MESSAGE="该分组名称已存在，无法更新";
	public static final int WRONG = -9999;
	
	public static final String WRONG_MESSAGE = "失败";
	public static final String[] IMG_TYPE={"jpg","png","jpeg","svg"};
	public static final String WECHAT_OPTION_STATE_NORMARL = "1";
	
}
