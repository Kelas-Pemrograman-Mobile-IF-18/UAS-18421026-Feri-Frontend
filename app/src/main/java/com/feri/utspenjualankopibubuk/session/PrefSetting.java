package com.feri.utspenjualankopibubuk.session;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.feri.utspenjualankopibubuk.admin.HomeAdminActivity;
import com.feri.utspenjualankopibubuk.pembeli.HomePembeli;

public class PrefSetting {
    public static String _id;
    public static String nama;
    public static String emailuserName;
    public static String noTelp;
    public static String role;
    public static String password;
    Activity activity;

    public PrefSetting(Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharedPreferences(){
        SharedPreferences preferences = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return preferences;
    }

    public void isLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if(session.isLoggedIn()){
            pref = getSharedPreferences();
            _id = pref.getString("_id", "");
            nama = pref.getString("nama", "");
            emailuserName = pref.getString("emailuserName", "");
            noTelp = pref.getString("noTelp", "");
            role = pref.getString("role", "");
            password = pref.getString("password", "");
        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent i = new Intent(activity, activity.getClass());
            activity.startActivity(i);
            activity.finish();
        }
    }

    public void checkLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        _id = pref.getString("_id", "");
        nama = pref.getString("nama", "");
        emailuserName = pref.getString("emailuserName", "");
        noTelp = pref.getString("noTelp", "");
        role = pref.getString("role", "");
        password = pref.getString("password", "");
        if (session.isLoggedIn()){
            if (role.equals("1")){
                Intent i = new Intent(activity, HomeAdminActivity.class);
                activity.startActivity(i);
                activity.finish();
            }else {
                Intent i = new Intent(activity, HomePembeli.class);
                activity.startActivity(i);
                activity.finish();
            }
        }
    }

    public void storeRegIdSharedPreferences(Context context, String _id, String nama,
                                            String emailuserName, String noTelp, String role,
                                            String password, SharedPreferences prefs){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("_id", _id);
        editor.putString("nama", nama);
        editor.putString("emailuserName", emailuserName);
        editor.putString("noTelp", noTelp);
        editor.putString("role", role);
        editor.putString("password", password);
        editor.commit();
    }
}
