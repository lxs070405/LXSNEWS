package news.lxs.com.news.util;

/**
 * Created by lxs on 16/4/13.
 * 存储键值对打印log
 */

public class YBasicNameValuePair implements NameValuePair {

    private String name = "";
    private String value = "";
    public YBasicNameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }



}
