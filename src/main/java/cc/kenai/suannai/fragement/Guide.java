package cc.kenai.suannai.fragement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import cc.kenai.suannai.PurActivity;
import cc.kenai.suannai.R;
import cc.kenai.suannai.pojo.UserInfo;


public class Guide extends FragmentActivity {
    UserInfo userInfo;
    TextView tv_userInfo;
    Button btn_buy, btn_addmoney;

    Activity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();


        Bundle bundle = context.getIntent().getExtras();
        userInfo = UserInfo.toUserInfo(bundle);

        tv_userInfo = (TextView) context.findViewById(R.id.userInfo);
        btn_buy = (Button) context.findViewById(R.id.pur);
        btn_addmoney = (Button) context.findViewById(R.id.addmoney);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(context, PurActivity.class);
                UserInfo.toIntent(it, userInfo);

                context.startActivity(it);
            }
        });
    }


    void findUser(String card) {
        tv_userInfo.setText("查询中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient hc = new DefaultHttpClient();
                hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
                hc.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);

                HttpGet post = new HttpGet("http://call.kenai.cc");


                try {
                    HttpResponse re = hc.execute(post);

                    String result = EntityUtils.toString(re.getEntity());

                    userInfo = UserInfo.fromJsonString(result);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_userInfo.setText(userInfo.toString());
                        }
                    });

                } catch (IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_userInfo.setText("网络错误");
                        }
                    });
                }
            }
        }).start();
    }

    StringBuilder builder=new StringBuilder();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode<=KeyEvent.KEYCODE_9&&keyCode>=KeyEvent.KEYCODE_0){
            builder.append(keyCode-KeyEvent.KEYCODE_0);
        }else if(keyCode==KeyEvent.KEYCODE_ENTER){
            findUser(builder.toString());
            builder.delete(0,builder.length());
        }
        return true;
    }
}
