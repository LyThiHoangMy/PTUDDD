package com.example.cvasport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cvasport.R;
import com.example.cvasport.model.LoaiSanPham;

import java.util.List;

public class LoaiSanPhamAdapter extends BaseAdapter {
    List<LoaiSanPham> listLSP;
    Context context;

    public LoaiSanPhamAdapter(Context context,List<LoaiSanPham> listLSP) {
        this.context = context;
        this.listLSP = listLSP;
    }

    @Override
    public int getCount() {
        return listLSP.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tensanpham;
        ImageView hinhanh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.tensanpham = convertView.findViewById(R.id.tvItemName);
            viewHolder.hinhanh = convertView.findViewById(R.id.ivItemImage);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tensanpham.setText(listLSP.get(position).getTenloaisp());
            Glide.with(context).load(listLSP.get(position).getHinhanh()).into(viewHolder.hinhanh);
        }
        return convertView;
    }
}
