package com.mercury.redeem;

import android.content.Context;
import android.content.SharedPreferences;

public class SavePref {

    public final String PREFS_NAME = "extraclass_prefs";
    private final Context context;
    public static final String FAVORITES2 = "Image_Favorite";

    public final String FAVORITES = "Notification";


    public SavePref(Context activity) {
        this.context = activity;
    }

    public String getClasses() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("classes", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setClasses(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("classes", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getClassesName() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("classesname", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setClassesName(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("classesname", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getemail() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("email", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setemail(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("email", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUserPhone() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("userphone", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setUserPhone(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("userphone", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUserId() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("userId", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setUserId(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("userId", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
            return prefs.getString("name", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setName(String classes) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", classes);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}