package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle args = getIntent().getExtras();
        Company company = (Company) args.getSerializable(Company.class.getSimpleName());
        ((TextView)findViewById(R.id.Name)).setText(company.getName());
        ((TextView)findViewById(R.id.Email)).setText(company.getEmail());
        ((TextView)findViewById(R.id.PhoneNumber)).setText(company.getPhoneNumber());
        ((TextView)findViewById(R.id.Location)).setText(company.getLocation());
        ((TextView)findViewById(R.id.Link)).setText(company.getLink());

    }
    public void ClickBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}