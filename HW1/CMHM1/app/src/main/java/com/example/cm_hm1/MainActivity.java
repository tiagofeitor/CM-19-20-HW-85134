package com.example.cm_hm1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String callNum;
    TextView numberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        callNum="";

        numberView = findViewById(R.id.textNum);

        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="0";
                updateNumber();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="1";
                updateNumber();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="2";
                updateNumber();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="3";
                updateNumber();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="4";
                updateNumber();
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="5";
                updateNumber();
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="6";
                updateNumber();
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="7";
                updateNumber();
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="8";
                updateNumber();
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="9";
                updateNumber();
            }
        });
        findViewById(R.id.buttonAsterisc).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="*";
                updateNumber();
            }
        });
        findViewById(R.id.buttonCardinal).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum+="#";
                updateNumber();
            }
        });
        findViewById(R.id.buttonCall).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialPhoneNumber(callNum);
            }
        });
    }

    public void updateNumber(){
        if(numberView!=null){
            numberView.setText(callNum);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
