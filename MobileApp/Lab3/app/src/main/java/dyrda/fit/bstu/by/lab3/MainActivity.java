package dyrda.fit.bstu.by.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    EditText noteView;
    Button delBtn;
    Button saveBtn;
    List<Note> notes = new ArrayList<>();
    Date selectedDate = new Date();
    JsonManager jsonManager = new JsonManager();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteView=findViewById(R.id.noteText);
        delBtn=findViewById(R.id.deleteBtn);
        saveBtn=findViewById((R.id.saveBtn));
        calendar=findViewById(R.id.calendar);
        String filePath = getFilesDir().getPath().toString() + "/notes.json";
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        notes = jsonManager.jsonToNotes(this);
        CheckDate();
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                selectedDate.setYear(year-1900);
                selectedDate.setMonth(month);
                selectedDate.setDate(dayOfMonth);
                CheckDate();
            }
        });
    }

    private void CheckDate()
    {
        noteView.setText("");
        if(notes.size()>0) {
            for (int i = 0; i < notes.size(); i++) {
                if(formatter.format(selectedDate).equals(formatter.format(notes.get(i).getDate())))
                {
                    noteView.setText(notes.get(i).getValue());
                    break;
                }
            }
        }
    }

    public void onClickSave(View v){
        if(noteView.getText().toString().isEmpty()){
            Toast toast=Toast.makeText(this,"Заполните поле",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();
        }
        else{
            for(int i = 0; i<notes.size(); i++) {
                if (formatter.format(selectedDate).equals(formatter.format(notes.get(i).getDate())))
                {
                    if(!notes.get(i).getValue().equals(noteView.getText().toString()))
                    {
                        notes.remove(i);
                        break;
                    }
                }
            }
            Note note = new Note(selectedDate, noteView.getText().toString());
            notes.add(note);
            jsonManager.expToJSON(this, notes);
            Toast.makeText(this,"Заметка добавлена",Toast.LENGTH_LONG).show();
            selectedDate=new Date();
        }
    }

    public void onClickDelete(View view)
    {
        for(int i = 0; i<notes.size(); i++)
        {
            if(formatter.format(selectedDate).equals(formatter.format(notes.get(i).getDate())))
            {
                notes.remove(i);
                jsonManager.expToJSON(this, notes);
                Toast.makeText(this, "Заметка удалена", Toast.LENGTH_LONG).show();
                noteView.setText("");
            }
        }
    }
}