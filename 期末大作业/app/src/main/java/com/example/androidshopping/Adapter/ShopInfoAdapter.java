package com.example.androidshopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidshopping.R;
import com.example.androidshopping.Show.ShopDetailActivity;
import com.example.androidshopping.entity.ShopCartBean;
import com.example.androidshopping.entity.ShopItemBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShopInfoAdapter extends RecyclerView.Adapter<ShopInfoAdapter.ViewHolder> {
    List<ShopItemBean> shopItemBeanList = new ArrayList<>();
    ShopCartBean shopItemBean;
    public  static  String detail;
    public  static  int  img;
    public  static  String  price;
    public  static  String  name;
    Context context;

    public ShopInfoAdapter(Context context,List<ShopItemBean> shopItemBeanList) {
        this.shopItemBeanList = shopItemBeanList;
        this.context=context;
    }

    @NonNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopitem, parent, false);
        LitePal.getDatabase();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_img;
        private TextView item_shopname;
        private Button item_btn_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img=itemView.findViewById(R.id.item_img);
            item_shopname=itemView.findViewById(R.id.item_shopname);
            item_btn_add=itemView.findViewById(R.id.item_btn_add);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShopItemBean shopItemBean=shopItemBeanList.get(position);
        holder.item_img.setImageResource(shopItemBean.getImg());
        holder.item_shopname.setText(shopItemBean.getName());
        holder.item_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail=shopItemBean.getShopDetail();
                img=shopItemBean.getImg();
                price=shopItemBean.getPrice();
                name=shopItemBean.getName();
                context.startActivity(new Intent(context, ShopDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopItemBeanList.size();
    }


}
