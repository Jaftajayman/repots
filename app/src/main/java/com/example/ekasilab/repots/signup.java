package com.example.ekasilab.repots;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }
databasehelper helper = new databasehelper(this);
    public void onSignUpClick(View v) {
        if (v.getId() == R.id.BsignUp) {
            EditText name = (EditText) findViewById(R.id.editName);
            EditText email = (EditText) findViewById(R.id.editEmail);
            EditText uname = (EditText) findViewById(R.id.editUName);
            EditText pass1 = (EditText) findViewById(R.id.editPassword);
            EditText pass2 = (EditText) findViewById(R.id.editCPass);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

           if (!(pass1str.equals(pass2str))) {
                Toast pass = Toast.makeText(signup.this, "Passwords don't match", Toast.LENGTH_LONG);
                pass.show();
            }

            else {
                //insert data in the database
                contact c = new contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUName(unamestr);
                c.setPass(pass1str);
                helper.insertContent(c);

           }
        }

    }
}
