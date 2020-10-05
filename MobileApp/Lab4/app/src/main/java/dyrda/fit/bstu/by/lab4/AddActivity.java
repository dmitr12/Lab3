package dyrda.fit.bstu.by.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText phone;
    EditText location;
    EditText link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name=findViewById(R.id.inpName);
        email=findViewById(R.id.inpEmail);
        phone=findViewById(R.id.inpPhoneNumber);
        location=findViewById(R.id.inpLocation);
        link=findViewById(R.id.inpLink);
    }

    protected void onResume() {
        name.setText("");
        email.setText("");
        phone.setText("");
        location.setText("");
        link.setText("");
        super.onResume();
    }

    public void clickBack(View view)
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clickAdd(View view){
        String strName=name.getText().toString();
        String strEmail=email.getText().toString();
        String strPhone=phone.getText().toString();
        String strLocation=location.getText().toString();
        String strLink=link.getText().toString();
        if(strName.isEmpty()&&strEmail.isEmpty()&&strPhone.isEmpty()&&strLocation.isEmpty()&&strLink.isEmpty()){
            Toast.makeText(this,"Необходимо заполнить все поля",Toast.LENGTH_LONG).show();
        }
        else{
            SQLiteDatabase db=getBaseContext().openOrCreateDatabase("database.db",MODE_PRIVATE,null);
            String query = "insert into companies (name, email, phoneNumber, location, link) values ('"+ strName + "', '" + strEmail + "', '" + strPhone + "', '" + strLocation +"', '" + strLink +"');";
            db.execSQL(query);
            db.close();
            Toast.makeText(this,"Компания добавлена", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}