package cc.kenai.suannai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import cc.kenai.suannai.pojo.UserInfo;

/**
 * Created by yujunqing on 14-4-30.
 */
public class MainActivity extends Activity {

    Activity context;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editText = new EditText(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handler.removeCallbacks(r);
                handler.postDelayed(r, 200);


            }
        });
        setContentView(editText);
        context = this;

        editText.requestFocus();
    }

    final Runnable r = new Runnable() {
        @Override
        public void run() {
            final String card = editText.getText().toString();
            try {

                if (card.contains("\n")) {
                    final String result = card.replaceAll("\n", "").trim();

                    if (result.length() >= 10) {
                        editText.setText("");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HttpClient hc = new DefaultHttpClient();
                                HttpGet get = new HttpGet("http://suannai.kenai.cc/rest/suannai/finduser?card=" + result);

                                try {
                                    HttpResponse re = hc.execute(get);
                                    String s = EntityUtils.toString(re.getEntity());
                                    Log.v("", s);
                                    final UserInfo userInfo;

                                    userInfo = UserInfo.fromJsonString(s);
                                    if (userInfo == null) {
                                        throw new IOException();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent it = new Intent(MainActivity.this, PurActivity.class);
                                            UserInfo.toIntent(it, userInfo);
                                            startActivity(it);
                                        }
                                    });


                                } catch (IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                            builder.setMessage("网略错误！");
                                            builder.create().show();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                }
            } catch (Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(":" + card + ":");
                        builder.create().show();
                    }
                });
            }
        }
    };
    final static Handler handler = new Handler();
}


