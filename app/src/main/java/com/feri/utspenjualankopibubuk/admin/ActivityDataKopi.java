package com.feri.utspenjualankopibubuk.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.adapter.AdapterKopi;
import com.feri.utspenjualankopibubuk.model.ModelKopi;
import com.feri.utspenjualankopibubuk.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDataKopi extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterKopi adapter;
    ListView list;

    ArrayList<ModelKopi> newsList = new ArrayList<ModelKopi>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kopi);

        getSupportActionBar().setTitle("Data Kopi");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterKopi(ActivityDataKopi.this, newsList);
        list.setAdapter(adapter);
        getAllKopi();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataKopi.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }

    private void getAllKopi() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataKopi, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data buku = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelKopi kopi = new ModelKopi();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodeKopi = jsonObject.getString("kodeKopi");
                                    final String jenisKopi = jsonObject.getString("jenisKopi");
                                    final String hargaKopi = jsonObject.getString("hargaKopi");
                                    final String gamabr = jsonObject.getString("gambar");
                                    kopi.setKodeKopi(kodeKopi);
                                    kopi.setJenisKopi(jenisKopi);
                                    kopi.setHargaKopi(hargaKopi);
                                    kopi.setGambar(gamabr);
                                    kopi.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(ActivityDataKopi.this, EditKopiDanHapus.class);
                                            a.putExtra("kodeKopi", newsList.get(position).getKodeKopi());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("jenisKopi", newsList.get(position).getJenisKopi());
                                            a.putExtra("hargaKopi", newsList.get(position).getHargaKopi());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(kopi);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}