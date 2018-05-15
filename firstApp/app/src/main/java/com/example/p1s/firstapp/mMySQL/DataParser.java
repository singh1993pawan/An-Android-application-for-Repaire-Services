package com.example.p1s.firstapp.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.p1s.firstapp.mDataObject.Spacecraft;
import com.example.p1s.firstapp.mRecycler.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by P1 S on 4/11/2017.
 */

public class DataParser extends AsyncTask<Void,Void,Integer> {


    Context c;
    RecyclerView rv;
    String jsonData;

    ProgressDialog pd;
    String type,user;

    ArrayList<Spacecraft> spacecraft=new ArrayList<>();


    public DataParser(Context c, RecyclerView rv, String jsonData,String type,String user) {
        this.c = c;
        this.rv = rv;
        this.jsonData = jsonData;
        this.type=type;
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
            Toast.makeText(c,"No data found",Toast.LENGTH_SHORT).show();
        }
        else {
            MyAdapter adapter=new MyAdapter(c,spacecraft,type,user);
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
                String address = jo.getString("address");
                String contact = jo.getString("mobile");
                String prize = jo.getString("prize");
                String email=jo.getString("email");
                String avg=jo.getString("average");
                String num=jo.getString("number");

                s=new Spacecraft();
                //s.setId(id);
                s.setName(name);
                s.setAddress(address);
                s.setContact(contact);
                s.setPrize(prize);
                s.setEmail(email);
                s.setAvg(avg);
                s.setPerson(num);

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
