//Create an app for persistent storage using SharedPreferences and
       // perform CRUD operation ( Create, Read, Update & Delete) .
package com.example.mca.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    EditText et1,et2, et3;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.btn1);
        b2=(Button) findViewById(R.id.btn2);
        b3=(Button) findViewById(R.id.btn3);
        b4=(Button) findViewById(R.id.btn4);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
et3=(EditText)findViewById(R.id.et3);
        final SharedPreferences sharedpreferences = getSharedPreferences(MY_PREFS_NAME , Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=et3.getText().toString();
                String name=et1.getText().toString();
                String mob=et2.getText().toString();
                if(TextUtils.isEmpty(id)) {
                    et3.setError("Enter id");
                }
                else if (TextUtils.isEmpty(name)) {
                    et1.setError("Enter Name");
                }
                else if (TextUtils.isEmpty(mob)) {
                    et2.setError("Enter Mobile");
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(id, id);
                    editor.putString("Name" + id, name);
                    editor.putString("Phone" + id, mob);
                    editor.apply();
                    String display = "Data Inserted \n ********** \n Roll No: " + id + " \n Name:" + name + "\n mobile :" + mob;
                    Toast.makeText(MainActivity.this, display, Toast.LENGTH_SHORT).show();
                    et1.getText().clear();
                    et2.getText().clear();
                    et3.getText().clear();
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String print=et3.getText().toString();

                String a=sharedpreferences.getString("Name"+print,"");
                if(TextUtils.isEmpty(print)) {
                    et3.setError("Enter id");

                }
                else if (TextUtils.isEmpty(a)) {
                    Toast.makeText(MainActivity.this,"Data Not Found", Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(MainActivity.this, "Name :" + sharedpreferences.getString("Name" + print, "") +
                                    "\n Phone :" + sharedpreferences.getString("Phone" + print, ""),
                            Toast.LENGTH_LONG).show();
                }



            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String print=et3.getText().toString();
                String a=sharedpreferences.getString("Name"+print,"");
                if(TextUtils.isEmpty(print)) {
                 // et3.setError("Enter id");

                }
                else if (TextUtils.isEmpty(a)) {
                    Toast.makeText(MainActivity.this,"Data Not Found", Toast.LENGTH_SHORT).show();

                }
               else {
                    et3.setEnabled(false);
                    et1.setText(sharedpreferences.getString("Name" + print, ""));
                    et2.setText(sharedpreferences.getString("Phone" + print, ""));
                }
            }
        });
        b3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                String print=et3.getText().toString();
                String name=et1.getText().toString();
                String mob=et2.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    et1.setError("Enter Name");
                }
                else if (TextUtils.isEmpty(mob)) {
                    et2.setError("Enter Mobile");
                }
                else {
                    et3.setEnabled(true);
                editor.putString("Name"+print,name);
                editor.putString("Phone"+print,mob);
                editor.apply();
                Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_LONG).show();
                et1.getText().clear();
                et2.getText().clear();
                et3.getText().clear();
                }
                return false;
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String print=et3.getText().toString();

                String a=sharedpreferences.getString("Name"+print,"");
                if(TextUtils.isEmpty(print)) {
                    et3.setError("Enter id");

                }
                else if (TextUtils.isEmpty(a)) {
                    Toast.makeText(MainActivity.this,"Data Not Found", Toast.LENGTH_SHORT).show();

                }
                else {
                    sharedpreferences.edit().remove(print).apply();

                    sharedpreferences.edit().remove("Name" + print).apply();
                    sharedpreferences.edit().remove("Phone" + print).apply();
                    Toast.makeText(MainActivity.this, "The Data Deleted With Roll No :" + print, Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
