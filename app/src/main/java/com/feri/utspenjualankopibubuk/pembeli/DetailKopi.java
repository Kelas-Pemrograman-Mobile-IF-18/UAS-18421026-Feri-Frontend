package com.feri.utspenjualankopibubuk.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailKopi extends AppCompatActivity {

    EditText edtKodeKopi, edtJenisKopi, edtHargaKopi;
    ImageView imgGambarBuku;

    String strKodeKopi, strJenisKopi, strHargaKopi, strGamabr, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kopi);
        edtKodeKopi = (EditText) findViewById(R.id.edtKodeKopi);
        edtJenisKopi = (EditText) findViewById(R.id.edtJenisKopi);
        edtHargaKopi = (EditText) findViewById(R.id.edtHargaKopi);

        imgGambarBuku = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodeKopi = i.getStringExtra("kodeKopi");
        strJenisKopi = i.getStringExtra("jenisKopi");
        strHargaKopi = i.getStringExtra("hargaKopi");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeKopi.setText(strKodeKopi);
        edtJenisKopi.setText(strJenisKopi);
        edtHargaKopi.setText(strHargaKopi);
        Picasso.get().load(BaseURL.baseURL + "gambar/" + strGamabr)
                .into(imgGambarBuku);
    }
}