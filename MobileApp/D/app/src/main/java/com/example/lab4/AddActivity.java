package com.example.lab4;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void ClickBack(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
    public void ClickAdd(View view) {
        EditText text = (EditText)findViewById(R.id.inpName);
        String name = text.getText().toString();
        EditText text1 = (EditText)findViewById(R.id.inpEmail);
        String email = text1.getText().toString();
        EditText text2 = (EditText)findViewById(R.id.inpPhoneNumber);
        String phoneNumber = text2.getText().toString();
        EditText text3 = (EditText)findViewById(R.id.inpLocation);
        String location = text3.getText().toString();
        EditText text4 = (EditText)findViewById(R.id.inpLink);
        String link = text4.getText().toString();
        if(name.length()>0 && email.length()>0 && phoneNumber.length()>0 && location.length()>0 && link.length()>0)
        {
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lab4.db", MODE_PRIVATE, null);
            String queryStr = "INSERT INTO Conpanies (name, email, phoneNumber, location, link) VALUES ('"+ name + "', '" + email + "', '" + phoneNumber + "', '" + location +"', '" + link +"');";
            db.execSQL(queryStr);
            db.close();
            Toast.makeText(this, "Успешно добавлено", Toast.LENGTH_SHORT).show();
            text.setText("");
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
        }
        else
        {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }
}