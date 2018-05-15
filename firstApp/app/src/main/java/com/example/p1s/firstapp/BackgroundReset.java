package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by P1 S on 3/24/2017.
 */

public class BackgroundReset extends AsyncTask<String,Void,String> {

    ResetPassword ctx;
    String userID;
    AlertDialog alertDialog;

    BackgroundReset(ResetPassword ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        userID = params[4];
        String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/ResetPassword.php";


        if (type.equals("reset")) {
            try {
                String Cpassword = params[1];
                String Npassword = params[2];
                String CNpassword = params[3];
                URL url = new URL(reset_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                        (outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder
                        .encode(userID, "UTF-8") + "&" + URLEncoder.encode("identifier_cpassword", "UTF-8") + "=" + URLEncoder
                        .encode(Cpassword, "UTF-8") + "&" + URLEncoder.encode("identifier_npassword",
                        "UTF-8") + "=" + URLEncoder.encode(Npassword, "UTF-8") + "&" + URLEncoder.encode("identifier_cnpassword",
                        "UTF-8") + "=" + URLEncoder.encode(CNpassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
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
      //  alertDialog = new AlertDialog.Builder(ctx).create();
       // alertDialog.setTitle("Reset Status");

    }

    @Override
    protected void onPostExecute(String result) {
       // String res = result.trim();
        Log.d("Pawy",result);
        if (result.equalsIgnoreCase("4"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(ctx);
            mbuilder.setMessage("YOUR CURRENT PASSWORD AND DATABASE EXISTING PASSWORD IS NOT SAME");
            mbuilder.setTitle("PASSWORD INFORMATION");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent i = new Intent(ctx,After_login.class);
                    i.putExtra("start",userID);
                    ctx.startActivity(i);


                }
            }).create().show();

        }
        else if (result.equalsIgnoreCase("2"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(ctx);
            mbuilder.setMessage("RESET SUCCESSFULL");
            mbuilder.setTitle("PASSWORD INFORMATION");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(ctx, Main2Activity.class);
                    ctx.startActivity(intent);

                }
            }).create().show();
        }
        else if (result.equalsIgnoreCase("3"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(ctx);
            mbuilder.setMessage("CONFIRM PASSWORD AND NEW PASSWORD NOT MATCH");
            mbuilder.setTitle("PASSWORD INFORMATION");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(ctx, After_login.class);
                    intent.putExtra("start",userID);
                    ctx.startActivity(intent);

                }
            }).create().show();
        }

        else if (result.equalsIgnoreCase("6"))
        {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(ctx);
            mbuilder.setMessage("YOUR CURRENT PASSWORD AND NEW PASSWORD ARE SAME");
            mbuilder.setTitle("PASSWORD INFORMATION");
            mbuilder.setPositiveButton("Dismiss",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(ctx, After_login.class);
                    intent.putExtra("start",userID);
                    ctx.startActivity(intent);

                }
            }).create().show();
        }





    }
    public void newActivity()
    {


    }
};

