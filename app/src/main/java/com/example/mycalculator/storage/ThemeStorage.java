package com.example.mycalculator.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mycalculator.domain.Themes;

public class ThemeStorage {

    private final SharedPreferences preferences;

    public ThemeStorage(Context context) {
        preferences = context.getSharedPreferences("themes", Context.MODE_PRIVATE);
    }

    public Themes getThemes (){
        String savedTheme = preferences.getString("theme", Themes.ONE.getKey());
    Themes selectedThemes = Themes.ONE;
        for (Themes themes: Themes.values()) {
            if (themes.getKey().equals(savedTheme)) {
                selectedThemes = themes;
                break;
            }
        }
        return selectedThemes;
    }

    public void saveThemes(Themes themes) {
        preferences.edit().putString("theme", themes.getKey()).apply();
    }
}
