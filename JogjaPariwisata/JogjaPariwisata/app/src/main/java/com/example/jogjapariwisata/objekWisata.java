package com.example.jogjapariwisata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class objekWisata {
  public String nama_pariwisata;
  public String alamat_pariwisata;
  public String detail_pariwisata;
  public String txt_gambar_pariwisata;
  public Bitmap gambar_pariwisata;

  // Constructor to convert JSON object into a Java class instance
  public objekWisata(JSONObject object){
    try {
      this.nama_pariwisata = object.getString("nama_pariwisata");
      this.alamat_pariwisata = object.getString("alamat_pariwisata");
      this.detail_pariwisata = object.getString("detail_pariwisata");
      this.txt_gambar_pariwisata = object.getString("gambar_pariwisata");

      new DownloadImageFromInternet().execute(this.txt_gambar_pariwisata);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private class DownloadImageFromInternet extends AsyncTask<String, Void, Void> {

    public DownloadImageFromInternet() { }

    protected Void doInBackground(String... urls) {
      String imageURL = urls[0];
      Bitmap bimage = null;
      try {
        InputStream in = new java.net.URL(imageURL).openStream();
        bimage = BitmapFactory.decodeStream(in);
        gambar_pariwisata = bimage;

      } catch (Exception e) {
        Log.e("Error Message", e.getMessage());
        e.printStackTrace();
      }

      return null;
    }


  }

/*
  private Bitmap downloadImage(String urls) {
    String imageURL = urls;
    Bitmap bimage = null;
    try {
      InputStream in = new java.net.URL(imageURL).openStream();
      bimage = BitmapFactory.decodeStream(in);

    } catch (Exception e) {
      Log.e("Error Message", e.getMessage());
      e.printStackTrace();
    }
    return bimage;
  }
*/
  public String getNama_pariwisata(){
    return nama_pariwisata;
  }

  //retrieve users' hometown
  public String getAlamat_pariwisata(){
    return alamat_pariwisata;
  }

  public String getDetail_pariwisata(){
    return detail_pariwisata;
  }

  public String getTxt_gambar_pariwisata(){
    return txt_gambar_pariwisata;
  }

  public Bitmap getGambar_pariwisata(){
    return gambar_pariwisata;
  }

  public static ArrayList<objekWisata> fromJson(JSONArray jsonObjects) {
    ArrayList<objekWisata> ow = new ArrayList<objekWisata>();
    for (int i = 0; i < jsonObjects.length(); i++) {
      try {
        ow.add(new objekWisata(jsonObjects.getJSONObject(i)));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return ow;
  }
}
