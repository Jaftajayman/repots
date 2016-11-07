package com.example.ekasilab.repots;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class display extends Activity {
    Button  ButtonViewAll, ButtonAlldetails, buttonUpdate,buttonDelete;
    EditText editName, editEmail,editTextId, editTextUsername, editTextPassword;
    String pass;
    String username;
    databasehelper db = new databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ButtonAlldetails=(Button)findViewById(R.id.Bview);
        ButtonViewAll=(Button)findViewById(R.id.BDisplay);

        editTextId=(EditText)findViewById(R.id.id);
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.Email);
        //editMarks = (EditText) findViewById(R.id.marks);
        editTextUsername =(EditText)findViewById(R.id.username);
        editTextPassword=(EditText)findViewById(R.id.editPassword);

        buttonUpdate=(Button)findViewById(R.id.update) ;
        buttonDelete=(Button)findViewById(R.id.Bdelete);


        username  = getIntent().getStringExtra("Welcome ");
pass = getIntent().getStringExtra("pass");
        TextView tv =(TextView)findViewById(R.id.display);
        tv.setText("Welcome "+ username);
    }


    //viewing all data
    public  void getAll(View v){
        ButtonAlldetails.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=db.getAlldata();
                        if (res.getCount()==0){

                            //show message
                            showMessage("Sorry!!!", "Nothing found");
                            return;

                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0) + "\n" );
                            buffer.append("Name :"+ res.getString(1) + "\n" );
                            buffer.append("Marks :"+ res.getString(2) + "\n" );
                            buffer.append("Username :"+ res.getString(3) + "\n" );
                            buffer.append("Password :"+ res.getString(4) + "\n\n------------------------\n" );


                        }
                        //show all data
                        showMessage("Data ", buffer.toString());
                    }
                }
        );
    }
    //showing profile

    public  void viewAll(View v){
        ButtonViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=db.getdata(pass);
                        if (res.getCount()==0){

                            //show message
                            showMessage("Sorry!!!", "Nothing found");
                            return;

                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0) + "\n" );
                            buffer.append("Name :"+ res.getString(1) + "\n" );
                            buffer.append("Marks:"+ res.getString(2) + "\n" );
                            buffer.append("Username :"+ res.getString(3) + "\n" );
                            buffer.append("Password :"+ res.getString(4) + "\n\n------------------------\n" );


                        }
                        //show all data
                        showMessage("Data ", buffer.toString());
                    }
                }
        );
    }



    public  void  showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }




}

