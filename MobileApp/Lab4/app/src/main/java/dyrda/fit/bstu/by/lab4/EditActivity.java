package dyrda.fit.bstu.by.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText phone;
    EditText location;
    EditText link;
    Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle arguments=getIntent().getExtras();
        company = (Company) arguments.getSerializable(Company.class.getSimpleName());
        ((TextView) findViewById(R.id.editName)).setText(company.getName());
        ((TextView) findViewById(R.id.editEmail)).setText(company.getEmail());
        ((TextView) findViewById(R.id.editPhoneNumber)).setText(company.getPhoneNumber());
        ((TextView) findViewById(R.id.editLocation)).setText(company.getLocation());
        ((TextView) findViewById(R.id.editLink)).setText(company.getSocialN());

    }

    public void clickBack(View view) {
        Intent intent=new Intent(this, InfoActivity.class);
        intent.putExtra(Company.class.getSimpleName(), company);
        startActivity(intent);
    }

    public void clickEdit(View view){
        name=findViewById(R.id.editName);
        email=findViewById(R.id.editEmail);
        phone=findViewById(R.id.editPhoneNumber);
        location=findViewById(R.id.editLocation);
        link=findViewById(R.id.editLink);
        if(name.getText().toString().isEmpty()&&email.getText().toString().isEmpty()&&phone.getText().toString().isEmpty()
                &&location.getText().toString().isEmpty()&&link.getText().toString().isEmpty()){
            Toast.makeText(this,"Необходимо заполнить все поля",Toast.LENGTH_LONG).show();
        }
        else{
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("database.db", MODE_PRIVATE, null);
            String query = "update companies set name = '" + name.getText().toString() + "', email = '" + email.getText().toString() + "', phoneNumber = '" + phone.getText().toString() +
                    "', location ='" + location.getText().toString() + "', link = '" + link.getText().toString() + "' " +
                    "where id = " + company.getId() + ";";
            db.execSQL(query);
            db.close();
            Toast.makeText(getApplicationContext(), "Компания обновлена", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);        }
    }
}