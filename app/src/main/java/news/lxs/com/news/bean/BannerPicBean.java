package news.lxs.com.news.bean;

/**
 * Created by lxs on 2016/11/7.
 */

public class BannerPicBean  {

    /**
     * [
     {
     "DetailsHref": "http://www.141.com/Cont/Detials.aspx?id=1",
     "PicHref": "http://www.127.com/UploadFiles/nh.jpg",
     "Title": "3334何亚坤"
     }
     ]
     */
    private String DetailsHref;
    private String PicHref;
    private  String  Title;

    public String getPicHref() {
        return PicHref;
    }

    public void setPicHref(String picHref) {
        PicHref = picHref;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetailsHref() {
        return DetailsHref;
    }

    public void setDetailsHref(String detailsHref) {
        DetailsHref = detailsHref;
    }
}
