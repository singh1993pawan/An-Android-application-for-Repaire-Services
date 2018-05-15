package com.example.p1s.firstapp.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.p1s.firstapp.mDataObject.Spacecraft;
import com.example.p1s.firstapp.mRecycler.NotiMyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by P1 S on 5/4/2017.
 */

public class NotiDataParser extends AsyncTask<Void,Void,Integer> {


    Context c;
    RecyclerView rv;
    String jsonData;

    ProgressDialog pd;
    String type,user;

    ArrayList<Spacecraft> spacecraft=new ArrayList<>();


    public NotiDataParser(Context c, RecyclerView rv, String jsonData,String user) {
        this.c = c;
        this.rv = rv;
        this.jsonData = jsonData;
        this.user=user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing ..... Please wait");
        pd.show();
    }



    @Override
    protected Integer doInBackground(Void... params) {

        return this.parseData();
    }

    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();

        if (result==0)
        {
            Toast.makeText(c,"No Notification found",Toast.LENGTH_SHORT).show();
        }
        else {
            NotiMyAdapter adapter=new NotiMyAdapter(c,spacecraft,user);
            rv.setAdapter(adapter);

        }
    }

    private int parseData()
    {
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            spacecraft.clear();
            Spacecraft s=null;
            for (int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i);

                // int id =jo.getInt("id");
                String name = jo.getString("user_name");

                s=new Spacecraft();
                //s.setId(id);
                s.setName(name);

                // Log.d("name:",name);
                //Log.d("addres:",address);
                spacecraft.add(s);


            }

            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

