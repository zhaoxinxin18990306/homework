package com.example.androidshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidshopping.R;
import com.example.androidshopping.entity.HistoryBean;

import java.util.ArrayList;
import java.util.List;

public class ShophistoryAdapter extends BaseAdapter {

    private List<HistoryBean> objects = new ArrayList<HistoryBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ShophistoryAdapter(Context context,List<HistoryBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public HistoryBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shophistory, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((HistoryBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(HistoryBean object, ViewHolder holder,int position) {
        //TODO implement
        holder.tvItemDate.setText("消费日期："+object.getDate());
        holder.tvItemName.setText("店铺名称："+object.getShopname());
        holder.tvItemPrice.setText("价格￥："+object.getPrice()+"元");
        holder.tv_count.setText("第"+(position+1)+"单");
    }

    protected class ViewHolder {
        private TextView tvItemPrice;
        private TextView tvItemName;
        private TextView tvItemDate;
        private TextView tv_count;

        public ViewHolder(View view) {
            tvItemPrice = (TextView) view.findViewById(R.id.tv_item_price);
            tvItemName = (TextView) view.findViewById(R.id.tv_item_name);
            tvItemDate = (TextView) view.findViewById(R.id.tv_item_date);
            tv_count = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}

