package com.example.eshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class GoodsActivity extends AppCompatActivity {
    private ListView listView;
    private GoodsAdapter goodsAdapter;

    // 商品名称数组
    private String[] names;

    // 商品价格数组
    private double[] prices;

    // 商品图片资源数组
    private int[] images;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        // 初始化数组
        names = new String[]{
                getString(R.string.goods1_name),
                getString(R.string.goods2_name),
                getString(R.string.goods3_name)
        };

        prices = new double[]{300.2, 100, 3000};

        images = new int[]{
                R.drawable.goods1,
                R.drawable.goods2,
                R.drawable.goods3
        };

        // 创建商品列表
        ArrayList<Goods> goodsList = createGoodsList();

        // 初始化 ListView
        listView = findViewById(R.id.goods_list_view);

        // 创建并设置 Adapter
        goodsAdapter = new GoodsAdapter(this, goodsList);
        listView.setAdapter(goodsAdapter);
    }

    // 根据数组创建商品列表
    private ArrayList<Goods> createGoodsList() {
        ArrayList<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Goods goods = new Goods(names[i], images[i], prices[i]);
            goodsList.add(goods);
        }
        return goodsList;
    }
}

