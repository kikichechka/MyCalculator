package com.example.mycalculator.domain;

import androidx.annotation.StyleRes;

import com.example.mycalculator.R;

public enum Themes {
    ONE(R.style.Theme_MyCalculator,"one"),
    TWO(R.style.Theme_MyCalculator2,"two"),
    THREE(R.style.Theme_MyCalculator3, "three");

    @StyleRes
    private int style;
    private String key;

    Themes(int style, String key) {
        this.style = style;
        this.key = key;
    }

    public int getStyle() {
        return style;
    }

    public String getKey() {
        return key;
    }
}
