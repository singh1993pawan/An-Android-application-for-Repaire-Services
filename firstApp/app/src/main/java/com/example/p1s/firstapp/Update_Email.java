package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Update_Email extends AppCompatActivity {

    EditText new_email;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__email);
        new_email = (EditText) findViewById(R.id.updatedemail);
        userID = getIntent().getExtras().getString("start");
        TextView t = (TextView) findViewById(R.id.textemail);
        t.setText(userID);

    }

    public void UpdateEmail(View view) {
        String newemail = new_email.getText().toString();
        String type = "update";

        BackgroundUpdateEmail backgroundUpdateEmail = new BackgroundUpdateEmail(this);
        backgroundUpdateEmail.execute(type, newemail, userID);


    }

    class BackgroundUpdateEmail extends AsyncTask<String, Void, String> {

        Context ctx;
        String user;
       AlertDialog alertDialog;

        BackgroundUpdateEmail(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            user = params[2];
            String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/UpdateEmail.php";

            if (type.equals("update")) {
                try {
                    String emailID = params[1];
                    //Toast.makeText(ctx, "what happen", Toast.LENGTH_SHORT).show();

                    URL url = new URL(reset_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                            (outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder
                            .encode(emailID, "UTF-8")+"&"+URLEncoder.encode("identifier_oldemail",
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
                mbuilder.setMessage("EMAIL ID IS ALREADY EXISTING IN DATABASE");
                mbuilder.setTitle("UPDATE INFORMATION");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, Update.class);
                        i.putExtra("start", userID);
                        ctx.startActivity(i);


                    }
                }).create().show();

            } else if (result.equalsIgnoreCase("2")) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(ctx);
                mbuilder.setMessage("SUCCESSFULL UPDATE EMAIL ID ");
                mbuilder.setTitle("UPDATE INFORMATION");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, Main2Activity.class);
                        i.putExtra("start", userID);
                        ctx.startActivity(i);


                    }
                }).create().show();

            }




        }


    }
    };






