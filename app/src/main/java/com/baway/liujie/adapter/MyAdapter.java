package com.baway.liujie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.liujie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/17
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<String> list = new ArrayList<>();
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        isSelected = new HashMap<Integer, Boolean>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.icon);
            holder.mText = (TextView) convertView.findViewById(R.id.textview);
            holder.cb= (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mIcon.setImageResource(R.mipmap.ic_launcher);
        holder.mText.setText(list.get(position));
        // 根据isSelected来设置checkbox的选中状况
//        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }

    public static class ViewHolder {
        ImageView mIcon;
        TextView mText;
        CheckBox cb;
    }
}
