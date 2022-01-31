package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {
    private boolean flag = true;
    private TextView input;
    private TextView result;
    private String symbol;
    private float answer = 0f;
    ArrayList listNumber = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // поле вывода текста
        input = findViewById(R.id.input);

        // поле вывода результата
        result = findViewById(R.id.result);

        // кнопка "с" удаление всех значений из коллекций
        findViewById(R.id.button_c).setOnClickListener(view -> methodDelete(listNumber));

        // кнопка умножение
        findViewById(R.id.button_multiplication).setOnClickListener(view -> methodAddSymbol(listNumber, "*"));

        // кнопка деление
        findViewById(R.id.button_division).setOnClickListener(view -> methodAddSymbol(listNumber, "÷"));

        // кнопка минус
        findViewById(R.id.button_minus).setOnClickListener(view -> methodAddSymbol(listNumber, "-"));

        // кнопка плюс
        findViewById(R.id.button_plus).setOnClickListener(view -> methodAddSymbol(listNumber, "+"));

        // кнопка равно
        findViewById(R.id.button_equals).setOnClickListener(view -> equals(listNumber));

        // кнопка проценты
        findViewById(R.id.button_percent).setOnClickListener(view -> methodAddSymbol(listNumber, "%"));

        // кнопка точка
        findViewById(R.id.button_dot).setOnClickListener(view -> methodAddSymbol(listNumber, "."));

        // 1
        findViewById(R.id.button_1).setOnClickListener(view -> calculation(listNumber, "1"));

        // 2
        findViewById(R.id.button_2).setOnClickListener(view -> calculation(listNumber, "2"));

        // 3
        findViewById(R.id.button_3).setOnClickListener(view -> calculation(listNumber, "3"));

        // 4
        findViewById(R.id.button_4).setOnClickListener(view -> calculation(listNumber, "4"));

        // 5
        findViewById(R.id.button_5).setOnClickListener(view -> calculation(listNumber, "5"));

        // 6
        findViewById(R.id.button_6).setOnClickListener(view -> calculation(listNumber, "6"));

        // 7
        findViewById(R.id.button_7).setOnClickListener(view -> calculation(listNumber, "7"));

        // 8
        findViewById(R.id.button_8).setOnClickListener(view -> calculation(listNumber, "8"));

        // 9
        findViewById(R.id.button_9).setOnClickListener(view -> calculation(listNumber, "9"));

        // 0
        findViewById(R.id.button_0).setOnClickListener(view -> calculation(listNumber, "0"));
    }



    // метод добавления в коллекцию чисел
    public void calculation(ArrayList list, String value) {
        if (symbol == null) { // если символ арифметической операции не был введен ранее
            list.add(value); // добавляем цифру в коллекцию
            view(list); // выводим коллекцию в textView
        } else { // если символ арифметической операции был введен ранее
            list.add(value); // добавляем цифру в коллекцию
            flag = true;
            arithmeticOperation(list); // выполняем арифметическую операцию с двумя числами, выведенными из массива
            view(list); // выводим коллекцию в textView
            result.setText(String.valueOf(answer)); // выводим результат в textViewResult
        }
    }

    // добавление в коллекцию символов
    public void methodAddSymbol(ArrayList list, String value) {
        if (list.size() != 0 && flag) {
            flag = false;
            symbol = value;
            list.add(value);
            view(list);
        }
    }

    // вывод значений Arraylist в textView
    public void view(ArrayList list) {
        String valueResult = "";
        for (Object o : list) {
            valueResult += o;
        }
        input.setText(valueResult);
    }

    // вычисление двух чисел, взятых из коллекции
    public void arithmeticOperation(ArrayList list) {
        int symbolNumber = list.lastIndexOf(symbol); // записали индекс последнего символа в коллекции
        String numberOne = "";
        String numberTwo = "";
        float number1 = 0f;
        float number2 = 0f;

        for (int i = 0; i < symbolNumber; i++) {
            if (list.get(i).equals("÷") || list.get(i).equals("*") || list.get(i).equals("-") // если до текущего ввода символа арифметической операции был введен символ
                    || list.get(i).equals("+") || list.get(i).equals("%")) {
                number1 = answer; // присваиваем номеру 1 значение ответа
                break;
            }
        }

        if (number1 < 0.1) { //  если до текущего ввода символа арифметической операции не был введен другой символ
            for (int i = 0; i < symbolNumber; i++) {
                numberOne += list.get(i);
            }
            number1 = Float.parseFloat(numberOne); // извлекли из массива первое число
        }

        for (int i = symbolNumber + 1; i < list.size(); i++) {
            numberTwo += list.get(i);
        }
        number2 = Float.parseFloat(numberTwo); // извлекли из массива второе число


        switch (symbol) {
            case "÷":
                answer = number1 / number2;
                break;
            case "*":
                answer = number1 * number2;
                break;
            case "-":
                answer = number1 - number2;
                break;
            case "+":
                answer = number1 + number2;
                break;
            case "%":
                answer = number1 * number2 / 100;
                break;
        }
    }

    // метод равно
    public void equals(ArrayList list) {
        symbol = null;
        list.clear();
        calculation(list, String.valueOf(answer));
        answer = 0.0f;
        result.setText("");
        list.clear();
    }

    // метод кнопки С
    public void methodDelete (ArrayList list) {
        list.clear();
        input.setText("0");
        answer = 0.0F;
        symbol = null;
        result.setText(String.valueOf(answer));
    }
}
