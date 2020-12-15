package com.example.androidshopping.Show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Login.LoginActivity;
import com.example.androidshopping.R;

public class ShopGerenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_touxiaang;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_age;
    private TextView tv_address;
    private Button btn_exit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_geren);
        initView();
    }

    private void initView() {
        sharedPreferences=getSharedPreferences("is",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        img_touxiaang = (ImageView) findViewById(R.id.img_touxiaang);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_address = (TextView) findViewById(R.id.tv_address);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        tv_name.setText(sharedPreferences.getString("name",""));
        tv_sex.setText(sharedPreferences.getString("sex",""));
        tv_age.setText(sharedPreferences.getString("age",""));
        tv_address.setText(sharedPreferences.getString("address",""));

        if (tv_sex.getText().toString().equals("男")){
            img_touxiaang.setImageResource(R.drawable.boy);
        }else {
            img_touxiaang.setImageResource(R.drawable.girl);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                startActivity(new Intent(ShopGerenActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
