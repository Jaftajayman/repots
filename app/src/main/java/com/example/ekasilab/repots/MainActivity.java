package com.example.ekasilab.repots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
databasehelper helper = new databasehelper(this);
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button)findViewById(R.id.BLogin);
        btnLogin.setOnClickListener(this);

    }


    public void startActivity(View view){
        Intent contactPage = new Intent(MainActivity.this,signup.class);
        startActivity( contactPage );


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BLogin) {
            EditText a = (EditText) findViewById(R.id.username);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.pass);
            String pass = b.getText().toString();

            String  password = helper.searchPass(str);
            if (pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this, display.class);
                i.putExtra("Welcome ",str);
                i.putExtra("pass",pass);
                startActivity(i);
            }

            else
            {
                Toast msg = Toast.makeText(MainActivity.this, "username and Passwords don't match", Toast.LENGTH_LONG);
                msg.show();
            }

        }
    }
}
