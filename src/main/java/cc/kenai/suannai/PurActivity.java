package cc.kenai.suannai;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import cc.kenai.suannai.fragement.Caidan;
import cc.kenai.suannai.fragement.Dingdan;
import cc.kenai.suannai.pojo.UserInfo;

public class PurActivity extends FragmentActivity {
    UserInfo userInfo;

    Dingdan dingdan = new Dingdan(new Dingdan.OnDingdanChanged() {
        @Override
        public void onChanged() {
            changeTotal();
        }
    });
    TextView tx_totalMoney, tv_nowMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pur);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.caidan, new Caidan(new Caidan.OnCaidanChoosed() {
            @Override
            public void onChoosed(Caidan.CaidanItem item) {
                Dingdan.DingdanItem dingdanItem = new Dingdan.DingdanItem();
                dingdanItem.name = item.name;
                dingdanItem.oneMoney = item.money;
                dingdan.addDingdan(dingdanItem);

                changeTotal();

            }
        }));

        ft.replace(R.id.dingdan, dingdan);
        ft.commit();

        Button btn = (Button) findViewById(R.id.commit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tx_totalMoney = (TextView) findViewById(R.id.totalmoney);
        tv_nowMoney = (TextView) findViewById(R.id.nowmoney);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        userInfo = UserInfo.toUserInfo(bundle);

        tv_nowMoney.setText("余额：" + userInfo.getMoney() + "元");
    }

    AlertDialog dialog;
    boolean state = false;

    public final void commit() throws JSONException {
//提交订单
        final JSONObject commit = new JSONObject();
        commit.put("phone", userInfo.getPhone());

        JSONArray jsonArray = new JSONArray();

        Map<String, Dingdan.DingdanItem> dingdanItemMap = dingdan.getDingdan();
        Set<String> strings = dingdanItemMap.keySet();
        for (String s : strings) {
            Log.v("dingdan", dingdanItemMap.get(s).name);
            jsonArray.put(dingdanItemMap.get(s).toString());

        }
        commit.put("dingdan", jsonArray.toString());

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("订单提交");
        builder.setMessage("正在提交");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialog != null && dialog.isShowing() && state) {
                    dialog.dismiss();
                }
            }
        });
        state = false;
        dialog = builder.create();


        dialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.v("", commit.toString());

                HttpClient hc = new DefaultHttpClient();
                hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
                hc.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);

                HttpPost post = new HttpPost("http://suannai.kenai.cc/rest/suannai/xiaofei");

                try {
                    StringEntity entity = new StringEntity(commit.toString());
                    post.setEntity(entity);
                } catch (UnsupportedEncodingException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.setMessage("错误");
                                state = true;
                            }
                        }
                    });
                }


                try {
                    HttpResponse re = hc.execute(post);
                    final String result=EntityUtils.toString(re.getEntity());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.setMessage(result);
                                state = true;
                            }
                        }
                    });

                } catch (IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.setMessage("网络错误");
                                state = true;
                            }
                        }
                    });
                }
            }
        }).start();

    }

    void changeTotal() {
        float totalmone = 0;
        Map<String, Dingdan.DingdanItem> dingdans = dingdan.getDingdan();
        Set<String> keySet = dingdans.keySet();
        for (String key : keySet) {
            Dingdan.DingdanItem item1 = dingdans.get(key);
            totalmone += item1.num * item1.oneMoney;

        }
        tx_totalMoney.setText("共计：" + totalmone + "元");
    }


}

