package com.example.shoppingcart.bean;

import com.example.shoppingcart.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "纯肉零食大礼包", " 网红休闲零食", "港荣蒸蛋糕", "良品铺子", "进口休闲零食", "士力架"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "良品铺子 肉肉大满足纯肉零食大礼包 肉干肉脯牛肉干猪肉脯宵夜休闲零食送女友礼物1589g/1576g  【12款纯荤零食】肉肉大礼包1589g 1箱",
            "百草味 网红休闲零食特色小吃美食整箱蛋糕早餐手撕面包点心传统糕点 原味肉松饼1000g/箱",
            "港荣蒸蛋糕 奶香味900g/箱 饼干蛋糕 营养早餐食品 手撕面包口袋吐司 休闲零食小吃",
            "良品铺子 鸭肉大礼包490g 零食大礼包整箱装卤味鸭脖鸭舌 21包鸭肉小吃 辣条零食节日送女友零食 【纯鸭肉组合】鸭肉大礼包490g 1盒",
            "【感恩节七仓配送】进口休闲零食大礼包一整箱食品礼盒超市团购送女友儿童女生小孩子好吃的生日礼物\n",
            "士力架 花生夹心巧克力（全家桶）礼物送女友 休闲零食员工福利 糖果460g （新旧包装随机发放）"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {139, 28, 30, 47, 99, 26};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.jpg1, R.drawable.rsb, R.drawable.zdg,
            R.drawable.lt, R.drawable.ls, R.drawable.slj
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.jpg1, R.drawable.rsb, R.drawable.zdg,
            R.drawable.lt, R.drawable.ls, R.drawable.slj
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}
