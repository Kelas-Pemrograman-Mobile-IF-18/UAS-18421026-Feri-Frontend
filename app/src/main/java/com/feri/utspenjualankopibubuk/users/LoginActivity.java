package com.feri.utspenjualankopibubuk.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.admin.HomeAdminActivity;
import com.feri.utspenjualankopibubuk.pembeli.HomePembeli;
import com.feri.utspenjualankopibubuk.server.BaseURL;
import com.feri.utspenjualankopibubuk.session.PrefSetting;
import com.feri.utspenjualankopibubuk.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    TextView btnKeRegistrasi;
    Button btnLogin;
    EditText edtusernameemail, edtpassword;
    ProgressDialog pDialog;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    private RequestQueue mRequestQueue;

    @Override
//METOD 1
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtusernameemail = (EditText) findViewById(R.id.edtusernameemail);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharedPreferences();

        session = new SessionManager(this);
        prefSetting.checkLogin(session, prefs);

        //MESIN KE REGISTER
        btnKeRegistrasi = (TextView) findViewById(R.id.btnKeRegistrasi);
        btnKeRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Registrasi.class);
                startActivity(i);
                finish();
            }
        });

        //MESIN LOGIN
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsernameemail = edtusernameemail.getText().toString();
                String strpassword = edtpassword.getText().toString();
                if (strUsernameemail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    login(strUsernameemail, strpassword);
                }
            }
        });
    }

// METOD 2
    // MESIN Login
    public void login(String emailuserName, String password){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("emailuserName", emailuserName);
        params.put("password", password);

        pDialog.setMessage("Mohon Tunguu..");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if (status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                String data = response.getString("data");
                                JSONObject jsonObject = new JSONObject(data);
                                String role = jsonObject.getString("role");
                                String _id = jsonObject.getString("_id");
                                String nama = jsonObject.getString("nama");
                                String emailuserName = jsonObject.getString("emailuserName");
                                String noTelp = jsonObject.getString("noTelp");
                                String password = jsonObject.getString("password");
                                session.setLogin(true);
                                prefSetting.storeRegIdSharedPreferences(LoginActivity.this, _id, nama, emailuserName, noTelp, role, password, prefs);
                                if (role.equals("1")) {
                                    Intent i = new Intent(LoginActivity.this, HomeAdminActivity.class);
                                    startActivity(i);
                                    finish();
                                }else {
                                    Intent i = new Intent(LoginActivity.this, HomePembeli.class);
                                    startActivity(i);
                                    finish();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        // add the request object to the queue to be executed
        mRequestQueue.add(req);
    }

//METOD 3
    // MESIN dialog
    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }
    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}