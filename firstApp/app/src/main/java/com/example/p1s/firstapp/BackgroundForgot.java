package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.p1s.firstapp.packagesendmail.SendMail;

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
 * Created by P1 S on 4/30/2017.
 */

class BackgroundForgot extends AsyncTask<String, Void, String> {

    Context context;
    String id,random;
    //AlertDialog alertDialog;

    BackgroundForgot(ForgotPassword ctx) {
        context=ctx;
    }

    public BackgroundForgot(DialogInterface.OnClickListener onClickListener) {
    }


    @Override
    protected String doInBackground(String... params) {

        String type=params[0];
        id=params[1];
        random=params[2];
        String reset_url="http://andromeda.nitc.ac.in/~m140383ca/ForgotF.php";
   // id.trim();
        Log.d("email",id);
        if (type.equals("forgot")) {
            try {
                //Toast.makeText(ctx, "what happen", Toast.LENGTH_SHORT).show();

                URL url=new URL(reset_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data=URLEncoder.encode("id", "UTF-8") +"="+ URLEncoder.encode(id, "UTF-8")+"&"+
                        URLEncoder.encode("random","UTF-8")+"="+URLEncoder.encode(random,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line;
                while ((line=bufferedReader.readLine()) != null) {
                    result+=line;
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
       // Log.d("mayank",id);
        Log.d("mayank1",result);
        //Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
        if (result.equalsIgnoreCase("2")) {
            AlertDialog.Builder mbuilder=new AlertDialog.Builder(context);
            mbuilder.setMessage("YOUR EMAIL IS NOT VALID");
            mbuilder.setTitle("FORGOT STATUS");
            mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent i=new Intent(context, ForgotPassword.class);
                    // i.putExtra("start", user);
                    context.startActivity(i);


                }
            }).create().show();

        } else if (result.equalsIgnoreCase("1")) {



            //Update table
           // String useremail=email.getText().toString();
           // String type="forgot";
            //ForgotPassword.BackgroundF backgroundF=new ForgotPassword.BackgroundF(this);
            //backgroundF.execute(type, id, random);
            //Toast.makeText(context, random, Toast.LENGTH_LONG).show();
           // String emails="singh1993pawan@gmail.com";
            //Log.d("pawy","mayank");
            //send mail
            Toast.makeText(context,"Successfull send Password ",Toast.LENGTH_LONG).show();
            SendMail.send("singh1993pawan@gmail.com","7860841276",id,"Forgot Password ",random);

        }


    }







}
