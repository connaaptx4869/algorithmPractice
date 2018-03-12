package com.parker.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式：
 * (1)当读到数字直接送至输出队列中；
 * (2)当读到运算符t时：
 * a.将栈中所有优先级高于或等于t的运算符弹出，送到输出队列中；
 * 这句话不好理解，可以说成这样，从栈顶开始，依次弹出比当前处理的运算符优先级高的运算符，直到一个比它优先级低的或者遇到了一个左括号就停止。
 * b.t进栈；
 * （3）读到左括号时总是将它压入栈中；
 * （4）读到右括号时，将靠近栈顶的第一个左括号上面的运算符全部依次弹出，送至输出队列后，再丢弃左括号；
 * （5）中缀表达式全部读完后，若栈中仍有运算符，将其送到输出队列中。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.editText);
        findViewById(R.id.tv_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString()
                        .replaceAll("\n|\t|\n|\\s", "");
                if (string.length() == 0) {
                    Toast.makeText(MainActivity.this, "请输入算式", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        transformAndCalculate(string);
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
