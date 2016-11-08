package news.lxs.com.news.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import news.lxs.com.news.R;
import news.lxs.com.news.util.Utils;
import news.lxs.com.news.util.YBasicNameValuePair;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Utils.start_Activity(SplashActivity.this, MainActivity.class, new YBasicNameValuePair[]{});
            }
        }, 1500);
    }
}
