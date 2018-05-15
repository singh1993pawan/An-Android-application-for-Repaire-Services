package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by P1 S on 4/21/2017.
 */

public class BackgroundRating  extends AsyncTask<String,Void,String> {


    Context context;
    String user;
    AlertDialog alertDialog;
    public BackgroundRating(Rating ctx){
        context = ctx;
    }


    protected String doInBackground(String... params) {
        String type = params[0];
        String email=params[1];
        String value = params[2];
        user = params[3];
        String rating="http://andromeda.nitc.ac.in/~m140383ca/Rating.php";


        if (true)
        {
            try {
                URL url = new URL(rating);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                        (outputStream, "UTF-8"));
                String post_data =  URLEncoder.encode("identifier_f1","UTF-8") +"="+URLEncoder
                        .encode(type,"UTF-8")+"&"+URLEncoder.encode("identifier_f2", "UTF-8")
                        +"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode
                        ("identifier_f4", "UTF-8") +"="+URLEncoder.encode(user,"UTF-8")+"&"+URLEncoder.encode
                        ("identifier_f3", "UTF-8") +"="+URLEncoder.encode(value,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (inputStream,"iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
      alertDialog = new AlertDialog.Builder(context).create();
       //alertDialog.setTitle("Rating Status");

    }
    @Override
    protected void onPostExecute(String result) {
        //String res = result.trim();
        if (result.equalsIgnoreCase("2"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(context);
            mbuilder.setMessage("THANK YOU FOR RATING");
            mbuilder.setTitle("RATING STATUS");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(context,selectCategory.class);
                    intent.putExtra("start",user);
                    context.startActivity(intent);

                }
            }).create().show();

        }
        if (result.equalsIgnoreCase("1"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(context);
            mbuilder.setMessage("YOU ALREADY SUBMITED RATING");
            mbuilder.setTitle("RATING STATUS");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(context,selectCategory.class);
                    intent.putExtra("start",user);
                    context.startActivity(intent);

                }
            }).create().show();

        }
        else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}


