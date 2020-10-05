package dyrda.fit.bstu.by.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle arguments=getIntent().getExtras();
        company=(Company) arguments.getSerializable(Company.class.getSimpleName());
        ((TextView)findViewById(R.id.Name)).setText(company.getName());
        ((TextView)findViewById(R.id.Email)).setText(company.getEmail());
        ((TextView)findViewById(R.id.PhoneNumber)).setText(company.getPhoneNumber());
        ((TextView)findViewById(R.id.Location)).setText(company.getLocation());
        ((TextView)findViewById(R.id.Link)).setText(company.getSocialN());
    }

    public void clickBack(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btnEdit(View view){
        Intent intent=new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra(Company.class.getSimpleName(), company);
        startActivity(intent);
    }

    public void btnDelete(View view){
       SQLiteDatabase db=getBaseContext().openOrCreateDatabase("database.db", MODE_PRIVATE,null);
       String query="delete from companies where id="+company.getId()+";";
       db.execSQL(query);
       db.close();
       Toast.makeText(getApplicationContext(),"Компания удалена", Toast.LENGTH_LONG).show();
       startActivity(new Intent(this, MainActivity.class));
    }
}