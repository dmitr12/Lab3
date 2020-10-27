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
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    CompanyAdapter adapter;
    ListView listView;
    DatabaseHelper db;
    EditText searchField;
    AsyncSelectAll asyncSelectAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        listView = findViewById(R.id.listCompanies);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Company company = (Company) adapterView.getItemAtPosition(i);
                itemPopupMenu(view, company, adapterView);
                return true;
            }
        });
        searchField=findViewById(R.id.searchField);
        registerForContextMenu(findViewById(R.id.listCompanies));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.getAll:
                asyncSelectAll=new AsyncSelectAll();
                asyncSelectAll.execute(getApplicationContext());
                try {
                    adapter=new CompanyAdapter(this, R.layout.list_item, asyncSelectAll.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
                break;
            case R.id.getFav:
                AsyncGetFav db=new AsyncGetFav();
                db.execute(getApplicationContext());
                try {
                    adapter=new CompanyAdapter(this, R.layout.list_item, db.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
                break;
            case R.id.getSort:
                AsyncSortAllSelect db1=new AsyncSortAllSelect();
                db1.execute(getApplicationContext());
                try {
                    adapter=new CompanyAdapter(this,R.layout.list_item,db1.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Company> list=new ArrayList<>();
        asyncSelectAll=new AsyncSelectAll();
        asyncSelectAll.execute(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            adapter=new CompanyAdapter(this, R.layout.list_item, asyncSelectAll.get());
            listView.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ClickAdd(View view) {
        Intent intent=new Intent(this, AddActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void onClickSearch(View view){
        if(searchField.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Заполните поле для поиска", Toast.LENGTH_LONG).show();
        }
        else{
            String query= String.format("select * from %s where %s='%s'",db.TABLE,db.column_name,searchField.getText().toString());
            List<Company> list=db.getData(query);
            if(list.size()==0)
                Toast.makeText(getApplicationContext(),"В базе данных нет компании "+searchField.getText().toString(),Toast.LENGTH_LONG).show();
            else {
                adapter=new CompanyAdapter(getApplicationContext(), R.layout.list_item, db.getData(query));
                listView.setAdapter(adapter);
                searchField.setText("");
            }
        }
    }


    private void itemPopupMenu(final View view, final Company company, final AdapterView<?> adapterView) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popoutmenu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.itemDelete:
                        AsyncDelete dlt=new AsyncDelete(getApplicationContext());
                        dlt.execute(company.getId());
                        AsyncSelectAll s=new AsyncSelectAll();
                        s.execute(getApplication());
                        try {
                            adapter=new CompanyAdapter(getApplicationContext(), R.layout.list_item, s.get());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        listView.setAdapter(adapter);
                        break;
                    case R.id.itemEdit:
                        intent=new Intent(getApplicationContext(),EditActivity.class);
                        intent.putExtra(Company.class.getSimpleName(), company);
                        startActivity(intent);
                        break;
                    case R.id.itemAddFavourite:
                        company.setIsChosen(1);
                        AsyncEdit edt=new AsyncEdit(getApplicationContext());
                        edt.equals(company);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
