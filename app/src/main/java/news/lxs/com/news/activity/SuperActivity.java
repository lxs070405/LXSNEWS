package news.lxs.com.news.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import news.lxs.com.news.util.ActivityManager;
import news.lxs.com.news.view.EProgressDialog;

/**
 * Created by lxs on 16/5/21.
 */
public class SuperActivity extends AppCompatActivity {

    private EProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    /**
     * 显示进度对话框
     */
    public void showLoading(){
        dialog = new EProgressDialog(this);
        dialog.show();
    }

    /**
     * 消失进度对话框
     */
    public void dismissLoading(){
        if(dialog != null){
            dialog.dismiss();;
            dialog = null;
        }
    }
}