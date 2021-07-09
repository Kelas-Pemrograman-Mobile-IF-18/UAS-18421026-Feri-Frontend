package com.feri.utspenjualankopibubuk.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.server.BaseURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registrasi extends AppCompatActivity {
    TextView btnKeLogin;
    Button btnRegistrasi;
    EditText edtnama, edtusernameemail, edtnoTelp, edtpassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;
    @Override

//METOD 1
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtnama = (EditText) findViewById(R.id.edtnama);
        edtusernameemail = (EditText) findViewById(R.id.edtusernameemail);
        edtnoTelp = (EditText) findViewById(R.id.edtnoTelp);
        edtpassword = (EditText) findViewById(R.id.edtpassword);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

    // MESIN registrasi
        btnRegistrasi = (Button) findViewById(R.id.btnRegistrasi);
        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnama = edtnama.getText().toString();
                String strUsernameemail = edtusernameemail.getText().toString();
                String strnoTelp = edtnoTelp.getText().toString();
                String strpassword = edtpassword.getText().toString();

                if (strnama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strUsernameemail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strnoTelp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nomor Telpon Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    registrasi(strnama, strUsernameemail, strnoTelp, strpassword);
                }
            }
        });

    // MESIN login
        btnKeLogin = (TextView) findViewById(R.id.btnKeLogin);
        btnKeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registrasi.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

// METOD 2
    // MESIN registrasi
    public void registrasi(String nama, String emailuserName, String noTelp, String password){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nama", nama);
        params.put("emailuserName", emailuserName);
        params.put("noTelp", noTelp);
        params.put("role", "2");
        params.put("password", password);

        pDialog.setMessage("Mohon Tunguu..");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if (status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Registrasi.this, LoginActivity.class);
                                startActivity(i);
                                finish();
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