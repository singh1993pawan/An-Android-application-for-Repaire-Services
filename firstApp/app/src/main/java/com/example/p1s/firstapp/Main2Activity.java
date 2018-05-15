package com.example.p1s.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.p1s.firstapp.R.id.radioButton1;

public class Main2Activity extends AppCompatActivity{

    EditText user_email,user_password;
    TextView textView;
    boolean checked;
     String category="user";
    String email,password;
    Button login_button;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            user_email = (EditText) findViewById(R.id.user_email);
            user_password = (EditText) findViewById(R.id.user_password);
            login_button = (Button) findViewById(R.id.login_button);
            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginMethod();//call when button is click
                }
            });

    }

    public void forgetPassword(View view)
    {
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
    }

    public void loginMethod(){
        intialize(); //intialuze the input to string variable
        if (!validate())
        {
            Toast.makeText(this,"Log In faild",Toast.LENGTH_SHORT).show();
        }
        else {
            onSigninSuccess();
        }
    }

    private void onSigninSuccess() {

        this.selfDestruct(null);
        //Toast.makeText(this,"Sign up Successfull",Toast.LENGTH_SHORT).show();
    }

    public boolean validate()
    {
        boolean valid= true;
        if (email.isEmpty())
        {
            user_email.setError("Please Enter Email");
            valid = false;

        }
        if (password.isEmpty())
        {
            user_password.setError("Please Enter password");
            valid = false;
        }

        return valid;

    }

    void intialize()
    {

        email = user_email.getText().toString();
        password= user_password.getText().toString();


    }


    public void selfDestruct(View view){
       String useremail = user_email.getText().toString();
        String password = user_password.getText().toString();
        String type = "login";
        if (category.equals("user")) {
            BackgroundLogin backgroundLogin = new BackgroundLogin(this);
            backgroundLogin.execute(type, useremail, password, category);
        }
        else if (category=="service provider")
        {
            SPBackgroundLogin backgroundLogin = new SPBackgroundLogin(this);
            backgroundLogin.execute(type, useremail, password, category);

        }
    }

    public void rbclick(View view)
    {
        checked = ((RadioButton) view).isChecked();


        switch (view.getId())
        {

            case radioButton1:
                if(checked) {
                    category = "user";
                break;
        }
            case R.id.radioButton2:
                if(checked)
                {
                    category = "service provider";
                    break;
                }

        }

    }


   /* public void forgetPassword(View view)
    {
        Intent i = new Intent(this,Forget_password.class);
        startActivity(i);

    }*/

}
