package com.example.androidshopping.Show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Adapter.ShopCartAdapter;
import com.example.androidshopping.Adapter.ShopInfoAdapter;
import com.example.androidshopping.R;
import com.example.androidshopping.entity.ShopCartBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView shopdetail_img;
    private TextView shop_detail_price;
    private TextView shop_detail;
    private Button btn_addshopcart;
    private List<ShopCartBean> shopCartBeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detain);
        LitePal.getDatabase();
        initView();
    }

    private void initView() {

        shopdetail_img = (ImageView) findViewById(R.id.shopdetail_img);
        shop_detail_price = (TextView) findViewById(R.id.shop_detail_price);
        shop_detail = (TextView) findViewById(R.id.shop_detail);
        btn_addshopcart = (Button) findViewById(R.id.btn_addshopcart);
        btn_addshopcart.setOnClickListener(this);
        shopdetail_img.setImageResource(ShopInfoAdapter.img);
        shop_detail_price.setText(ShopInfoAdapter.price);
        shop_detail.setText(ShopInfoAdapter.detail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addshopcart:
                shopCartBeanList= LitePal.findAll(ShopCartBean.class);
                for (int i = 0; i < shopCartBeanList.size(); i++) {
                    if (shopCartBeanList.get(i).getName().equals(ShopInfoAdapter.name)){
                        Toast.makeText(this, "商品已存在，请勿重复添加", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                ShopCartBean shopCartBean=new ShopCartBean();
                shopCartBean.setPrice(shop_detail_price.getText().toString());
                shopCartBean.setImg(ShopInfoAdapter.img);
                shopCartBean.setName(ShopInfoAdapter.name);
                shopCartBean.save();
                Toast.makeText(this, "加入购物车成功,即将为您跳转", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ShopCartActivity.class));
                break;
        }
    }
}
