package dyrda.fit.bstu.by.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<Integer,Float> k= new HashMap<Integer, Float>();
    
    @Override
    protected void onSaveInstanceState(Bundle outState){
        TextView resString=findViewById(R.id.result);
        outState.putString("stringRes",resString.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        TextView txtV=findViewById(R.id.result);
        txtV.setText(savedInstanceState.getString("stringRes"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner activities=(Spinner)findViewById(R.id.levelActivity);
        Button btn=(Button)findViewById(R.id.btn);
        final RadioButton man=(RadioButton)findViewById(R.id.man);
        final EditText growth=(EditText)findViewById(R.id.growth);
        final EditText weigth=(EditText)findViewById(R.id.weight);
        final EditText age=(EditText)findViewById(R.id.age);
        final TextView result=(TextView)findViewById(R.id.result);
        k.put(0,1.2f);
        k.put(1,1.375f);
        k.put(2,1.55f);
        k.put(3,1.725f);
        k.put(4,1.9f);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(growth.getText().toString().isEmpty() || weigth.getText().toString().isEmpty() || age.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Заполните все поля", Toast.LENGTH_SHORT).show();
                else{
                    int indexSelectedItem=activities.getSelectedItemPosition();
                    float bmr, amr;
                    amr=k.get(indexSelectedItem);
                    if(!man.isChecked()){
                        bmr=655.955f+(9.5634f * Float.parseFloat(weigth.getText().toString())) + (1.8496f * Float.parseFloat(growth.getText().toString()))
                                - (4.6756f * Float.parseFloat(age.getText().toString()));
                    }
                    else{
                        bmr=66.4730f+(13.7516f * Float.parseFloat(weigth.getText().toString())) + (5.0033f * Float.parseFloat(growth.getText().toString()))
                                - (6.7550f * Float.parseFloat(age.getText().toString()));
                    }
                    float res=bmr*amr;
                    result.setText("Суточная норма калорий: "+(int)res);
                }
            }
        });
    }


}