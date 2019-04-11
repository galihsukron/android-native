package com.example.jogjapariwisata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class objekWisataAdapter extends ArrayAdapter<objekWisata> {
  public objekWisataAdapter(Context context, ArrayList<objekWisata> users) {
    super(context, 0, users);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_objekwisata, parent, false);
    }

    // Get the data item for this position
    objekWisata ow = getItem(position);

    // Lookup view for data population
    TextView tv_nama_pariwisata = (TextView) convertView.findViewById(R.id.tv_nama_pariwisata);
    TextView tv_alamat_pariwisata = (TextView) convertView.findViewById(R.id.tv_alamat_pariwisata);
    TextView tv_detail_pariwisata = (TextView) convertView.findViewById(R.id.tv_detail_pariwisata);

    ImageView img_gambar_pariwisata = (ImageView) convertView.findViewById(R.id.image_view);




    // Populate the data into the template view using the data object
    tv_nama_pariwisata.setText(ow.getNama_pariwisata());
    tv_alamat_pariwisata.setText(ow.getAlamat_pariwisata());
    tv_detail_pariwisata.setText(ow.getDetail_pariwisata());

    img_gambar_pariwisata.setImageBitmap(ow.getGambar_pariwisata());


    // Return the completed view to render on screen
    return convertView;
  }


}
