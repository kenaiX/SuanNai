package cc.kenai.suannai.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.kenai.suannai.R;

/**
 * Created by yujunqing on 14-4-29.
 */
public class Dingdan extends ListFragment {
    public interface OnDingdanChanged {
        public void onChanged();
    }

    public Dingdan(OnDingdanChanged onDingdanChanged) {
        this.onDingdanChanged = onDingdanChanged;
    }

    Activity context;
    List<Map<String, String>> data;
    LinkedHashMap<String, DingdanItem> dingdanMap = new LinkedHashMap<String, DingdanItem>();

    SimpleAdapter adapter;
    OnDingdanChanged onDingdanChanged;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.layout_dingdan,null,false);
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = getActivity();
        data = new LinkedList<Map<String, String>>();
        adapter = new SimpleAdapter(context, data, R.layout.layout_dingdan_list_item,
                new String[]{"name", "money", "num"},
                new int[]{R.id.dingdan_list_item_name, R.id.dingdan_list_item_money, R.id.dingdan_list_item_num});

        setListAdapter(adapter);


    }

    public Map<String, DingdanItem> getDingdan() {
        return dingdanMap;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String name = data.get(position).get("name");
        DingdanItem item = dingdanMap.get(name);
        item.num--;
        if (item.num <= 0) {
            dingdanMap.remove(name);
        }
        updateDate();
        adapter.notifyDataSetChanged();
        onDingdanChanged.onChanged();
    }

    public void addDingdan(DingdanItem dingdan) {
        DingdanItem item = dingdanMap.get(dingdan.name);
        if (item != null) {
            item.num++;
        } else {
            dingdan.num = 1;


            final Map<String, String> map = new HashMap<String, String>();
            map.put("name", dingdan.name);
            map.put("num", String.valueOf(dingdan.num));
            map.put("money", String.valueOf(dingdan.oneMoney * dingdan.num));

            dingdanMap.put(dingdan.name, dingdan);
        }
        updateDate();
        adapter.notifyDataSetChanged();

    }

    private void updateDate() {
        data.clear();
        Set<String> set = dingdanMap.keySet();
        for (String name : set) {
            DingdanItem item = dingdanMap.get(name);
            final Map<String, String> map = new HashMap<String, String>();
            map.put("name", item.name);
            map.put("num", String.valueOf(item.num));
            map.put("money", String.valueOf(item.oneMoney * item.num));
            data.add(map);
        }
    }


    public static class DingdanItem {
        public String name;
        public int num;
        public float oneMoney;

        public DingdanItem() {
        }

        @Override
        public String toString() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name);
                jsonObject.put("num", num);
                jsonObject.put("oneMoney", oneMoney);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonObject.toString();
        }
    }

}
