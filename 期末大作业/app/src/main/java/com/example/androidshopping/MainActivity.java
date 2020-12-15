package com.example.androidshopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    int i=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                handler.sendEmptyMessageDelayed(2,1000);
            }
            if (msg.what==2){
                handler.sendEmptyMessageDelayed(3,1000);
            }
            if (msg.what==3){
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }
    };
    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
