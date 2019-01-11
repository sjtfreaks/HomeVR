package com.sjtfreaks.homevr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjtfreaks.homevr.R;
import com.sjtfreaks.homevr.bean.HomeData;
import com.sjtfreaks.homevr.utils.PicassoUtils;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomeData> mList;
    private LayoutInflater inflater;
    private HomeData data;
    private WindowManager windowManager;
    private int width;
    public HomeAdapter (Context mContext,List<HomeData>mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //get系统服务
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        width = windowManager.getDefaultDisplay().getWidth();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_home,null);
            viewHolder.iv_home = (ImageView) view.findViewById(R.id.iv_home);
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            viewHolder.tv_price = view.findViewById(R.id.tv_price);
            viewHolder.tv_zong = view.findViewById(R.id.tv_zong);
            viewHolder.tv_desc = view.findViewById(R.id.tv_desc);
            //保存缓存
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        //设置数据
        data = mList.get(i);
        String iv_home = data.getImages();
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_price.setText(data.getPrice());
        viewHolder.tv_zong.setText(data.getZong());
        viewHolder.tv_desc.setText(data.getDesc());
        //封装
        PicassoUtils.loadImageViewSize(mContext,iv_home,viewHolder.iv_home,width/2,500);

        return view;
    }

    class ViewHolder{
        private TextView tv_title;
        private TextView tv_desc;
        private TextView tv_price;
        private TextView tv_zong;
        private ImageView iv_home;
    }
}
