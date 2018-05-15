package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class sp_option extends AppCompatActivity {

    //String [] SPINNERLIST={"ELECTRICIAN","PLUMBER","CORPENTER","HOME CLEANING"};

    EditText user_name,user_email,user_address,user_password,user_confpassword,user_mobile,prize;
    String name,email,address,password,confpassword,mobile,cprize;
    Button regs_button;
    Spinner sp;
    String dmh="";
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_option);
        user_name = (EditText)findViewById(R.id.text1);
        user_email = (EditText)findViewById(R.id.text2);
        user_address = (EditText)findViewById(R.id.text3);
        user_mobile = (EditText)findViewById(R.id.text4);
        user_password = (EditText)findViewById(R.id.text5);
        user_confpassword = (EditText)findViewById(R.id.text6);
        prize = (EditText)findViewById(R.id.text7);
        //user_category = (EditText)findViewById(R.id.text7);


        sp = (Spinner) findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout
                .simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",
                 //       Toast.LENGTH_LONG).show();
                dmh=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        regs_button = (Button) findViewById(R.id.regs_button);
        regs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();//call when button is click
            }
        });



       // ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout
         //       .simple_dropdown_item_1line,SPINNERLIST);
        //MaterialBetterSpinner betterSpinner=(MaterialBetterSpinner) findViewById(R.id.spinner);
        //betterSpinner.setAdapter(arrayAdapter);
    }
    public void register(){
        intialize(); //intialuze the input to string variable
        if (!validate())
        {
            Toast.makeText(this,"Sign up faild",Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSuccess();
        }
    }

    private void onSignupSuccess() {

        this.service_provider(null);
        Toast.makeText(this,"Sign up Successfull",Toast.LENGTH_SHORT).show();
    }




    public boolean validate()
    {
        boolean valid= true;
        if ((name.isEmpty()) || (name.length() > 32))
        {
            user_name.setError("Please Enter valid name");
            valid = false;
        }
        if ((email.isEmpty()) || (!Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            user_email.setError("Please Enter valid Email");
            valid = false;

        }
        if ((address.isEmpty()) || (address.length() > 200))
        {
            user_address.setError("Please Enter valid Address");
            valid = false;
        }
        if (mobile.isEmpty() || (mobile.length()!=10))
        {
            user_mobile.setError("Please Enter valid moblie number");
            valid = false;
        }
        if (password.isEmpty() || (password.length()<8))
        {
            user_password.setError("Please Enter minimum 8 digit password");
            valid = false;
        }
       if (confpassword.isEmpty() || (confpassword.length()<8))
        {
             user_confpassword.setError("Please Enter minimum 8 digit password");
            valid = false;
        }
        if (!password.equals(confpassword))
        {
            user_confpassword.setError("Not match Password");
            valid = false;
        }

        if (cprize.isEmpty())
        {
            prize.setError("Please Enter valid amount");
            valid = false;
        }


       /* if ((category.isEmpty()) || (category.length() > 32))
        {
            user_category.setError("Please Enter valid category");
            valid = false;
        }*/


        return valid;
    }

    void intialize()
    {
        // name = user_name.toString().trim();
        //email = user_email.toString().trim();
        //address = user_address.toString().trim();
        //mobile = user_mobile.toString().trim();
        //password = user_password.toString().trim();
        //confpassword = user_confpassword.toString().trim();
        name = user_name.getText().toString();
        email = user_email.getText().toString();
        address = user_address.getText().toString();
        mobile = user_mobile.getText().toString();
        password= user_password.getText().toString();
        confpassword = user_confpassword.getText().toString();
        cprize = prize.getText().toString();

        //category = user_category.getText().toString();
    }

    public void service_provider(View view){

        name = user_name.getText().toString();
        email = user_email.getText().toString();
        address = user_address.getText().toString();
        mobile = user_mobile.getText().toString();
        password= user_password.getText().toString();
        confpassword = user_confpassword.getText().toString();
        cprize = prize.getText().toString();
        //category = user_category.getText().toString();
        String method = "register";
        BackgroundTaskSP background = new BackgroundTaskSP(this);
        background.execute(method,name,email,address,mobile,password,confpassword,dmh,cprize);
    }
}
