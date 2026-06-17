package com.sauzask.hissi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

final class ThemeHelper {
    private static final String PREF_THEME = "theme";
    private static final String THEME_AMOLED = "amoled";
    private static final String THEME_DARK = "dark";
    private static final String THEME_WHITE = "white";

    private ThemeHelper() {
    }

    static void apply(Activity activity) {
        String theme = PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(PREF_THEME, THEME_AMOLED);
        if (THEME_WHITE.equals(theme)) {
            activity.setTheme(C0018R.style.AppThemeWhite);
        } else if (THEME_DARK.equals(theme)) {
            activity.setTheme(C0018R.style.AppThemeDark);
        } else {
            activity.setTheme(C0018R.style.AppThemeAmoled);
        }
    }

    static void showThemeDialog(final Activity activity) {
        final String[] labels = {"AMOLED black", "Dark", "White"};
        final String[] values = {THEME_AMOLED, THEME_DARK, THEME_WHITE};
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(activity);
        String current = pref.getString(PREF_THEME, THEME_AMOLED);
        int checked = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(current)) {
                checked = i;
                break;
            }
        }
        new AlertDialog.Builder(activity)
                .setTitle("Theme")
                .setSingleChoiceItems(labels, checked, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pref.edit().putString(PREF_THEME, values[which]).commit();
                        dialog.dismiss();
                        activity.recreate();
                    }
                })
                .show();
    }
}
