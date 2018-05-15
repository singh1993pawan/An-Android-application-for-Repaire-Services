package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
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
 * Created by P1 S on 4/17/2017.
 */

public class SPBackgroundLogin extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    String useremail;

    SPBackgroundLogin(Context ctx) {
        context = ctx;
    }

    protected String doInBackground(String... params) {
        String type = params[0];
        String category = params[3];
       // String userID = params[2];
        useremail = params[1];
        String password = params[2];
        String login_url = "http://andromeda.nitc.ac.in/~m140383ca/login.php";
        String login_url2 = "http://andromeda.nitc.ac.in/~m140383ca/sp_login.php";
        String login1_url = "http://andromeda.nitc.ac.in/~m140383ca/loginservice.php";
        String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/ResetPassword.php";

        if (type.equals("login") && category.equals("service provider")) {
            try {

                URL url = new URL(login_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_email", "UTF-8") + "=" +
                        URLEncoder.encode(useremail, "UTF-8") + "&" + URLEncoder.encode("identifier_password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
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
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }
    @Override
    protected void onPostExecute(String result) {
        String res = result.trim();
        if (res.equals("Login successfull sp")){
            Intent i = new Intent(context,SPAfter_login.class);
            i.putExtra("start",useremail);
            context.startActivity(i);
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