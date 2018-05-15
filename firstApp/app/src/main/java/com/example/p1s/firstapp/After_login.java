package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class After_login extends FragmentActivity {

    Button logout;
    String user;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        user=getIntent().getExtras().getString("start");
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string
                .drawer_open,R
                .string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId())
                {
                   /* case R.id.sc:
                        Intent inten=new Intent(After_login.this,selectCategory.class);
                        inten.putExtra("start",user);
                        startActivity(inten);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;*/
                    case R.id.up:
                        Intent intent=new Intent(After_login.this,Update.class);
                        intent.putExtra("start",user);
                        startActivity(intent);
                        //getSupportActionBar().setTitle("Update Profile");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.rp:
                        Intent intent1=new Intent(After_login.this,ResetPassword.class);
                        intent1.putExtra("start",user);
                        startActivity(intent1);
                        //getSupportActionBar().setTitle("Reset Password");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.da:
                       // Intent intent2=new Intent(After_login.this,After_login.class);
                       // intent2.putExtra("start",user);
                       // startActivity(intent2);
                        //getSupportActionBar().setTitle("Deactivate Account");
                        AlertDialog.Builder myAlert = new AlertDialog.Builder(After_login.this);
                        myAlert.setTitle("DELETE ACCOUNT");
                        myAlert.setIcon(R.drawable.delete);
                        myAlert.setMessage("IF YOU WANT TO DELETE YOUR ACCOUNT, PRESS YES");
                        myAlert.setPositiveButton("YES", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                YES();
                            }
                        });
                        myAlert.create().show();
                        myAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent i = new Intent(After_login.this, After_login.class);
                                i.putExtra("start", user);
                                startActivity(i);

                            }
                        }).create().show();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                       // Intent intent4=new Intent(After_login.this,Main2Activity.class);
                        //intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       // intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       // startActivity(intent4);
                        //getSupportActionBar().setTitle("Logout");
                        //logout.setOnClickListener(new View.OnClickListener() {
                          //  @Override
                           // public void onClick(View v) {
                                // After_login.logout();
                                logoutMethod();
                            //}
                        //});
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

        //logout = (Button) findViewById(R.id.log);
        TextView t=(TextView)findViewById(R.id.text_1);
        t.setText(user);
        /*
          logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // After_login.logout();
                logoutMethod();
            }
        });*/
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void logoutMethod()
    {
        Intent intent = new Intent(After_login.this,Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void selectCategory(View view){

        Intent i=new Intent(this,selectCategory.class);
        i.putExtra("start",user);
        startActivity(i);

    }


    public void ResetPassword(View view){


            Intent startNewActivity = new Intent(this, ResetPassword.class);
            startNewActivity.putExtra("start", user);
            startActivity(startNewActivity);

        }



    public void UpdateProfile(View view){
        Intent startNewActivity = new Intent(this, Update.class);
        startNewActivity.putExtra("start",user);
        startActivity(startNewActivity);
    }



    public void GiveFeedback(View view)
    {
        Intent intent = new Intent(this,giveFeedback.class);
        intent.putExtra("start",user);
        startActivity(intent);
    }
    void YES(){  String method = "delete";
        BackgroundDelete backgroundDelete = new BackgroundDelete(this);
        backgroundDelete.execute(method,user);

    }


    public void Deactivate(View view)
    {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("DELETE ACCOUNT");
        myAlert.setIcon(R.drawable.delete);
        myAlert.setMessage("IF YOU WANT TO DELETE YOUR ACCOUNT, PRESS YES");
        myAlert.setPositiveButton("YES", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
              YES();
            }
        });
        myAlert.create().show();
        myAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent i = new Intent(After_login.this, After_login.class);
                i.putExtra("start", user);
                startActivity(i);

            }
        }).create().show();



    }

    class BackgroundDelete extends AsyncTask<String, Void, String> {

        After_login context;
        String id;
        //AlertDialog alertDialog;

        BackgroundDelete(After_login ctx) {
            context=ctx;
        }

        public BackgroundDelete(DialogInterface.OnClickListener onClickListener) {
        }


        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            id = params[1];
            String reset_url = "http://andromeda.nitc.ac.in/~m140383ca/DeleteAccount.php";

            if (type.equals("delete")) {
                try {
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
                            .encode(id, "UTF-8");
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
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(context);
                mbuilder.setMessage("SUCCESSFULL DELETE ACCOUNT");
                mbuilder.setTitle("DELETE ACCOUNT");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(context, Main2Activity.class);
                       // i.putExtra("start", user);
                        context.startActivity(i);


                    }
                }).create().show();

            } else if (result.equalsIgnoreCase("2")) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(context);
                mbuilder.setMessage("SOMETHING IS WRONG PLEASE TRY AGAIN");
                mbuilder.setTitle("DELETE ACCOUNT");
                mbuilder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent i = new Intent(context, After_login.class);
                        i.putExtra("start", user);
                        context.startActivity(i);


                    }
                }).create().show();

            }




        }


    }

    public void onBackPressed()
    {

    }
};

