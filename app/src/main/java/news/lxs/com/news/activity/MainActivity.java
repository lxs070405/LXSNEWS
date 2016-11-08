package news.lxs.com.news.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bannerlayout.widget.BannerLayout;
import com.bannerlayout.widget.BannerRound;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import news.lxs.com.news.R;
import news.lxs.com.news.bean.BannerPicBean;
import news.lxs.com.news.changliang.ConstValues;
import news.lxs.com.news.model.ImageModel;
import news.lxs.com.news.util.YHttpManagerUtils;
import news.lxs.com.news.view.MyGridView;

public class MainActivity extends SuperActivity {

    @InjectView(R.id.bannerLayout)
    BannerLayout bannerLayout;
    @InjectView(R.id.gridview)
    MyGridView gridview;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        ctx = this;
//        lodeData();
        initView();
    }

    private YHttpManagerUtils httpManagerUtils;

    private void lodeData() {
        httpManagerUtils = new YHttpManagerUtils(this, ConstValues.GetHomePicNew,
                mHandler, BannerPicBean.class.getName());
        httpManagerUtils.startRequest();
    }

    private Handler mHandler = new MyHandler(this);

    @OnClick(R.id.fuwu)
    public void onClick() {//服务平台跳转

    }

    class MyHandler extends Handler {

        WeakReference<AppCompatActivity> mFragmentReference;

        public MyHandler(AppCompatActivity activitiy) {
            mFragmentReference = new WeakReference<AppCompatActivity>(activitiy);
        }

        @Override
        public void handleMessage(Message msg) {

            dismissLoading();

            AppCompatActivity fragment = mFragmentReference.get();

            if (fragment != null) {
                switch (msg.what) {
                    case ConstValues.MSG_FAILED:
                        break;
                    case ConstValues.MSG_ERROR:
                        break;
                    case ConstValues.MSG_NET_INAVIABLE:
                        break;
                    case ConstValues.MSG_JSON_FORMAT_WRONG:
                        break;
                    case ConstValues.MSG_SUCESS:
                        String json = (String) msg.obj;
                        parsData(json);
                        break;
                }
            }
        }
    }

    List<BannerPicBean> listdata;

    private void parsData(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<BannerPicBean>>() {
        }.getType();
        listdata = gson.fromJson(json, listType);
    }


    private int[] icon = {R.drawable.index1, R.drawable.index2, R.drawable.index3,
            R.drawable.index4, R.drawable.index5, R.drawable.index6,
            R.drawable.index7, R.drawable.index8, R.drawable.index9};
    private String[] iconName = {"检查新闻", "图阅新闻", "视频新闻", "廉政新闻", "案件聚焦",
            "预防天地", "检务公开", "检察文学", "便民服务"};

    private void initView() {
        List<ImageModel> list = new LinkedList<>();
        if (listdata != null) {//真实数据
            for (BannerPicBean bean : listdata) {
                ImageModel model = new ImageModel(bean.getPicHref(), bean.getTitle(), bean.getDetailsHref());
                list.add(model);
            }
        } else {//测试数据
            list.add(new ImageModel("http://photocdn.sohu.com/20161107/Img472455138.jpeg", "民调显示希拉里仍占微小优势", "banner1"));
            list.add(new ImageModel("http://photocdn.sohu.com/20161107/Img472455139.jpeg", "大选前夕，重点攻摇摆州", "banner2"));
            list.add(new ImageModel("http://photocdn.sohu.com/20161107/Img472455140.jpeg", "4000万选民已提前投票", "banner3"));
            list.add(new ImageModel("http://fashion.chinadaily.com.cn/img/attachement/jpg/site1/20161106/a41f726b573a198945f60f.jpg", "跑跑卡丁车", "banner4"));
        }

        bannerLayout
                .initImageListResources(list) //自定义model类
//                .setImageLoaderManage(new ImageLoader()) //自己定义加载图片的方式
//                .setRoundContainerHeight(200)
//                .initImageArrayResources(mImage)
                .setTitleSetting(ContextCompat.getColor(this, R.color.colortitle), -1)
//                .addPromptBar(new PromptBarView(getBaseContext()))//initAdapter之前调用生效
//                .addOnBannerPageChangeListener(new BannerOnPage())
                .initAdapter()
                .initRound(true, true, true, null, BannerRound.BANNER_ROUND_POSITION.RIGHT, BannerRound.BANNER_TITLE_POSITION.LEFT)
                .setOnBannerClickListener(new BannerLayout.OnBannerClickListener() {
                    @Override
                    public void onBannerClick(int position, Object model) {
                        ImageModel imageModel = (ImageModel) model;
                        Toast.makeText(getApplicationContext(), imageModel.getTestText(), Toast.LENGTH_SHORT).show();
                    }
                })
                .start(true);


        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        gridview.setAdapter(new SimpleAdapter(ctx, getData(), R.layout.item, from, to));//获取数据
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                }
            }
        });
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
