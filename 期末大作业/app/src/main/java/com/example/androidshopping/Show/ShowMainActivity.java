package com.example.androidshopping.Show;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.androidshopping.Adapter.ShopInfoAdapter;
import com.example.androidshopping.entity.ShopItemBean;
import com.example.androidshopping.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ShowMainActivity extends AppCompatActivity {

    private LinearLayout line_content;
    private BottomNavigationView bottomNavigationView;
    ShopInfoAdapter shopInfoAdapter;
    List<ShopItemBean> itemBeanList = new ArrayList<>();
    private RecyclerView recycleView;

    public  static  String detail;
    public  static  int  img;
    public  static  String  price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_main);
        initView();
    }

    private void initView() {

        line_content = (LinearLayout) findViewById(R.id.line_content);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        initBottomNavigationView();
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycleView.setLayoutManager(staggeredGridLayoutManager);
        initAdapter();



    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_bottom_1:
                        startActivity(new Intent(ShowMainActivity.this, ShowMainActivity.class));
                        break;
                    case R.id.item_bottom_2:
                        startActivity(new Intent(ShowMainActivity.this, ShopCartActivity.class));
                        break;
                    case R.id.item_bottom_3:
                        startActivity(new Intent(ShowMainActivity.this, ShopHistoryActivity.class));
                        break;
                    case R.id.item_bottom_4:
                        startActivity(new Intent(ShowMainActivity.this, ShopGerenActivity.class));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initAdapter() {
        itemBeanList = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {

            if (i%2==0){
                itemBeanList.add(new ShopItemBean("商店ssssssssssssssssssssssssssssssssssssssssssss"+i,R.drawable.sd,"卖家承诺本商品100%为品牌正品，卖家已为该商品购买正品保险，假一赔十（具体保单以商品订单页展示的保险内容为准）","1000"));
            }else {
                itemBeanList.add(new ShopItemBean("商店ssssssssssssssssssssssssssssssssssssssssssss"+i,R.drawable.ls,"卖家已为该商品购买正品保险，假一赔十（具体保单以商品订单页展示的保险内容为准）","999"));
            }

        }

        shopInfoAdapter = new ShopInfoAdapter(this,itemBeanList);
        detail=ShopInfoAdapter.detail;
        img=ShopInfoAdapter.img;
        price=ShopInfoAdapter.price;
        recycleView.setAdapter(shopInfoAdapter);
    }
}
