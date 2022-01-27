package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    protected static TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list_number = new ArrayList<>();
        //ArrayList<String> list_number2 = new ArrayList<>();


        // поле вывода текста
        result = findViewById(R.id.result);

        // кнопка "с" удаление всех значений из коллекций
        findViewById(R.id.button_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_number.clear();
                result.setText("0");
            }
        });

        // кнопка умножение
        findViewById(R.id.button_multiplication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "*");
            }
        });

        // кнопка деление
        findViewById(R.id.button_division).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, ":");
            }
        });

        // кнопка удаление последнего элемента
        findViewById(R.id.button_division).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_1(list_number);
            }
        });

        // кнопка минус
        findViewById(R.id.button_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "-");
            }
        });

        // кнопка плюс
        findViewById(R.id.button_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "+");
            }
        });

        // кнопка равно
        findViewById(R.id.button_division).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // кнопка проценты
        findViewById(R.id.button_percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "%");
            }
        });

        // кнопка запятая
        findViewById(R.id.button_comma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, ",");
            }
        });


        // 1
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "1");
            }
        });

        // 2
        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "2");
            }
        });

        // 3
        findViewById(R.id.button_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "3");
            }
        });

        // 4
        findViewById(R.id.button_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "4");
            }
        });

        // 5
        findViewById(R.id.button_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "5");
            }
        });

        // 6
        findViewById(R.id.button_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "6");
            }
        });

        // 7
        findViewById(R.id.button_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "7");
            }
        });

        // 8
        findViewById(R.id.button_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "8");
            }
        });

        // 9
        findViewById(R.id.button_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "9");
            }
        });

        // 0
        findViewById(R.id.button_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                method(list_number, "0");
            }
        });
    }


    /**
     * метод добавления в коллекцию чисел
     */
    public static void method(ArrayList list, String value) {
        list.add(value);

        String value_result = "";
        for (Object o : list) {
            value_result += o;
        }

        //list.toString().replace("[", "").replace("]", "").replace(",", "");

        result.setText(value_result);
    }

    public static void delete_1(ArrayList list) {
        list.remove(list.size()-2);

        String value_result = "";
        for (Object o : list) {
            value_result += o;
        }
        result.setText(value_result);
    }
}