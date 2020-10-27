package dyrda.fit.bstu.by.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class EditActivity extends AppCompatActivity {
    Company company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle args = getIntent().getExtras();
        company = (Company) args.getSerializable(Company.class.getSimpleName());
        ((TextView)findViewById(R.id.inpNameEdit)).setText(company.getName());
        ((TextView)findViewById(R.id.inpEmailEdit)).setText(company.getEmail());
        ((TextView)findViewById(R.id.inpPhoneNumberEdit)).setText(company.getPhoneNumber());
        ((TextView)findViewById(R.id.inpLocationEdit)).setText(company.getLocation());
        ((TextView)findViewById(R.id.inpLinkEdit)).setText(company.getLink());
    }

    public void ClickBack(View view) {
        Intent intent=new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void ClickEdit(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final EditText text = (EditText) findViewById(R.id.inpNameEdit);
        final String name = text.getText().toString();
        final EditText text1 = (EditText) findViewById(R.id.inpEmailEdit);
        final String email = text1.getText().toString();
        final EditText text2 = (EditText) findViewById(R.id.inpPhoneNumberEdit);
        final String phoneNumber = text2.getText().toString();
        final EditText text3 = (EditText) findViewById(R.id.inpLocationEdit);
        final String location = text3.getText().toString();
        final EditText text4 = (EditText) findViewById(R.id.inpLinkEdit);
        final String link = text4.getText().toString();

        if (name.length() > 0 && email.length() > 0 && phoneNumber.length() > 0 && location.length() > 0 && link.length() > 0) {
            company=new Company(company.getId(),name,email,phoneNumber,location,link,company.getIsChosen());
            AsyncEdit d=new AsyncEdit(getApplicationContext());
            d.execute(company);
            ClickBack(findViewById(R.id.backBtn));
        }
        else {
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }
}