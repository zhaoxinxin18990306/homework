package com.example.androidshopping.Show;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Adapter.ShopCartAdapter;
import com.example.androidshopping.R;
import com.example.androidshopping.entity.ShopCartBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShopCartActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listview;
    private Button btn_clear;
    public static  TextView all_Price;
    ShopCartAdapter adapter;
    List<ShopCartBean> shopCartBeanList = new ArrayList<>();
    private TextView tv_em;

    int  price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        LitePal.getDatabase();
        initView();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        btn_clear.setOnClickListener(this);
        all_Price = (TextView) findViewById(R.id.all_Price);
        all_Price.setOnClickListener(this);
        shopCartBeanList = LitePal.findAll(ShopCartBean.class);

        tv_em = (TextView) findViewById(R.id.tv_em);
        tv_em.setOnClickListener(this);
        if (shopCartBeanList.size()==0){
            tv_em.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        }else {
            listview.setVisibility(View.VISIBLE);
            tv_em.setVisibility(View.GONE);
            adapter = new ShopCartAdapter(this, shopCartBeanList);
            listview.setAdapter(adapter);
        }

        all_Price.setText("￥"+LitePal.sum(ShopCartBean.class,"price",int.class)+"元");
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                all_Price.setText("￥"+LitePal.sum(ShopCartBean.class,"price",int.class)+"元");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                LitePal.deleteAll(ShopCartBean.class);
                    tv_em.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                Toast.makeText(this, "已清空所有购物车", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
