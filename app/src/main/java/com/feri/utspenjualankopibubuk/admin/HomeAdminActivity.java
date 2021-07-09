package com.feri.utspenjualankopibubuk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.session.PrefSetting;
import com.feri.utspenjualankopibubuk.session.SessionManager;
import com.feri.utspenjualankopibubuk.users.LoginActivity;
import com.feri.utspenjualankopibubuk.users.Registrasi;

public class HomeAdminActivity extends AppCompatActivity {
    LinearLayout btnLogout, cardDataKopi, btnInputDataKopi , profileadmin;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().hide();

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharedPreferences();
        session= new SessionManager(HomeAdminActivity.this);
        prefSetting.isLogin(session, prefs);

        btnLogout = (LinearLayout) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardDataKopi = (LinearLayout) findViewById(R.id.cardDataKopi);
        cardDataKopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataKopi.class);
                startActivity(i);
                finish();
            }
        });

        btnInputDataKopi = (LinearLayout) findViewById(R.id.btnInputDataKopi);
        btnInputDataKopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataKopi.class);
                startActivity(i);
                finish();
            }
        });

        profileadmin = (LinearLayout) findViewById(R.id.profileadmin);
        profileadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
    }
}