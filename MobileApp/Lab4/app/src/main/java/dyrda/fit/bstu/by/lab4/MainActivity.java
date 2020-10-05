package dyrda.fit.bstu.by.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Company> companies;
    ListView compList;
    CompanyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companies = getData();
        compList = findViewById(R.id.companiesList);
        adapter = new CompanyAdapter(this, R.layout.list_item, companies);
        compList.setAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Company company = (Company) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra(Company.class.getSimpleName(), company);
                startActivity(intent);
            }
        };
        compList.setOnItemClickListener(itemListener);
    }

    public void clickAdd(View view)
    {
        Intent intent=new Intent(this, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public List<Company> getData() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("database.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists companies (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, email TEXT, phoneNumber TEXT, location TEXT, link TEXT)");
        Cursor query = db.rawQuery("select * from companies;", null);
        List<Company> companies = new ArrayList<>();
        if (query.moveToFirst()) {
            do {
                int id = query.getInt(0);
                String name = query.getString(1);
                String email = query.getString(2);
                String phoneNumber = query.getString(3);
                String location = query.getString(4);
                String link = query.getString(5);
                companies.add(new Company(id, name, email, phoneNumber, location, link));
            }
            while (query.moveToNext());
        }
        query.close();
        db.close();
        return companies;
    }

}