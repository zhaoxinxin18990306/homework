package com.example.androidshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidshopping.R;
import com.example.androidshopping.Show.ShopCartActivity;
import com.example.androidshopping.entity.HistoryBean;
import com.example.androidshopping.entity.ShopCartBean;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopCartAdapter extends BaseAdapter {

    private List<ShopCartBean> objects = new ArrayList<ShopCartBean>();
    public   static int   price=0;

    private Context context;
    HistoryBean historyBean;
    private LayoutInflater layoutInflater;

    public ShopCartAdapter(Context context,List<ShopCartBean> objects) {
        this.context = context;
        this.objects=objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ShopCartBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shopcartitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ShopCartBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(final ShopCartBean object, final ViewHolder holder, final int position) {
        //TODO implement
        holder.shopcartBianhao.setText("编号"+(position+1));


        holder.shopcartImg.setImageResource(object.getImg());
        holder.shopInfo.setText(object.getName());
        holder.shopPrice.setText("￥"+object.getPrice());
        holder.btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                objects.remove(position);
                LitePal.deleteAll(ShopCartBean.class,"name=?",object.getName());
                historyBean=new HistoryBean();
                historyBean.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                historyBean.setPrice(object.getPrice());
                historyBean.setShopname(object.getName());
                historyBean.save();
                notifyDataSetChanged();

                ShopCartActivity.all_Price.setText("￥"+LitePal.sum(ShopCartBean.class,"price",int.class)+"元");
            }
        });
    }

    protected class ViewHolder {
        private TextView shopcartBianhao;
        private ImageView shopcartImg;
        private TextView shopInfo;
        private TextView shopPrice;
        private Button btnZhifu;

        public ViewHolder(View view) {
            shopcartBianhao = (TextView) view.findViewById(R.id.shopcart_bianhao);
            shopcartImg = (ImageView) view.findViewById(R.id.shopcart_img);
            shopInfo = (TextView) view.findViewById(R.id.shop_info);
            shopPrice = (TextView) view.findViewById(R.id.shop_price);
            btnZhifu = (Button) view.findViewById(R.id.btn_zhifu);
        }
    }
}
