package com.example.lab4;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle args = getIntent().getExtras();
        company = (Company) args.getSerializable(Company.class.getSimpleName());
        ((TextView) findViewById(R.id.editName)).setText(company.getName());
        ((TextView) findViewById(R.id.editEmail)).setText(company.getEmail());
        ((TextView) findViewById(R.id.editPhoneNumber)).setText(company.getPhoneNumber());
        ((TextView) findViewById(R.id.editLocation)).setText(company.getLocation());
        ((TextView) findViewById(R.id.editLink)).setText(company.getLink());
    }

    public void ClickEdit(View view) {



        EditText text = (EditText) findViewById(R.id.editName);
        final String name = text.getText().toString();
        EditText text1 = (EditText) findViewById(R.id.editEmail);
        final String email = text1.getText().toString();
        EditText text2 = (EditText) findViewById(R.id.editPhoneNumber);
        final String phoneNumber = text2.getText().toString();
        EditText text3 = (EditText) findViewById(R.id.editLocation);
        final String location = text3.getText().toString();
        EditText text4 = (EditText) findViewById(R.id.editLink);
        final String link = text4.getText().toString();
        if (name.length() > 0 && email.length() > 0 && phoneNumber.length() > 0 && location.length() > 0 && link.length() > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Для продолжения нажмите ОК")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lab4.db", MODE_PRIVATE, null);
                            String q = "Update Conpanies Set name = '" + name + "', email = '" + email + "', phoneNumber = '" + phoneNumber + "', location ='" + location + "', link = '" + link + "' " +
                                    "where id = " + company.getId() + ";";
                            db.execSQL(q);
                            db.close();
                            Toast.makeText(getApplicationContext(), "Успешно обновлено", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Отмена", null);
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }

    }

    public void ClickBack(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
}