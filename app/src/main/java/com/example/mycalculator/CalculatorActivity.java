package com.example.mycalculator;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    CalculatorDevelopment calculatorDevelopment = new CalculatorDevelopment();
    private static final String KEY = "key";
    TextView input;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // поле вывода текста
        input = findViewById(R.id.input);

        // поле вывода результата
        result = findViewById(R.id.result);

        // кнопка "с" удаление всех значений из коллекций
        findViewById(R.id.button_c).setOnClickListener(view -> pressingButton("c"));

        // кнопка умножение
        findViewById(R.id.button_multiplication).setOnClickListener(view -> pressingButton("*"));

        // кнопка деление
        findViewById(R.id.button_division).setOnClickListener(view -> pressingButton("÷"));

        // кнопка минус
        findViewById(R.id.button_minus).setOnClickListener(view -> pressingButton("-"));

        // кнопка плюс
        findViewById(R.id.button_plus).setOnClickListener(view -> pressingButton("+"));

        // кнопка равно
        findViewById(R.id.button_equals).setOnClickListener(view -> pressingButton("="));

        // кнопка проценты
        findViewById(R.id.button_percent).setOnClickListener(view -> pressingButton( "%"));

        // кнопка точка
        findViewById(R.id.button_dot).setOnClickListener(view -> pressingButton("."));

        // 1
        findViewById(R.id.button_1).setOnClickListener(view -> pressingButton("1"));

        // 2
        findViewById(R.id.button_2).setOnClickListener(view -> pressingButton("2"));

        // 3
        findViewById(R.id.button_3).setOnClickListener(view -> pressingButton("3"));

        // 4
        findViewById(R.id.button_4).setOnClickListener(view -> pressingButton("4"));

        // 5
        findViewById(R.id.button_5).setOnClickListener(view -> pressingButton("5"));

        // 6
        findViewById(R.id.button_6).setOnClickListener(view -> pressingButton("6"));

        // 7
        findViewById(R.id.button_7).setOnClickListener(view -> pressingButton("7"));

        // 8
        findViewById(R.id.button_8).setOnClickListener(view -> pressingButton("8"));

        // 9
        findViewById(R.id.button_9).setOnClickListener(view -> pressingButton("9"));

        // 0
        findViewById(R.id.button_0).setOnClickListener(view -> pressingButton("0"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY,calculatorDevelopment);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorDevelopment = (CalculatorDevelopment) savedInstanceState.getParcelable(KEY);
        view();
    }

    private void pressingButton (String value) {
        calculatorDevelopment.distribution(value);
        view();

        if (value.equals("=")) { // если введен знак "="
            calculatorDevelopment.listNumber.clear(); // коллекция очищается
        }
    }

    public void view() {
        StringBuilder valueResult = new StringBuilder();
        for (Object o : calculatorDevelopment.listNumber) {
            valueResult.append(o);
        }
        input.setText(valueResult.toString());
        result.setText(String.valueOf(calculatorDevelopment.getAnswer()));
    }
}