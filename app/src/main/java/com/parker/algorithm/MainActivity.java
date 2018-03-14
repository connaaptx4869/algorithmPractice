package com.parker.algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Author : zhenh.
 * Created by Orz on 2018/3/12 18:13.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_stack_postfix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostfixActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.tv_coin_problem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoinProblemActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
