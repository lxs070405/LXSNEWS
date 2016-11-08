package news.lxs.com.news.changliang;

/**
 * Created by HF02 on 2016/11/7.
 */

public class ConstValues {

    /**
     * HANDLER 发送的失败的空消息
     */
    public static final int MSG_FAILED = -1;

    public static final int MSG_JSON_FORMAT_WRONG = -2;
    /**
     * Handler发送的成功消息
     */
    public static final int MSG_SUCESS = 1;

    /**
     * Handler发送的访问失败
     */
    public static final int MSG_ERROR = -3;

    /**
     * 网络不可用
     */
    public static final int MSG_NET_INAVIABLE = 0;

    public static String BASE_URL = "http://192.168.1.1:8801/";
    /**
     * 获取首页轮播新闻，共5条新闻
     */
    public static String GetHomePicNew = BASE_URL + "News/GetHomePicNews";
    /**
     * 获取以文字和图片为主要内容的新闻
     */
    public static String GetNewsList = BASE_URL + "News/GetNewsList?newsId=%s&loadType=%s&dataType=%s";
    /**
     * 获取图片轮播类型的新闻
     */
    public static String GetPicNewsList = BASE_URL + "News/GetPicNewsList?newsId=%s&loadType=%s";

    /**
     * 获取视频类型的新闻
     */
    public static String GetVideoNewsList = BASE_URL + "News/GetVideoNewsList?newsId=%s&loadType=%s";


    /**
     * 获取图片轮播新闻详情-从图片轮播列表进来
     */
    public static String GetPicNewsDetails = BASE_URL + "News/GetPicNewsDetails?newsId=%s";
 /**
     * 获取视频新闻详情-从视频列表页面进入
     */
    public static String GetVideoNewsDetails = BASE_URL + "News/GetVideoNewsDetails?newsId=%s";

    /**
     * 更新新闻点击的次数
     */
    public static String UpdateReadTimes = BASE_URL + "News/UpdateReadTimes?newsId=%s";


}
