package com.example.jogjapariwisata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  TextView count;
  Button btnHit;
  TextView txtJson;
  ProgressDialog pd;

  FirebaseAuth mAuth;
  FirebaseAuth.AuthStateListener mAuthListner;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {

    case R.id.exit:
      mAuth.signOut();
      finish();
      return(true);

  }
    return(super.onOptionsItemSelected(item));
  }

  @Override
  protected void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListner);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mAuth = FirebaseAuth.getInstance();
    mAuthListner = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser()==null)
        {
          startActivity(new Intent(MainActivity.this, signin_activity.class));
        }
      }
    };

    loadData();
  }

  private void loadData() {
    new JsonTask().execute("http://erporate.com/bootcamp/jsonBootcamp.php");
  }


  private class JsonTask extends AsyncTask<String, String, String>{

    protected void onPreExecute(){
      super.onPreExecute();

      pd = new ProgressDialog(MainActivity.this);
      pd.setMessage("Please Wait!");
      pd.setCancelable(false);
      pd.show();
    }

    protected String doInBackground(String... params){

      HttpURLConnection connection = null;
      BufferedReader reader = null;

      try {
        URL url = new URL(params[0]);
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();


        InputStream stream = connection.getInputStream();

        reader = new BufferedReader(new InputStreamReader(stream));

        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = reader.readLine()) != null) {
          buffer.append(line+"\n");
          Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

        }

        return buffer.toString();


      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (connection != null) {
          connection.disconnect();
        }
        try {
          if (reader != null) {
            reader.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      return null;
    }

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      if (pd.isShowing()){
        pd.dismiss();
      }
      JSONObject jsonObj;
      try {
        jsonObj = new JSONObject(result);
        JSONArray data = jsonObj.getJSONArray("data");

        ArrayList<objekWisata> newObjekWisata = objekWisata.fromJson(data);
        // Create the adapter to convert the array to views
        objekWisataAdapter adapter = new objekWisataAdapter(MainActivity.this, newObjekWisata);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lv_objekwisata);
        listView.setAdapter(adapter);

        int len = data.length();


        int hh = 1+1;
      } catch (JSONException e) {
        e.printStackTrace();
      }





    }

  }


}
