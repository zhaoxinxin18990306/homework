package com.example.androidshopping.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Choose.ChooseActivity;
import com.example.androidshopping.R;
import com.example.androidshopping.Register.RegisterActivity;
import com.example.androidshopping.entity.RegsiterBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_ac;
    private EditText et_password;
    private Button btn_dl;
    private Button btn_zhuce;
    List<RegsiterBean> regsiterBeanList = new ArrayList<>();
    ProgressDialog progressDialog;
    private CheckBox cb_jizhu;
    private CheckBox cb_zidong;



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        initView();
        cdLuoJi();

    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        et_ac = (EditText) findViewById(R.id.et_ac);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_dl = (Button) findViewById(R.id.btn_dl);
        btn_zhuce = (Button) findViewById(R.id.btn_zhuce);

        btn_dl.setOnClickListener(this);
        btn_zhuce.setOnClickListener(this);

        regsiterBeanList = LitePal.findAll(RegsiterBean.class);
        cb_jizhu = (CheckBox) findViewById(R.id.cb_jizhu);
        cb_jizhu.setOnClickListener(this);
        cb_zidong = (CheckBox) findViewById(R.id.cb_zidong);
        cb_zidong.setOnClickListener(this);

        sharedPreferences=getSharedPreferences("is",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        cb_jizhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editor.putString("accout",et_ac.getText().toString());
                    editor.putString("password",et_password.getText().toString());
                    editor.putString("choose","已选择");
                    editor.apply();
                }else {
                    editor.putString("accout","");
                    editor.putString("password","");
                    editor.putString("choose","");
                    editor.apply();
                }
            }
        });

        cb_zidong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editor.putString("zidong","已自动登录");
                }else {
                    editor.putString("zidong","");
                }
            }
        });



    }

    private  void cdLuoJi(){
        if (sharedPreferences.getString("choose","").equals("已选择")){
            et_password.setText(sharedPreferences.getString("password",""));
            et_ac.setText(sharedPreferences.getString("accout",""));
            cb_jizhu.setChecked(true);
        }

        if (sharedPreferences.getString("zidong","").equals("已自动登录")){
            et_password.setText(sharedPreferences.getString("password",""));
            et_ac.setText(sharedPreferences.getString("accout",""));
            ziDong();
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                handler.sendEmptyMessageDelayed(2,1000);
            }
            if (msg.what==2){
                progressDialog.dismiss();
                submit();
            }
        }
    };


    private  void  ziDong(){
        handler.sendEmptyMessageDelayed(1,1000);
        progressDialog.setTitle("即将自动登录");
        progressDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dl:
                submit();
                break;
            case R.id.btn_zhuce:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String ac = et_ac.getText().toString().trim();
        if (TextUtils.isEmpty(ac)) {
            Toast.makeText(this, "帐号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (regsiterBeanList.size() == 0) {
            progressDialog.setTitle("请先注册");
            progressDialog.show();
        }
        for (int i = 0; i < regsiterBeanList.size(); i++) {
            if (regsiterBeanList.get(i).getAccount().equals(ac) && regsiterBeanList.get(i).getPassword().equals(password)) {
                Toast.makeText(this, "欢迎您！" + regsiterBeanList.get(i).getName(), Toast.LENGTH_SHORT).show();
                editor.putString("sex",regsiterBeanList.get(i).getSex());
                editor.putString("name",regsiterBeanList.get(i).getName());
                editor.putString("address",regsiterBeanList.get(i).getAdress());
                editor.putString("age",regsiterBeanList.get(i).getAge());
                editor.apply();
                startActivity(new Intent(LoginActivity.this, ChooseActivity.class));
            } else {
                progressDialog.setTitle("检验失败");
                progressDialog.show();
            }
        }

        editor.putString("accout",et_ac.getText().toString());
        editor.putString("password",et_password.getText().toString());
        editor.apply();
        // TODO validate success, do something


    }
}
