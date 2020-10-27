package dyrda.fit.bstu.by.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


interface Callback {
    void myResponseCallback(long result);
}

public class AddActivity extends AppCompatActivity {
    FirebaseUser user;
    long id;

    EditText text;
    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        user = FirebaseAuth.getInstance().getCurrentUser();
        text=findViewById(R.id.inpName);
        text1=findViewById(R.id.inpEmail);
        text2=findViewById(R.id.inpPhoneNumber);
        text3 = findViewById(R.id.inpLocation);
        text4 = findViewById(R.id.inpLink);
    }

    @Override
    protected void onStart() {
        super.onStart();
        text.setText("");
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        getId(new Callback() {
            @Override
            public void myResponseCallback(long result) {
                id = result;
            }
        });
    }

    public void ClickBack(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void ClickAdd(View view) {
        final String name = text.getText().toString();
        final String email = text1.getText().toString();
        final String phoneNumber = text2.getText().toString();
        final String location = text3.getText().toString();
        final String link = text4.getText().toString();
        if (name.length() > 0 && email.length() > 0 && phoneNumber.length() > 0 && location.length() > 0 && link.length() > 0) {
            Company company = new Company(id, name, email, phoneNumber, location, link);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user.getUid());
            ref.push().setValue(company).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(AddActivity.this, "Успешно добавлено", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, "При добавлении произошла ошибка", Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }

    }
    public void getId(final Callback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child(user.getUid()).orderByChild("id");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                Company company=null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    count++;
                    company = snapshot.getValue(Company.class);
                }
                if(company==null)
                    callback.myResponseCallback(0);
                else
                    callback.myResponseCallback(company.getId()+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}