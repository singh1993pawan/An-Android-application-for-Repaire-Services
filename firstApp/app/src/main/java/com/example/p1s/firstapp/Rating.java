package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Rating extends AppCompatActivity {


    boolean checked;
    String category;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Button b1 = (Button) findViewById(R.id.rt);

        final String type = getIntent().getExtras().getString("type");
        final String email = getIntent().getExtras().getString("email");
        final String user = getIntent().getExtras().getString("user");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = cheked();
                if (b) {
                    BackgroundRating backgroundRating=new BackgroundRating(Rating.this);
                    backgroundRating.execute(type, email, category,user);
                }
                else
                {
                    Toast.makeText(Rating.this,"Please select Action Button",Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public boolean cheked()
    {
        boolean valid=true;
        if (!checked)
        {
            valid=false;
        }

        return valid;
    }

    public void rbclick(View view) {
        checked=((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked) {
                    category="Excellent";
                    break;
                } else {
                    textView.setEnabled(false);
                }
            case R.id.radioButton2:
                if (checked) {
                    category="Good";
                    break;
                } else {
                    textView.setEnabled(false);
                }
            case R.id.radioButton3:
                if (checked) {
                    category="Average";
                    break;
                } else {
                    textView.setEnabled(false);
                }
            case R.id.radioButton4:
                if (checked) {
                    category="Fair";
                    break;
                } else {
                    textView.setEnabled(false);
                }
            case R.id.radioButton5:
                if (checked) {
                    category="Poor";
                    break;
                } else {
                    textView.setEnabled(false);
                }
        }
    }

}
