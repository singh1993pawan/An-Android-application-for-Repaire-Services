package com.example.p1s.firstapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email=(EditText) findViewById(R.id.user_email);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


    }

    public void forgot(View view) {
        String useremail=email.getText().toString();
        Log.d("pawy","mayank");
        //Toast.makeText(this,useremail.concat("pawy"),Toast.LENGTH_SHORT).show();
        String type="forgot";
        String random=generateCIN();
        BackgroundForgot backgroundForgot = new BackgroundForgot(this);
        backgroundForgot.execute(type,useremail,random);


    }

    public String generateCIN() {
        Character arr[]=new Character[62];
        int i, k;
        for (i=0; i < 62; i++) {
            k=48;
            while (i < 10) arr[i++]=(char) k++;
            k=65;
            while (i >= 10 && i < 36) arr[i++]=(char) k++;
            k=97;
            while (i >= 36 && i < 62) arr[i++]=(char) k++;
        }
        StringBuffer str=new StringBuffer(5);
        for (i=0; i < 8; i++) {
            int index=getRandomNumber(1000, 9000);
            if (i == 1 || i == 3 || i == 4) {
                index%=10;
                str.append(arr[index]);
            } else {
                index%=61;
                str.append(arr[index]);
            }
        }
        return str.toString();
    }

    public int getRandomNumber(int minimum, int maximum) {
        return ((int) ((Math.random() * (maximum - minimum)) + maximum));
    }
/*

    class BackgroundForgot extends AsyncTask<String, Void, String> {

        Context context;
        String id;
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
            String reset_url="http://andromeda.nitc.ac.in/~m140383ca/Forgot.php";

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
                    String post_data=URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
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
            //Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
            if (result.equalsIgnoreCase("1")) {
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

            } else if (result.equalsIgnoreCase("2")) {

                String random=generateCIN();

                //Update table
                //String useremail=email.getText().toString();
                String type="forgot";
                BackgroundF backgroundF=new BackgroundF(ForgotPassword.this);
                backgroundF.execute(type, id, random);
                Toast.makeText(context, random, Toast.LENGTH_LONG).show();
                String emails="bhupesh.v94@gmail.com";

                //send mail
                SendMail.send("singh1993pawan@gmail.com","7860841276",emails,"Forgot Password ",
                        "Your New Password is  :");

            }


        }

        public String generateCIN() {
            Character arr[]=new Character[62];
            int i, k;
            for (i=0; i < 62; i++) {
                k=48;
                while (i < 10) arr[i++]=(char) k++;
                k=65;
                while (i >= 10 && i < 36) arr[i++]=(char) k++;
                k=97;
                while (i >= 36 && i < 62) arr[i++]=(char) k++;
            }
            StringBuffer str=new StringBuffer(5);
            for (i=0; i < 8; i++) {
                int index=getRandomNumber(1000, 9000);
                if (i == 1 || i == 3 || i == 4) {
                    index%=10;
                    str.append(arr[index]);
                } else {
                    index%=61;
                    str.append(arr[index]);
                }
            }
            return str.toString();
        }


    }

    public int getRandomNumber(int minimum, int maximum) {
        return ((int) ((Math.random() * (maximum - minimum)) + maximum));
    }


    //second background start


    class BackgroundF extends AsyncTask<String, Void, String> {

        Context context;
        String id, random;

        //AlertDialog alertDialog;

        BackgroundF(ForgotPassword ctx) {
            context=ctx;
        }

        public BackgroundF(DialogInterface.OnClickListener onClickListener) {
        }


        @Override
        protected String doInBackground(String... params) {

            String type=params[0];
            id=params[1];
            random=params[2];
            String reset_url="http://andromeda.nitc.ac.in/~m140383ca/ForgotF.php";

            if (type.equals("forgot")) {
                try {
                    //Toast.makeText(context, "what happen", Toast.LENGTH_SHORT).show();

                    URL url=new URL(reset_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data=URLEncoder.encode("identifier_email", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" + URLEncoder.encode("identifier_password", "UTF-8") + "=" + URLEncoder.encode(random, "UTF-8");
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

        }


    }*/
}






