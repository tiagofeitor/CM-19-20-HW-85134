package com.example.cm_hm1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigDial extends AppCompatActivity{

    String id;
    String name;
    String number;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_dial);

        Intent current = getIntent();

        name = current.getStringExtra("name");
        id = current.getStringExtra("id");
        number = current.getStringExtra("number");


        EditText editName = (EditText)findViewById(R.id.editTextName);
        editName.setText(name, TextView.BufferType.EDITABLE);

        EditText editNumber = (EditText)findViewById(R.id.editTextNumber);
        editNumber.setText(number, TextView.BufferType.EDITABLE);


       findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigDial.this, MainActivity.class);
                intent.putExtra("id", id); //Optional parameters
                intent.putExtra("name" , name);
                intent.putExtra("number" , number);
                ConfigDial.this.startActivity(intent);
            }
        });
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigDial.this, MainActivity.class);

                EditText editName = findViewById(R.id.editTextName);
                String putName = editName.getText().toString();
                EditText editNumber =findViewById(R.id.editTextNumber);
                String putNumber = editNumber.getText().toString();

                intent.putExtra("id", id); //Optional parameters
                intent.putExtra("name" ,putName);
                intent.putExtra("number" , putNumber);
                ConfigDial.this.startActivity(intent);
            }
        });
    }




}
