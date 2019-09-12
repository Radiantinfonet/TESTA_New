package com.radiant.rpl.testa.Registration;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Vipin", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public  String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("Vipin",Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");


        return position;
    }



    public void setPreferences1(Context context, String key, String value) {

        SharedPreferences.Editor editor1 = context.getSharedPreferences("pankaj", Context.MODE_PRIVATE).edit();
        editor1.putString(key, value);
        editor1.commit();
    }


    public  String getPreferences1(Context context, String key){
        SharedPreferences language_prefs = context.getSharedPreferences("pankaj",Context.MODE_PRIVATE);
        String position1 = language_prefs.getString(key, "");

        return position1;


    }







    /*public void cleard(Context context, String key, String value){
        SharedPreferences prefs1 = context.getSharedPreferences("Vipin",	Context.MODE_PRIVATE);
        prefs1.clear();
        prefs1.commit();
    }*/
}
