package com.sjtfreaks.homevr.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sjtfreaks.homevr.R;
import com.sjtfreaks.homevr.activity.WebActivity;
import com.sjtfreaks.homevr.adapter.HomeAdapter;
import com.sjtfreaks.homevr.bean.HomeData;
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
public class HomeFragment extends Fragment {

    private GridView mGridView;
    private List<HomeData> mList = new ArrayList<>();
    private HomeAdapter mAdapter;
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    public static HomeFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mGridView =(GridView) view.findViewById(R.id.mGridView);
        mList.clear();
        String abc = getJson("home.json", getActivity());
        parsingJson(abc);
        //Toast.makeText(getActivity(),abc, Toast.LENGTH_SHORT).show();
        L.i("json:" + abc);
        //点击
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:" + position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                //2 way chuan zhi BUNdle
                intent.putExtra("title", mListTitle.get(position));
                intent.putExtra("url", mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String abc) {
        try {
            JSONObject jsonObject = new JSONObject(abc);
            JSONArray jsonArray = jsonObject.getJSONArray("dates");
            for(int i=0;i < jsonArray.length();i++){
                JSONObject json = (JSONObject) jsonArray.get(i);

                HomeData data = new HomeData();
                String url = json.getString("url");
                String title = json.getString("title");

                data.setImages(json.getString("images"));
                data.setTitle(title);
                data.setZong(json.getString("zong"));
                data.setPrice(json.getString("price"));
                data.setDesc(json.getString("desc"));

                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
            }
            //适配器
            mAdapter = new HomeAdapter(getActivity(),mList);
            mGridView.setAdapter(mAdapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
