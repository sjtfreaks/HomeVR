package com.sjtfreaks.homevr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.sjtfreaks.homevr.R;
import com.sjtfreaks.homevr.activity.WebActivity;
import com.sjtfreaks.homevr.adapter.HotelAdapter;
import com.sjtfreaks.homevr.bean.Hotel;
import com.sjtfreaks.homevr.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.sjtfreaks.homevr.fragment.HouseFragment.getJson;

/**
 * A simple {@link Fragment} subclass.
 */
public class DingFragment extends Fragment {
    private ListView mListView;
    private List<Hotel> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public static DingFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        DingFragment fragment = new DingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ding,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        mList.clear();
        String abc = getJson("hotel.json",getActivity());
        String url = "file:///android_asset/hotel";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t, Toast.LENGTH_SHORT).show();
                //parsingJson(t);
                L.i("json:"+t);
            }
        });
        parsingJson(abc);
        //Toast.makeText(getActivity(),abc, Toast.LENGTH_SHORT).show();
        L.i("json:"+abc);
        //点击
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:"+position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                //2 way chuan zhi BUNdle
                intent.putExtra("title",mListTitle.get(position));
                intent.putExtra("url",mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String abc) {
        try {
            JSONObject jsonObject = new JSONObject(abc);
            JSONArray jsonList = jsonObject.getJSONArray("dates");

            for (int i = 0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);

                Hotel data = new Hotel();

                String url = json.getString("url");
                String title = json.getString("title");

                data.setTitle(json.getString("title"));
                data.setImages(json.getString("images"));
                data.setTime(json.getString("time"));
                data.setPrice(json.getString("price"));
                data.setPhone(json.getString("phone"));

                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
            }
            HotelAdapter adapter = new HotelAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
