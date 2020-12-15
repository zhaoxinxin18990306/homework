package com.example.androidshopping.Register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidshopping.Login.LoginActivity;
import com.example.androidshopping.R;
import com.example.androidshopping.entity.RegsiterBean;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_zhanghao;
    private EditText et_name;
    private EditText et_password;
    private EditText et_adress;
    private RadioButton rb_nan;
    private RadioButton rb_nv;
    private EditText et_age;
    private Button btn_zhuce;
    List<RegsiterBean>  regsiterBeanList=new ArrayList<>();
    private  String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        LitePal.getDatabase();
    }

    private void initView() {
        et_zhanghao = (EditText) findViewById(R.id.et_zhanghao);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        et_adress = (EditText) findViewById(R.id.et_adress);
        rb_nan = (RadioButton) findViewById(R.id.rb_nan);
        rb_nv = (RadioButton) findViewById(R.id.rb_nv);
        et_age = (EditText) findViewById(R.id.et_age);
        btn_zhuce = (Button) findViewById(R.id.btn_zhuce);

        btn_zhuce.setOnClickListener(this);




       rb_nan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b){
                   sex="男";
                   rb_nv.setChecked(false);
               }
           }
       });

        rb_nv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    sex="女";
                    rb_nan.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zhuce:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String zhanghao = et_zhanghao.getText().toString().trim();
        if (TextUtils.isEmpty(zhanghao)) {
            Toast.makeText(this, "帐号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String adress = et_adress.getText().toString().trim();
        if (TextUtils.isEmpty(adress)) {
            Toast.makeText(this, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String age = et_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "年龄不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        regsiterBeanList=LitePal.findAll(RegsiterBean.class);
        for (int i = 0; i <regsiterBeanList.size() ; i++) {
            if (regsiterBeanList.get(i).getAccount().equals(zhanghao)){
                Toast.makeText(this, "该账号已存在，请重新注册", Toast.LENGTH_SHORT).show();
                return;
            }
            if (regsiterBeanList.get(i).getName().equals(zhanghao)){
                Toast.makeText(this, "该姓名已存在，请重新注册", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length()<=3){
                Toast.makeText(this, "密码设置过短，请重新设置", Toast.LENGTH_SHORT).show();
                return;
            }

            if (age.length()<=1){
                Toast.makeText(this, "年龄太小，长大了再来吧", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        RegsiterBean regsiterBean=new RegsiterBean();
        regsiterBean.setAccount(zhanghao);
        regsiterBean.setAdress(adress);
        regsiterBean.setAge(age);
        regsiterBean.setSex(sex);
        regsiterBean.setPassword(password);
        regsiterBean.setName(name);
        regsiterBean.save();
        writeSDcard(zhanghao);
        readSDcard();
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));





        // TODO validate success, do something


    }

    private void writeSDcard(String str) {
                 try {
                         // 判断是否存在SD卡
                       if (Environment.getExternalStorageState().equals(
                                     Environment.MEDIA_MOUNTED)) {
                              // 获取SD卡的目录
                              File sdDire = Environment.getExternalStorageDirectory();
                              FileOutputStream outFileStream = new FileOutputStream(
                                           sdDire.getCanonicalPath() + "/test.txt");
                              outFileStream.write(str.getBytes());
                              outFileStream.close();
                              Toast.makeText(this, "数据保存到text.txt文件了", Toast.LENGTH_LONG)
                                      .show();
                          }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
         }

    // 从SD卡中读取数据
     private void readSDcard() {
                 StringBuffer strsBuffer = new StringBuffer();
                 try {
                         // 判断是否存在SD
                         if (Environment.getExternalStorageState().equals(
                                      Environment.MEDIA_MOUNTED)) {
                                File file = new File(Environment.getExternalStorageDirectory()
                                              .getCanonicalPath() + "/test.txt");
                                // 判断是否存在该文件
                                if (file.exists()) {
                                        // 打开文件输入流
                                        FileInputStream fileR = new FileInputStream(file);
                                        BufferedReader reads = new BufferedReader(
                                                      new InputStreamReader(fileR));
                                        String st = null;
                                        while ((st = reads.readLine()) != null) {
                                           strsBuffer.append(st);
                                       }
                                        fileR.close();
                                    } else {
                                      //  Toast.makeText(this, "该目录下文件不存在", Toast.LENGTH_LONG).show();
                                    }
                           }
                   } catch (Exception e) {
                      e.printStackTrace();
                   }
               Toast.makeText(this, "读取到的数据是：" + strsBuffer.toString() + "",
                               Toast.LENGTH_LONG).show();
            }
 }


