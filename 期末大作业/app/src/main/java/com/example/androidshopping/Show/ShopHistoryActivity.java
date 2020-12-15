package com.example.androidshopping.Show;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Adapter.ShophistoryAdapter;
import com.example.androidshopping.R;
import com.example.androidshopping.entity.HistoryBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShopHistoryActivity extends AppCompatActivity {

    private TextView tv_em;
    private ListView listview;
    ShophistoryAdapter adapter;
    List<HistoryBean> historyBeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_history);
        initView();
    }

    private void initView() {
        historyBeanList= LitePal.findAll(HistoryBean.class);
        tv_em = (TextView) findViewById(R.id.tv_em);
        listview = (ListView) findViewById(R.id.listview);
        if (historyBeanList.size()==0){
            tv_em.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        }else {
            tv_em.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            adapter=new ShophistoryAdapter(this,historyBeanList);
            listview.setAdapter(adapter);
        }

    }
}
