package com.example.androidshopping.Choose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.R;
import com.example.androidshopping.Show.ShowMainActivity;

public class ChooseActivity extends AppCompatActivity {

    private LinearLayout linershop;
    private TextView shop_bianhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initView();
    }


    private void initView() {
        linershop = (LinearLayout) findViewById(R.id.linershop);
        shop_bianhao = (TextView) findViewById(R.id.shop_bianhao);
        linershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChooseActivity.this, "你点击了"+shop_bianhao.getText().toString()+"的商店，即将跳转", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChooseActivity.this, ShowMainActivity.class));
            }
        });
    }
}
