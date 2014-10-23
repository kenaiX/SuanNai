package cc.kenai.suannai.fragement;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cc.kenai.suannai.R;

/**
 * Created by yujunqing on 14-4-28.
 */
public class Caidan extends Fragment {

    Activity context;
    final OnCaidanChoosed onCaidanChoosed;

    @SuppressLint("ValidFragment")
    public Caidan(OnCaidanChoosed onCaidanChoosed) {
        this.onCaidanChoosed = onCaidanChoosed;
    }

    public interface OnCaidanChoosed {
        public void onChoosed(CaidanItem item);
    }

    public class CaidanFenlei {
        String Title;
        List<CaidanItem> list;
    }

    public class CaidanItem {
        public String name;
        public float money;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_caidan, null, false);
    }

    ViewPager pager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        pager = (ViewPager) context.findViewById(R.id.caidan_vp);
        pager.setAdapter(new TestAdapter(context, initCaidan(), new OnCaidanChoosed() {
            @Override
            public void onChoosed(CaidanItem item) {
                onCaidanChoosed.onChoosed(item);
            }
        }));


        TitlePageIndicator titleIndicator = (TitlePageIndicator) context.findViewById(R.id.caidan_titles);
        titleIndicator.setViewPager(pager);

        titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 数据格式化
     *
     * @return
     */
    List<CaidanFenlei> initCaidan() {
        List<CaidanFenlei> candan_list = new ArrayList<CaidanFenlei>();
        CaidanFenlei fenlei;

        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "酸奶";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 6;
                item.name = "原味酸奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "花蜜果酱酸奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "盆栽酸奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "鲜果酸奶";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);


        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "鲜奶";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 5;
                item.name = "小瓶鲜奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "大瓶鲜奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 7;
                item.name = "花蜜果酱小瓶鲜奶";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "花蜜果酱大瓶鲜奶";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 7;
                item.name = "鲜果小瓶鲜奶";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "鲜果大瓶鲜奶";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);

        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "双皮奶";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "原味双皮奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "红豆双皮奶";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);


        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "碎碎冰";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 4;
                item.name = "冰爽碎碎冰";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "大果粒酸奶碎碎冰";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);

        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "冰淇淋";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 7;
                item.name = "脆筒酸奶冰淇淋";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "水果酸奶冰淇淋杯";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);


        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "甜点";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 4;
                item.name = "蛋挞一个";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 7;
                item.name = "蛋挞两个";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "蛋挞三个";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 12;
                item.name = "酸奶姆斯蛋糕";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 12;
                item.name = "抹茶蛋糕杯";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "红丝绒蛋糕杯";
                list.add(item);
            }
            {
                final CaidanItem item = new CaidanItem();
                item.money = 8;
                item.name = "轻乳酪蛋糕";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);


        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "饮品";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 7;
                item.name = "椰奶";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 10;
                item.name = "椰奶西米露";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);


        fenlei = new CaidanFenlei();
        {
            fenlei.Title = "其它";
            List<CaidanItem> list = new ArrayList<CaidanItem>();
            {
                final CaidanItem item = new CaidanItem();
                item.money = 1;
                item.name = "押金";
                list.add(item);
            }

            {
                final CaidanItem item = new CaidanItem();
                item.money = 1;
                item.name = "补款";
                list.add(item);
            }
            fenlei.list = list;
        }
        candan_list.add(fenlei);
        return candan_list;
    }
}

/**
 * 页面呈现的逻辑主要放在这里
 */
class TestAdapter extends PagerAdapter {
    final Context context;
    final List<Caidan.CaidanFenlei> mListFenlei;
    final List<View> mListViews;
    final List<String> mListTitles;
    final Caidan.OnCaidanChoosed onCaidanChoosed;

    public TestAdapter(final Context context, final List<Caidan.CaidanFenlei> fenlei, final Caidan.OnCaidanChoosed onCaidanChoosed) {
        this.context = context;
        this.onCaidanChoosed = onCaidanChoosed;
        this.mListFenlei = new LinkedList<Caidan.CaidanFenlei>();
        this.mListViews = new LinkedList<View>();
        this.mListTitles = new LinkedList<String>();
        for (final Caidan.CaidanFenlei item : fenlei) {
            mListFenlei.add(item);
            ListView listView = new ListView(context);
            SimpleAdapter adapter = new SimpleAdapter(context, getData(item), R.layout.layout_caidan_list_item,
                    new String[]{"name", "money"},
                    new int[]{R.id.cadan_list_item_name, R.id.cadan_list_item_money});

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    onCaidanChoosed.onChoosed(item.list.get(i));
                }
            });

            mListViews.add(listView);
            mListTitles.add(item.Title);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));//删除页卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(mListViews.get(position), 0);//添加页卡
        return mListViews.get(position);
    }

    @Override
    public int getCount() {
        return mListViews.size();//返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;//官方提示这样写
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitles.get(position);
    }

    private List<Map<String, String>> getData(Caidan.CaidanFenlei fenlei) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (Caidan.CaidanItem item : fenlei.list) {
            final Map<String, String> map = new HashMap<String, String>();
            map.put("name", item.name);
            map.put("money", String.valueOf(item.money));
            list.add(map);
        }

        return list;
    }
}






