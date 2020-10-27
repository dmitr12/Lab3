package dyrda.fit.bstu.by.testdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper=new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database=dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public List<User> getAllUsers(){
        ArrayList<User> users=new ArrayList<>();
        Cursor userCursor=database.rawQuery("SELECT * FROM users",null);
        if(userCursor.moveToFirst()){
            do{
                User user=new User(
                        userCursor.getInt(userCursor.getColumnIndex(dbHelper.column_id)),
                        userCursor.getString(userCursor.getColumnIndex(dbHelper.column_name)),
                        userCursor.getString(userCursor.getColumnIndex(dbHelper.column_secondName)),
                        userCursor.getString(userCursor.getColumnIndex(dbHelper.column_mail)),
                        userCursor.getInt(userCursor.getColumnIndex(dbHelper.column_isChosen))
                );
                users.add(user);
            }while(userCursor.moveToNext());
        }
        userCursor.close();
        return users;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, dbHelper.TABLE);
    }

    public long insert(User user){
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbHelper.column_name, user.name);
        contentValues.put(dbHelper.column_secondName, user.secondName);
        contentValues.put(dbHelper.column_mail, user.mail);
        contentValues.put(dbHelper.column_isChosen, user.isChosen);
        return database.insert(dbHelper.TABLE,null,contentValues);
    }
}
