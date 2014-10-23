package cc.kenai.suannai.pojo;


import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class UserInfo implements Serializable {
    public static void toIntent(Intent it, UserInfo userInfo) {
        it.putExtra("phone", userInfo.phone);
        it.putExtra("money", userInfo.money);
        it.putExtra("card", userInfo.card);
        it.putExtra("name", userInfo.name);
        it.putExtra("habit", userInfo.habit);
    }

    public static UserInfo toUserInfo(Bundle bundle) {
        UserInfo userInfo = new UserInfo();
        userInfo.phone = bundle.getString("phone");
        userInfo.money = bundle.getFloat("money");
        userInfo.card = bundle.getString("card");
        userInfo.name = bundle.getString("name");
        userInfo.habit = bundle.getString("habit");
        return userInfo;
    }

    Long _id;


    //卡号*
    String card;
    //暗号（姓名）*
    String name;

    //手机号*
    String phone;

    //喜好等信息
    String habit;
    //性别
    String sex;
    //常用地址
    String adress;

    //费用*
    float money;

    //消费记录*
    float record;


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }


    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getRecord() {
        return record;
    }

    public void setRecord(float record) {
        this.record = record;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("phone", phone);
            jsonObject.put("money", money);
            jsonObject.put("habit", habit);
            jsonObject.put("card", card);
            jsonObject.put("sex", sex);
            jsonObject.put("record", record);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static UserInfo fromJsonString(String s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            UserInfo userInfo = new UserInfo();
            userInfo.setPhone(jsonObject.getString("phone"));
            userInfo.setMoney((float) jsonObject.getDouble("money"));
            userInfo.setHabit(jsonObject.getString("habit"));
            userInfo.setCard(jsonObject.getString("card"));
            userInfo.setSex(jsonObject.getString("sex"));
            userInfo.setRecord((float) jsonObject.getDouble("record"));
            return userInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
