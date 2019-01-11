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
import com.sjtfreaks.homevr.bean.Hotel;
import com.sjtfreaks.homevr.utils.PicassoUtils;

import java.util.List;


public class HotelAdapter extends BaseAdapter {
    private Context mContext;
    private List<Hotel> mList;
    private Hotel data;
    private LayoutInflater inflater;
    private WindowManager windowManager;
    private int width;
    public HotelAdapter(Context mContext, List<Hotel> mList){
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
            view = inflater.inflate(R.layout.item_hotel,null);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            viewHolder.tv_phone = view.findViewById(R.id.tv_phone);
            viewHolder.tv_price = view.findViewById(R.id.tv_price);
            viewHolder.im_hotel = (ImageView)view.findViewById(R.id.im_hotel);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mList.get(i);

        String im_hotel = data.getImages();
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_time.setText(data.getTime());
        viewHolder.tv_phone.setText(data.getPhone());
        viewHolder.tv_price.setText(data.getPrice());

        //指定大小
        PicassoUtils.loadImageViewSize(mContext,im_hotel,viewHolder.im_hotel,width,500);
        return view;
    }

    class ViewHolder{
        private TextView tv_title;//标题
        private TextView tv_time;//评级
        private TextView tv_phone;//类型
        private TextView tv_price;//一句话简介
        private ImageView im_hotel;//图片
    }
}
