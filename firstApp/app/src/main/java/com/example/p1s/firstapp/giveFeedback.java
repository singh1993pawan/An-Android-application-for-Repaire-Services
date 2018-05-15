package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class giveFeedback extends AppCompatActivity {

    EditText comments;
    String users,dmh="";
    Spinner sp;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);
        comments = (EditText) findViewById(R.id.comment);
        users = getIntent().getExtras().getString("start");
        TextView t = (TextView) findViewById(R.id.text_1);
        t.setText(users);


        sp = (Spinner) findViewById(R.id.ratingSpinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.rating,android.R.layout
                .simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",
                       Toast.LENGTH_LONG).show();
                dmh=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void GiveFeedback(View view)
    {
        String cmts = comments.getText().toString();
        String method = "feedback";
        BackgroundFeedback backgroundFeedback = new BackgroundFeedback(this);
        backgroundFeedback.execute(method,cmts,dmh,users);
    }


    class BackgroundFeedback extends AsyncTask<String, Void, String> {

        Context ctx;
        String user;
        //AlertDialog alertDialog;

        BackgroundFeedback(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            user = params[3];
            String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/giveFeedback.php";
            //Toast.makeText(ctx,"what the hell",Toast.LENGTH_LONG).show();
            if (type.equals("feedback")) {
                try {
                    String cmt = params[1];
                    String rating= params[2];

                    URL url = new URL(reset_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                            (outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("identifier_comment", "UTF-8") + "=" + URLEncoder
                            .encode(cmt, "UTF-8")+"&"+URLEncoder.encode("identifier_rating",
                            "UTF-8") +"="+URLEncoder.encode(rating,"UTF-8")+"&"+URLEncoder.encode("identifier_email", "UTF-8")
                            +"="+URLEncoder.encode(user,"UTF-8");
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
                mbuilder.setMessage("SUCCESSFUL SUBMIT FEEDBACK AND RATING");
                mbuilder.setTitle("FEEDBACK STATUS");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, After_login.class);
                        i.putExtra("start", user);
                        ctx.startActivity(i);


                    }
                }).create().show();

            } else if (result.equalsIgnoreCase("2")) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(ctx);
                mbuilder.setMessage("SOME THING IS WRONG , YOUR FEEDBACK IS NOT STORED IN " +
                        "DATABASE TRY AGAIN ");
                mbuilder.setTitle("FEEDBACK STATUS");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(ctx, After_login.class);
                        i.putExtra("start", user);
                        ctx.startActivity(i);


                    }
                }).create().show();

            }




        }


    }
};


