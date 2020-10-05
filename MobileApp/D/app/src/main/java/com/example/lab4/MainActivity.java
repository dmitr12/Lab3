package com.example.lab4;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Company> companies;
    List<Company> selectedCompanies;
    ListView compList;
    CompanyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        companies = GetData();
        Toast.makeText(this, String.valueOf(companies.size()), Toast.LENGTH_SHORT).show();
        compList = (ListView) findViewById(R.id.companiesList);
        adapter = new CompanyAdapter(this, R.layout.list_item, companies);
        compList.setAdapter(adapter);
        registerForContextMenu(compList);

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
        compList.setLongClickable(true);
        compList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Company company = (Company) adapterView.getItemAtPosition(i);
                companyPopupMenu(view, company, adapterView);
                adapter.toggleChecked(i);
                return true;
            }
        });

    }


    private void companyPopupMenu(final View view, final Company company, final AdapterView<?> adapterView) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.copyMenu:
                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("", company.getName());
                        clipboardManager.setPrimaryClip(clipData);
                        break;
                    case R.id.deleteMenu:

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Для продолжения нажмите ОК")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lab4.db", MODE_PRIVATE, null);
                                        List<Company> list = adapter.getCheckedItems();
                                        for (int ind = 0; ind < list.size(); ind++) {
                                            db.execSQL("Delete from Conpanies where id = " + list.get(ind).getId());
                                            adapter.remove(list.get(ind));
                                        }
                                        db.close();
                                        Toast.makeText(getApplicationContext(), "Успешно удалено", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Отмена", null);
                        builder.create().show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addMenu:
                startActivity(new Intent(this, AddActivity.class));
                return true;
            case R.id.editMenu:
                List<Company> list = adapter.getCheckedItems();
                if (list.size() > 1) {
                    Toast.makeText(getApplicationContext(), "Выберете один элемент", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                    intent.putExtra(Company.class.getSimpleName(), list.get(0));
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ClickAdd(View view) {
        startActivity(new Intent(this, AddActivity.class));
    }

    public List<Company> GetData() {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("lab4.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Conpanies (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, email TEXT, phoneNumber TEXT, location TEXT, link TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM Conpanies;", null);

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

