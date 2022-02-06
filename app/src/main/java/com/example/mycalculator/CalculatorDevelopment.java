package com.example.mycalculator;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class CalculatorDevelopment implements Parcelable {

    private boolean flag = true;
    private String symbol;
    private float answer;
    ArrayList listNumber = new ArrayList<>();

    public CalculatorDevelopment() {

    }


    public float getAnswer() {
        return answer;
    }


    /**
     * распределение методов для кнопок
     * @param value
     * если нажата кнопка с символом арифметической операции, вызывается метод methodAddSymbol(value)
     * если нажата кнопка delete, вызывается метод methodDelete()
     * если нажата кнопка равно, вызывается метод  equals();
     * во всех других случаях вызывается метод calculation(value);
     */
    public void distribution (String value) {
        switch (value) {
            case "*":
            case "÷":
            case "+":
            case "-":
            case "%":
            case ".":
                methodAddSymbol(value);
                break;
            case "c":
                methodDelete();
                break;
            case "=":
                equals();
                break;
            default:
                calculation(value);
                break;
        }
    }

    /** метод добавления в коллекцию чисел
     * @param value
     * 1) если символ арифметической операции не был введен ранее
     * добавляется цифра в коллекцию
     * flag = true (разрешение на следующий ввод символа)
     *
     * 2) во всех других случаях
     * добавляется цифра в коллекцию
     * flag = true (разрешение на следующий ввод символа)
     * выполняется арифметическая операция с двумя числами, выведенными из массива
     */
    public void calculation(String value) {
        if (symbol == null) {
            listNumberAdd(value);
            flag = true;
        } else {
            listNumberAdd(value);
            flag = true;
            arithmeticOperation();
        }
    }

    /** добавление в коллекцию символов
     * @param value
     * 1) для ввода отрицательного числа
     *
     * если колекция пустая и введенный символ является "-"
     * flag = false (запрет на следующий ввод символа)
     * добавление значения в коллекцию
     *
     * 2) для ввода положительного числа
     *
     * если колекция не пустая и флаг true
     * flag = false (запрет на следующий ввод символа)
     * symbol присвоение значения value;
     * добавление значения в коллекцию
     */
    public void methodAddSymbol(String value) {
        if (listNumber.size() == 0 && value.equals("-")) {
            flag = false;
            listNumberAdd(value);
        }
        if (listNumber.size() != 0 && flag) {
            flag = false;
            symbol = value;
            listNumberAdd(value);
        }
    }

    /** вычисление двух чисел, взятых из коллекции
     *
     */
    public void arithmeticOperation() {
        int symbolNumber = listNumber.lastIndexOf(symbol); // записали индекс последнего символа в коллекции
        StringBuilder numberOne = new StringBuilder();
        StringBuilder numberTwo = new StringBuilder();
        float number1 = 0f;
        float number2;

        for (int i = 0; i < symbolNumber; i++) {
            if (listNumber.get(i).equals("÷") || listNumber.get(i).equals("*") || listNumber.get(i).equals("-") // если до текущего ввода символа арифметической операции был введен символ
                    || listNumber.get(i).equals("+") || listNumber.get(i).equals("%")) {
                number1 = answer; // присваиваем номеру 1 значение ответа
                break;
            }
        }

        if (number1 < 0.1) { //  если до текущего ввода символа арифметической операции не был введен другой символ
            for (int i = 0; i < symbolNumber; i++) {
                numberOne.append(listNumber.get(i));
            }
            number1 = Float.parseFloat(numberOne.toString()); // извлекли из массива первое число
        }

        for (int i = symbolNumber + 1; i < listNumber.size(); i++) {
            numberTwo.append(listNumber.get(i));
        }
        number2 = Float.parseFloat(numberTwo.toString()); // извлекли из массива второе число


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

    /** метод кнопки "="
     * symbol присваивается null
     * коллекция очищается
     * выполняется метод добавления в коллекцию значения переменной answer
     * answer присваивается 0
     */
    public void equals() {
        symbol = null;
        listNumber.clear();
        calculation(String.valueOf(answer));
        answer = 0.0f;
    }

    /** метод кнопки С
     * коллекция очищается
     * answer присваивается 0
     * symbol присваивается null
     */
    public void methodDelete () {
        listNumber.clear();
        answer = 0.0F;
        symbol = null;
    }

    /**
     * метод добавления элемента в коллекцию
     */
    private void listNumberAdd (String value) {
        listNumber.add(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (flag ? 1 : 0));
        parcel.writeString(symbol);
        parcel.writeFloat(answer);
        parcel.writeTypedList(listNumber);
    }

    protected CalculatorDevelopment(Parcel in) {
        flag = in.readByte() != 0;
        symbol = in.readString();
        answer = in.readFloat();
        in.readTypedList(listNumber, CalculatorDevelopment.CREATOR);
    }

    public static final Creator<CalculatorDevelopment> CREATOR = new Creator<CalculatorDevelopment>() {
        @Override
        public CalculatorDevelopment createFromParcel(Parcel in) {
            return new CalculatorDevelopment(in);
        }

        @Override
        public CalculatorDevelopment[] newArray(int size) {
            return new CalculatorDevelopment[size];
        }
    };
}
