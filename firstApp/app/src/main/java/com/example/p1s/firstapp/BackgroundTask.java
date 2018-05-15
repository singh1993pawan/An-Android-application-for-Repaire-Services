package com.example.p1s.firstapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by P1 S on 3/11/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }

    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    protected String doInBackground(String... params)
    {
        String reg_url="http://andromeda.nitc.ac.in/~m140383ca/register.php";

        String method = params[0];
        if (method == "register")
        {
            String name = params[1];
            String email = params[2];
            String address = params[3];
            String mobile = params[4];
            String password = params[5];
            String confpassword = params[5];
            try
            {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,
                        "UTF-8"));
                String data = URLEncoder.encode("identifier_name","UTF-8") +"="+URLEncoder.encode
                        (name,"UTF-8")+"&"+URLEncoder.encode("identifier_email","UTF-8")
                        +"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode
                        ("identifier_address","UTF-8") +"="+URLEncoder.encode(address,"UTF-8")
                        +"&"+URLEncoder.encode("identifier_mobile","UTF-8") +"="+URLEncoder.encode
                        (mobile,"UTF-8")+"&"+URLEncoder.encode("identifier_password","UTF-8")
                        +"="+URLEncoder.encode(password,"UTF-8")+"&"+URLEncoder.encode
                        ("identifier_confpassword","UTF-8")+"="+URLEncoder.encode(confpassword,
                        "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is=httpURLConnection.getInputStream();
                is.close();
                return "Registration Success";
            } catch (Exception e) {
                Log.e("error msg", e.toString());
               // e.printStackTrace();
            }

        }
        return null;
    }
    protected void onProgressUpdate(Void... values){

    }
    protected void onPostExecute(String result)
    {
        Toast.makeText(ctx,result, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ctx,Main2Activity.class);
        ctx.startActivity(intent);
       //finish();
    }
}
