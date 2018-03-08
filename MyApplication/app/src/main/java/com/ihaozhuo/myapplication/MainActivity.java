package com.ihaozhuo.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ihaozhuo.myapplication.databinding.ActivityMainBinding;

import java.util.Stack;

/**
 * @author zhenh
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<String> a = new ArrayList<>();
//                a.add("sdfs");
//                String s = a.get(0);
//                s += "sd";
//                Log.e("onclick", a.get(0));
                String str = mBinding.et.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    calculateStr(str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "算式不合法", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void calculateStr(String str) throws Exception {
        str = str.replaceAll("\\s*", "");
        Stack<Character> characters = new Stack<>();
        Stack<String> result = new Stack<>();
        boolean isLastChar = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                if (isLastChar) {
                    String peek = result.pop();
                    peek += String.valueOf(c);
                    result.add(peek);
                } else {
                    result.add(String.valueOf(c));
                }
                isLastChar = true;
            } else {
                isLastChar = false;
                if (c == ')' || c == '）') {
                    while (characters.size() > 0) {
                        Character pop = characters.pop();
                        if (pop == '(' || pop == '（') {
                            break;
                        } else {
                            result.add(String.valueOf(pop));
                        }
                    }
                } else {
                    if (characters.isEmpty() || isNewCharHighLevel(characters.peek(), c)) {
                        characters.add(c);
                    } else {
                        while (!characters.isEmpty()) {
                            result.add(String.valueOf(characters.pop()));
                        }
                        characters.add(c);
                    }
                }
            }
        }
        while (!characters.isEmpty()) {
            result.add(String.valueOf(characters.pop()));
        }
//        StringBuilder builder = new StringBuilder();
//        for (String s : result) {
//            builder.append(s);
//        }
//        Log.e("StrResult", builder.toString());

    }

    private boolean isNewCharHighLevel(char existChar, char newChar) {
        return !(!isRightChar(existChar) || !isRightChar(newChar)) && getCharLevel(existChar) <= getCharLevel(newChar);
    }

    private int getCharLevel(char newChar) {
        switch (newChar) {
            case '+':
            case '-':
            case '—':
                return 1;
            case '*':
            case '×':
            case '/':
            case '÷':
            case '%':
                return 2;
            case '(':
            case ')':
            case '（':
            case '）':
                return 3;
        }
        return 0;
    }

    private boolean isRightChar(char character) {
        return character == '+'
                || character == '-'
                || character == '—'
                || character == '*'
                || character == '×'
                || character == '/'
                || character == '÷'
                || character == '%'
                || character == '('
                || character == ')'
                || character == '（'
                || character == '）';
    }
}
