package com.parker.algorithm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.parker.algorithm.databinding.ActivityCoinProblemBinding;

/**
 * Author : zhenh.
 * Created by Orz on 2018/3/14 15:40.
 */

public class CoinProblemActivity extends AppCompatActivity {
    private ActivityCoinProblemBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coin_problem);
        mBinding.tvCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mBinding.editText.getText().toString();
                try {
                    int num = Integer.parseInt(s);
                    mBinding.tvResult.setText(getResult(num));
                } catch (Exception ex) {
                    Toast.makeText(CoinProblemActivity.this, "数字不对", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getResult(int num) {
        StringBuilder result = new StringBuilder();
        while (true) {
            if (num % 2 == 0) {
                num = (num - 2) / 2;
                result.append("2");
            } else {
                num = (num - 1) / 2;
                result.append("1");
            }
            if (num <= 0) {
                break;
            }
        }
        return result.reverse().toString();
    }
}
