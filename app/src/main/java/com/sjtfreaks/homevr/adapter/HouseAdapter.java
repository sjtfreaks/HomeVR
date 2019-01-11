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
import com.sjtfreaks.homevr.bean.House;
import com.sjtfreaks.homevr.utils.PicassoUtils;

import java.util.List;

public class HouseAdapter extends BaseAdapter {
    private Context mContext;
    private List<House> mList;
    private House data;
    private LayoutInflater inflater;

    private WindowManager windowManager;
    private int width;

    public HouseAdapter(Context mContext, List<House> mList){
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
            view = inflater.inflate(R.layout.item_house,null);
            viewHolder.tv_title = view.findViewById(R.id.tv_title);
            viewHolder.tv_desc = view.findViewById(R.id.tv_desc);
            viewHolder.iv_vr = (ImageView)view.findViewById(R.id.iv_vr);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mList.get(i);
        String iv_vr = data.getImages();
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_desc.setText(data.getDesc());
        //指定大小
        PicassoUtils.loadImageViewSize(mContext,iv_vr,viewHolder.iv_vr,width,width);

        return view;
    }


    class ViewHolder{
        private TextView tv_title;//标题
        private TextView tv_desc;//作者
        private ImageView iv_vr;//图片
    }
}
