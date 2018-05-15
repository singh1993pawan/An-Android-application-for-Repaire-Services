package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class SP_Contact extends AppCompatActivity {


    String user;
    EditText contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp__contact);

        contact = (EditText) findViewById(R.id.updatedcontact);
        user = getIntent().getExtras().getString("start");
        TextView t = (TextView) findViewById(R.id.textshow);
        t.setText(user);
    }

    public void UpdateContact(View view) {
        String newcontact = contact.getText().toString();
        String type = "update";

        SP_Contact.BackgroundUpdateContact backgroundUpdateContact = new SP_Contact
                .BackgroundUpdateContact(this);
        backgroundUpdateContact.execute(type, newcontact, user);


    }


    class BackgroundUpdateContact extends AsyncTask<String, Void, String> {

        Context ctx;
        String user;
        //AlertDialog alertDialog;

        BackgroundUpdateContact(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            user = params[2];
            String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/SPUpdateContact.php";

            if (type.equals("update")) {
                try {
                    String mobile = params[1];
                    //Toast.makeText(ctx, "what happen", Toast.LENGTH_SHORT).show();

                    URL url = new URL(reset_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                            (outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("identifier_mobile", "UTF-8") + "=" +
                            URLEncoder
                                    .encode(mobile, "UTF-8")+"&"+URLEncoder.encode
                            ("identifier_email",
                                    "UTF-8") +"="+URLEncoder.encode(user,"UTF-8");
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
            //Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
            if (result.equalsIgnoreCase("1")) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(ctx);
                mbuilder.setMessage("YOUR CONTACT NUMBER ALREADY EXIST");
                mbuilder.setTitle("UPDATE INFORMATION");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, SPUpdate.class);
                        i.putExtra("start", user);
                        ctx.startActivity(i);


                    }
                }).create().show();

            } else if (result.equalsIgnoreCase("2")) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(ctx);
                mbuilder.setMessage("SUCCESSFULL UPDATE CONTACT NUMBER ");
                mbuilder.setTitle("UPDATE INFORMATION");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, SPUpdate.class);
                        i.putExtra("start", user);
                        ctx.startActivity(i);


                    }
                }).create().show();

            }




        }


    }
};




