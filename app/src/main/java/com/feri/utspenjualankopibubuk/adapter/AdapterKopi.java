package com.feri.utspenjualankopibubuk.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.feri.utspenjualankopibubuk.R;
import com.feri.utspenjualankopibubuk.model.ModelKopi;
import com.feri.utspenjualankopibubuk.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.List;

public class AdapterKopi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelKopi> item;

    public AdapterKopi(Activity activity, List<ModelKopi> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_kopi, null);


        TextView KodeKopi = (TextView) convertView.findViewById(R.id.txtKodeKopi);
        TextView JenisKopi     = (TextView) convertView.findViewById(R.id.txtJenisKopi);
        TextView HargaKopi = (TextView) convertView.findViewById(R.id.txtHargaKopi);
        ImageView GambarKopi = (ImageView) convertView.findViewById(R.id.gambarKopi);

        KodeKopi.setText(item.get(position).getKodeKopi());
        JenisKopi.setText(item.get(position).getJenisKopi());
        HargaKopi.setText("Rp." + item.get(position).getHargaKopi());
        Picasso.get().load(BaseURL.baseURL + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(GambarKopi);
        return convertView;
    }
}
