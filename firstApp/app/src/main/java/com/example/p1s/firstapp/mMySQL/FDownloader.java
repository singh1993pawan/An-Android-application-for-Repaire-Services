package com.example.p1s.firstapp.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by P1 S on 4/29/2017.
 */

public class FDownloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    RecyclerView rv;
    ProgressDialog pd;
    String type;

    public FDownloader(Context c, String urlAddress, RecyclerView rv,String type) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
        this.type=type;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Fetch");
        pd.setMessage("Fetching ........Please Wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {


        return this.downloadData();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s==null)
        {
            Toast.makeText(c,"Unsuccessfull,null returned",Toast.LENGTH_SHORT).show();
        }
        else {
            FDataParser parser = new FDataParser(c,rv,s,type);
            parser.execute();

        }
    }



    private String downloadData()
    {

        String result = "";
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data= URLEncoder.encode("id_category","UTF-8")+"="+URLEncoder.encode(type,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (inputStream,"iso-8859-1"));

            String line;
            while ((line = bufferedReader.readLine())!= null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d("Result",result);
            return result;
        }
        catch (Exception e) {
            Log.d("pawy",e.toString());
        }

        return  result;
    }


};

