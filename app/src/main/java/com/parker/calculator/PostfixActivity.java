package com.parker.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;


public class PostfixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfix);
        final EditText editText = findViewById(R.id.editText);
        findViewById(R.id.tv_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString()
                        .replaceAll("\n|\t|\n|\\s", "");
                if (string.length() == 0) {
                    Toast.makeText(PostfixActivity.this, "请输入算式", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        transformAndCalculate(string);
                    } catch (Exception e) {
                        Toast.makeText(PostfixActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void transformAndCalculate(String str) throws Exception {
        str = str.replaceAll("\\s*", "");
        str = str.replaceAll("\\s", "");
        Stack<Character> mathCharacters = new Stack<>();
        ArrayList<String> formulaResult = new ArrayList<>();
        boolean isLastNumber = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.' || c == '．') {
                if (isLastNumber) {
                    String peek = formulaResult.remove(formulaResult.size() - 1);
                    peek += String.valueOf(c);
                    formulaResult.add(peek);
                } else {
                    throw new Exception("表达式有误");
                }
            } else if (Character.isDigit(c)) {
                if (isLastNumber) {
                    String peek = formulaResult.remove(formulaResult.size() - 1);
                    peek += String.valueOf(c);
                    formulaResult.add(peek);
                } else {
                    formulaResult.add(String.valueOf(c));
                }
                isLastNumber = true;
            } else {
                isLastNumber = false;
                if (c == ')' || c == '）') {
                    while (mathCharacters.size() > 0) {
                        Character pop = mathCharacters.pop();
                        if (pop == '(' || pop == '（') {
                            break;
                        } else {
                            formulaResult.add(String.valueOf(pop));
                        }
                    }
                } else if (c == '(' || c == '（') {
                    mathCharacters.add(c);
                } else if (mathCharacters.isEmpty()) {
                    mathCharacters.add(c);
                } else {
                    while (!mathCharacters.isEmpty()) {
                        int oldLevel = getCharLevel(mathCharacters.peek());
                        int newLevel = getCharLevel(c);
                        if (oldLevel >= newLevel) {
                            formulaResult.add(String.valueOf(mathCharacters.pop()));
                        } else {
                            break;
                        }
                    }
                    mathCharacters.push(c);
                }
            }
        }
        while (!mathCharacters.isEmpty()) {
            formulaResult.add(String.valueOf(mathCharacters.pop()));
        }
        StringBuilder builder = new StringBuilder();
        for (String s : formulaResult) {
            builder.append(s);
        }
        Log.e("StrResult", builder.toString());
        Stack<String> calculateResult = new Stack<>();
        for (String s : formulaResult) {
            if (isRightMathChar(s)) {
                String one = calculateResult.pop();
                String two = calculateResult.pop();
                calculateResult.push(mathCalculate(two, one, s));
            } else {
                calculateResult.push(s);
            }
        }
        Toast.makeText(this, "计算结果是" + calculateResult.pop(), Toast.LENGTH_SHORT).show();
    }

    private String mathCalculate(String one, String two, String s) throws Exception {
        if (isRightMathChar(s)) {
            switch (s) {
                case "+":
                    return String.valueOf(Double.parseDouble(one) + Double.parseDouble(two));
                case "＋":
                    return String.valueOf(Double.parseDouble(one) + Double.parseDouble(two));
                case "-":
                    return String.valueOf(Double.parseDouble(one) - Double.parseDouble(two));
                case "—":
                    return String.valueOf(Double.parseDouble(one) - Double.parseDouble(two));
                case "*":
                    return String.valueOf(Double.parseDouble(one) * Double.parseDouble(two));
                case "＊":
                    return String.valueOf(Double.parseDouble(one) * Double.parseDouble(two));
                case "×":
                    return String.valueOf(Double.parseDouble(one) * Double.parseDouble(two));
                case "/":
                    return String.valueOf(Double.parseDouble(one) / Double.parseDouble(two));
                case "／":
                    return String.valueOf(Double.parseDouble(one) / Double.parseDouble(two));
                case "÷":
                    return String.valueOf(Double.parseDouble(one) / Double.parseDouble(two));
                case "%":
                    return String.valueOf(Integer.parseInt(one) % Integer.parseInt(two));
                case "％":
                    return String.valueOf(Integer.parseInt(one) % Integer.parseInt(two));
                default:
                    throw new Exception("符号不能识别");
            }
        } else {
            throw new Exception("符号不能识别");
        }
    }

    private boolean isNewCharHighLevel(char existChar, char newChar) {
        return !(!isRightMathChar(String.valueOf(existChar))
                || !isRightMathChar(String.valueOf(newChar))
                && getCharLevel(existChar) < getCharLevel(newChar));
    }

    private int getCharLevel(char newChar) {
        switch (newChar) {
            case '+':
            case '＋':
            case '-':
            case '—':
                return 1;
            case '*':
            case '＊':
            case '×':
            case '/':
            case '／':
            case '÷':
            case '%':
            case '％':
                return 2;
        }
        return 0;
    }

    private boolean isRightMathChar(String character) {
        return character.equals("+")
                || character.equals("＋")
                || character.equals("-")
                || character.equals("—")
                || character.equals("*")
                || character.equals("＊")
                || character.equals("×")
                || character.equals("/")
                || character.equals("／")
                || character.equals("÷")
                || character.equals("%")
                || character.equals("％")
                || character.equals("(")
                || character.equals(")")
                || character.equals("（")
                || character.equals("）");
    }
}
