package news.lxs.com.news.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

import news.lxs.com.news.changliang.ConstValues;

/**
 * Created by lxs on 16/4/18.
 * okhttp封装的访问网络的工具类
 */
public class YHttpManagerUtils{

    public static int POST = 1;
    public static int GET = 0;
    public static int UPLOAD = 2;

    private int httpMethod = 0;
    public int getHttpMethod(){
        return httpMethod;
    }

    private Call call;

    public Handler getmHanadler() {
        return mHanadler;
    }

    public void setmHanadler(Handler mHanadler) {
        this.mHanadler = mHanadler;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public boolean isEnableOffLineData() {
        return enableOffLineData;
    }

    public void setEnableOffLineData(boolean enableOffLineData) {
        this.enableOffLineData = enableOffLineData;
    }

    public boolean isAviable() {
        return isAviable;
    }

    public void setIsAviable(boolean isAviable) {
        this.isAviable = isAviable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Handler mHanadler;

    private boolean isAviable = true;

    private String url = "";

    private Context ctx;

    private String className = "";


    /**
     * 是否开启本地缓存
     */
    private boolean enableOffLineData = true;

    public YHttpManagerUtils(Context ctx,String url,Handler mHanadler,String className){
        this.url = url;
        this.mHanadler = mHanadler;
        this.className = className;
        this.isAviable = true;
        this.ctx = ctx;
    }

    /**
     * 开始请求访问,返回值非json字串
     */
    public void startTextRequest(){
        if (Utils.isNetworkAvailable(ctx)) {
            httpMethod = GET;
            Request request = new Request.Builder().url(String.format(url)).build();
            call = HttpNetUtils.enqueue(request, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    if (mHanadler != null){
                        mHanadler.sendEmptyMessage(ConstValues.MSG_FAILED);
                    }
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    if (isAviable) {
                        String json = response.body().string();
                        Log.e("lxs", "onResponse: 网络返回的数据"+json);
                        if (json.equals("error")){
                            mHanadler.sendEmptyMessage(ConstValues.MSG_ERROR);
                            return;
                        }
                        Message msg = mHanadler.obtainMessage();
                        msg.what = ConstValues.MSG_SUCESS;
                        msg.obj = json;
                        mHanadler.sendMessage(msg);
                    }
                }
            });
        } else {
            Toast.makeText(ctx, "网络不可用", Toast.LENGTH_SHORT).show();
            mHanadler.sendEmptyMessage(ConstValues.MSG_NET_INAVIABLE);
        }
    }


    /**
     * 开始请求访问
     */
    public void startRequest(){
        if (Utils.isNetworkAvailable(ctx)) {
            httpMethod = GET;
            Request request = new Request.Builder().url(String.format(url)).build();
            call = HttpNetUtils.enqueue(request, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    if (mHanadler != null){
                        mHanadler.sendEmptyMessage(ConstValues.MSG_FAILED);
                    }
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    if (isAviable) {
                        String json = response.body().string();
                        Log.e("lxs",url + " - > 网络返回的数据 : \n" + json);
                        if (json.equals("error")){
                            mHanadler.sendEmptyMessage(ConstValues.MSG_ERROR);
                            return;
                        }

                        if (Utils.isWrongJson(json)) {
                            mHanadler.sendEmptyMessage(ConstValues.MSG_JSON_FORMAT_WRONG);
                            return;
                        }


                        Message msg = mHanadler.obtainMessage();
                        msg.what = ConstValues.MSG_SUCESS;
                        msg.obj = json;
                        mHanadler.sendMessage(msg);
                    }
                }
            });
        } else {
            Toast.makeText(ctx, "网络不可用", Toast.LENGTH_SHORT).show();
            mHanadler.sendEmptyMessage(ConstValues.MSG_NET_INAVIABLE);
        }
    }

    /**
     * post提交数据
     * @param map
     */
    public void startPostRequest(HashMap<String,String> map){
        if (Utils.isNetworkAvailable(ctx)) {
            httpMethod = POST;
            try {
                Log.e("lxs", "postData .... ");
                HttpNetUtils.postData(url, map, new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.e("lxs", "onFailure : " + request.body().toString());
                        mHanadler.sendEmptyMessage(ConstValues.MSG_ERROR);
                    }
                    @Override
                    public void onResponse(Response response) throws IOException {

                        String result = response.body().string();
                        Log.e("TAG", "POST RESULT : = " + result);

                        if (result != null && result.equals("error")){
                            mHanadler.sendEmptyMessage(ConstValues.MSG_ERROR);
                            return;
                        }
                        Message msg = mHanadler.obtainMessage();
                        msg.what = ConstValues.MSG_SUCESS;
                        msg.obj = result;
                        mHanadler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("tag",e.toString());
            }
        } else {
            Toast.makeText(ctx, "网络不可用", Toast.LENGTH_SHORT).show();
            mHanadler.sendEmptyMessage(ConstValues.MSG_NET_INAVIABLE);
        }
    }




    public void cancle(){
        if (call != null ){
            if(!call.isCanceled()){
            call.cancel();
            call = null;}
        }
    }
}
