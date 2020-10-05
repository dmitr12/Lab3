package dyrda.fit.bstu.by.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        companies = getData();
        compList = findViewById(R.id.companiesList);
        adapter = new CompanyAdapter(this, R.layout.list_item, companies);
        compList.setAdapter(adapter);
        registerForContextMenu(compList);
        compList.setOnItemClickListener(itemListener);
        compList.setLongClickable(true);
        compList.setOnItemLongClickListener(longClickListener);

    }

    void companyPopupMenu(final View view, final Company company, final AdapterView<?> adapterView){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.copyMenu:
                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("", company.getName());
                        clipboardManager.setPrimaryClip(clipData);
                        break;
                    case R.id.deleteMenu:

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Вы действительно хотите произвести удаление?")
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("database.db", MODE_PRIVATE, null);
                                        List<Company> list = adapter.getCheckedItems();
                                        for (int ind = 0; ind < list.size(); ind++) {
                                            db.execSQL("delete from companies where id= " + list.get(ind).getId());
                                            adapter.remove(list.get(ind));
                                        }
                                        db.close();
                                        adapter.removeChecks();
                                        Toast.makeText(getApplicationContext(), "Данные удалены", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Нет", null);
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
        switch (item.getItemId()){
            case R.id.addMenu:
                clickAdd(findViewById(R.id.addMenu));
            case R.id.editMenu:
                List<Company> companies=adapter.getCheckedItems();
                if(companies.size()==0)
                    Toast.makeText(this,"Элемент не выбран", Toast.LENGTH_LONG).show();
                else if(companies.size()>1)
                    Toast.makeText(this,"Выберите один элемент для изменения", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                    intent.putExtra(Company.class.getSimpleName(), companies.get(0));
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Company company = (Company) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            intent.putExtra(Company.class.getSimpleName(), company);
            startActivity(intent);
        }
    };

    AdapterView.OnItemLongClickListener longClickListener=new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Company company=(Company)adapterView.getItemAtPosition(i);
            companyPopupMenu(view, company, adapterView);
            adapter.toggleChecked(i);
            return true;
        }
    };

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