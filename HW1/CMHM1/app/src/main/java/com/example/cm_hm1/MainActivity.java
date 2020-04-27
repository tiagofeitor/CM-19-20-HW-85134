package com.example.cm_hm1;


import com.example.cm_hm1.ConfigDial;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String callNum;
    String dialNumber1, dialNumber2, dialNumber3, dialName1, dialName2, dialName3;

    TextView numberView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialNumber1 = "123456789";
        dialNumber2 = "987654321";
        dialNumber3 = "1238789654";
         dialName1 = "ZÉ" ;
         dialName2 = "PAI";
         dialName3="MARIA";

        callNum="";

        Intent current = getIntent();
        String configName = current.getStringExtra("name");
        String configId = current.getStringExtra("id");
        String configNumber = current.getStringExtra("number");
        if(configId!=null) {
            if (configId.contains("1")) {

                dialName1 = configName;
                dialNumber1 = configNumber;
            }
            if (configId.contains("2")) {
                dialName2 = configName;
                dialNumber2 = configNumber;
            }
            if (configId.contains("3")) {
                dialName3 = configName;
                dialNumber3 = configNumber;
            }
        }
        Button dial1 = findViewById(R.id.dial1);
        dial1.setText(dialName1);

        Button dial2 = findViewById(R.id.dial2);
        dial2.setText(dialName2);

        Button dial3 = findViewById(R.id.dial3);
        dial3.setText(dialName3);

        setOnClickListeners();

        numberView = findViewById(R.id.textNum);

        findViewById(R.id.dial1).setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigDial.class );
                intent.putExtra("id", "1");
                intent.putExtra("name" , dialName1);
                intent.putExtra("number" , dialNumber1);
                MainActivity.this.startActivity(intent);
                return true;
            }
        });
        findViewById(R.id.dial2).setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigDial.class );
                intent.putExtra("id", "2");
                intent.putExtra("name" , dialName2);
                intent.putExtra("number" , dialNumber2);
                MainActivity.this.startActivity(intent);

                return true;
            }
        });
        findViewById(R.id.dial3).setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigDial.class );
                intent.putExtra("id", "3");

                intent.putExtra("name" , dialName3);
                intent.putExtra("number" , dialNumber3);
                MainActivity.this.startActivity(intent);

                return true;
            }
        });

    }

    public void setOnClickListeners() {

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
        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callNum=="") return;
                if (callNum.length() == 1)
                    callNum = "";
                else
                    callNum = callNum.substring(0, callNum.length() - 1);

                updateNumber();

            }
        });
        findViewById(R.id.buttonDelete).setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                callNum = "";
                updateNumber();
                return true;
            }
        });

        findViewById(R.id.dial1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum = dialNumber1;
                updateNumber();
            }
        });

        findViewById(R.id.dial2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum = dialNumber2;
                updateNumber();
            }
        });

        findViewById(R.id.dial3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callNum = dialNumber3;
                updateNumber();
            }
        });



    }

    public void updateNumber(){

        if(numberView!=null ){
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
