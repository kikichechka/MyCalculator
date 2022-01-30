package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static boolean flag = true;
    protected static TextView input;
    protected static TextView result;
    protected static String symbol;
    protected static float answer = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // коллекция всех значений
        ArrayList<String> list_number = new ArrayList<>();

        // поле вывода текста
        input = findViewById(R.id.input);

        // поле вывода результата
        result = findViewById(R.id.result);

        // кнопка "с" удаление всех значений из коллекций
        findViewById(R.id.button_c).setOnClickListener(view -> {
            list_number.clear();
            input.setText("0");
            answer = 0.0F;
            symbol = null;
            result.setText(String.valueOf(answer));
        });

        // кнопка умножение
        findViewById(R.id.button_multiplication).setOnClickListener(view -> methodAddSymbol(list_number, "*"));

        // кнопка деление
        findViewById(R.id.button_division).setOnClickListener(view -> methodAddSymbol(list_number, "÷"));

        // кнопка минус
        findViewById(R.id.button_minus).setOnClickListener(view -> methodAddSymbol(list_number, "-"));

        // кнопка плюс
        findViewById(R.id.button_plus).setOnClickListener(view -> methodAddSymbol(list_number, "+"));

        // кнопка равно
        findViewById(R.id.button_equals).setOnClickListener(view -> {
            symbol = null;
            list_number.clear();
            calculation(list_number,String.valueOf(answer));
            answer = 0.0f;
            result.setText(String.valueOf(answer));
        });

        // кнопка проценты
        findViewById(R.id.button_percent).setOnClickListener(view -> methodAddSymbol(list_number, "%"));

        // кнопка точка
        findViewById(R.id.button_dot).setOnClickListener(view -> calculation(list_number, "."));

        // 1
        findViewById(R.id.button_1).setOnClickListener(view -> calculation(list_number, "1"));

        // 2
        findViewById(R.id.button_2).setOnClickListener(view -> calculation(list_number, "2"));

        // 3
        findViewById(R.id.button_3).setOnClickListener(view -> calculation(list_number, "3"));

        // 4
        findViewById(R.id.button_4).setOnClickListener(view -> calculation(list_number, "4"));

        // 5
        findViewById(R.id.button_5).setOnClickListener(view -> calculation(list_number, "5"));

        // 6
        findViewById(R.id.button_6).setOnClickListener(view -> calculation(list_number, "6"));

        // 7
        findViewById(R.id.button_7).setOnClickListener(view -> calculation(list_number, "7"));

        // 8
        findViewById(R.id.button_8).setOnClickListener(view -> calculation(list_number, "8"));

        // 9
        findViewById(R.id.button_9).setOnClickListener(view -> calculation(list_number, "9"));

        // 0
        findViewById(R.id.button_0).setOnClickListener(view -> calculation(list_number, "0"));
    }


    // метод добавления в коллекцию чисел
    public static void calculation(ArrayList list, String value) {
        if (symbol == null) { // если символ арифметической операции не был введен ранее
            list.add(value); // добавляем цифру в коллекцию
            view(list); // выводим коллекцию в textView
        }
        else { // если символ арифметической операции был введен ранее
            list.add(value); // добавляем цифру в коллекцию
            flag = true;
            arithmeticOperation(list); // выполняем арифметическую операцию с двумя числами, выведенными из массива
            view(list); // выводим коллекцию в textView
            result.setText(String.valueOf(answer)); // выводим результат в textViewResult
        }
    }

    // добавление в коллекцию символов
    public static void methodAddSymbol(ArrayList list, String value) {
        if (list.size() != 0 && flag) {
            flag = false;
            symbol = value;
            list.add(value);
            view(list);
        }
    }

    // вывод значений Arraylist в textView
    public static void view(ArrayList list) {
        String value_result = "";
        for (Object o : list) {
            value_result += o;
        }
        input.setText(value_result);
    }

    // вычисление двух чисел, взятых из коллекции
    public static void arithmeticOperation(ArrayList list) {
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
}