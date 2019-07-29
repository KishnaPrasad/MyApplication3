package com.example.amma.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    DatabaseHelper db;

    Button addButton, updateButton, viewButton, deleteButton;
    EditText fname, fsurname, fmark, fid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        fname = findViewById(R.id.fname);
        fsurname = findViewById(R.id.fsurname);
        fmark = findViewById(R.id.fmarks);
        fid = findViewById(R.id.fid);
        addButton = findViewById(R.id.fbadd);
        updateButton = findViewById(R.id.fbupdate);
        viewButton = findViewById(R.id.fbview);
        deleteButton = findViewById(R.id.fbdelete);

    }

    public void addData(View view) {
        boolean insert = databaseHelper.insertData(fname.getText().toString(), fsurname.getText().toString(), fmark.getText().toString());
        if (insert == true)
            Toast.makeText(getApplicationContext(), "DATA INSERTED", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
        fname.setText("");
        fmark.setText("");
        fsurname.setText("");
        fid.setText("");

    }
    public void viewData(View v){
        Cursor result =  databaseHelper.getallData();
        int c = result.getCount();
        if(c==0)
            Toast.makeText(getApplicationContext(),"No data retrievd",Toast.LENGTH_LONG).show();
        StringBuffer str = new StringBuffer();
        while(result.moveToNext()){
            str.append("Id:"+result.getString(0));
            str.append("Name:"+result.getString(1));
            str.append("Surname:"+result.getString(2));
            str.append("Marks:"+result.getString(3));
        }
        Toast.makeText(getApplicationContext(),str.toString(),Toast.LENGTH_LONG).show();
    }
    public void btnDelete(View view){
        Integer d = databaseHelper.delete(fid.getText().toString());
        if(d>0)
            Toast.makeText(getApplicationContext(),"Record Deleted!!!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Record Not Found!!!",Toast.LENGTH_LONG).show();
        //databaseHelper.delete(fid.getText().toString());
    }
}
