package dyrda.fit.bstu.by.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    FragmentInfo fi;
    Company company;
    FragmentTransaction transaction;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        backBtn=findViewById(R.id.backB);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras=getIntent().getExtras();
        company=(Company)extras.get(Company.class.getSimpleName());
        fi=new FragmentInfo();
        fi.Company=company;
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.infoLayout, fi);
        transaction.commit();
    }
}