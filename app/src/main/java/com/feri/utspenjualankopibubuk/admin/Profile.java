package com.feri.utspenjualankopibubuk.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView nama, emailuserName, noTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile User");

        nama = (TextView) findViewById(R.id.nama);
        emailuserName = (TextView) findViewById(R.id.emailuserName);
        noTelp = (TextView) findViewById(R.id.noTelp);

        nama.setText(PrefSetting.nama);
        emailuserName.setText(PrefSetting.emailuserName);
        noTelp.setText(PrefSetting.noTelp);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

}