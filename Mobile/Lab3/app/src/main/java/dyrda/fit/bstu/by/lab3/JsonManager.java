package dyrda.fit.bstu.by.lab3;
import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class JsonManager {
    private static final String fileName = "notes.json";
    public boolean expToJSON (Context context, List<Note> notes) {
        Gson gson = new Gson();
        Items items = new Items();
        items.setNotes(notes);
        String jsonString = gson.toJson(items);
        FileOutputStream fileWrite = null;
        try {
            fileWrite = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileWrite.write(jsonString.getBytes());
            return true;
        }
        catch (Exception ex){
            Toast.makeText(context, "Ошибка: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                fileWrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public List<Note> jsonToNotes (Context context) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(fileName);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            Items notes = gson.fromJson(streamReader, Items.class);
            return  notes.getNotes();
        }
        catch (Exception ex){
            Toast.makeText(context, "Ошибка: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            try {
                streamReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
    private class Items {
        private List<Note> notes;

        public List<Note> getNotes() {
            return notes;
        }

        public void setNotes(List<Note> notes) {
            this.notes = notes;
        }
    }
}

