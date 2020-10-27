package dyrda.fit.bstu.by.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME="testDB.db";
    static final String TABLE="companies";
    private static String DB_PATH;
    private Context context;
    public Cursor cursor;
    SimpleCursorAdapter adapter;

    public static final String column_id="Id";
    public static final String column_name="Name";
    public static final String column_mail="Mail";
    public static final String column_phoneNumber="PhoneNumber";
    public static final String column_location="Location";
    public static final String column_link="Link";
    public static final String column_isChosen="IsChosen";

    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context=context;
        DB_PATH=context.getFilesDir().getPath()+DB_NAME;
    }

    public List<Company> getData(String sqlScriptSelect){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Company> companies=new ArrayList<>();
        cursor=db.rawQuery(sqlScriptSelect,null);
        if(cursor.moveToFirst()){
            do{
                Company company=new Company(
                        cursor.getInt(cursor.getColumnIndex(column_id)),
                        cursor.getString(cursor.getColumnIndex(column_name)),
                        cursor.getString(cursor.getColumnIndex(column_mail)),
                        cursor.getString(cursor.getColumnIndex(column_phoneNumber)),
                        cursor.getString(cursor.getColumnIndex(column_location)),
                        cursor.getString(cursor.getColumnIndex(column_link)),
                        cursor.getInt(cursor.getColumnIndex(column_isChosen))
                );
                companies.add(company);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return companies;
    }

    public void insert(Company company){
        SQLiteDatabase db=getReadableDatabase();
        db.execSQL("insert into companies (Name, Mail, PhoneNumber, Location, Link, IsChosen) values ('"+ company.getName() + "', '" + company.getEmail() + "', '" + company.getPhoneNumber() + "', '" + company.getLocation() +"', '" + company.getLink()+"', " + company.getIsChosen()+");");
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db=getReadableDatabase();
        db.execSQL("delete from "+TABLE+" where "+column_id+"="+id);
        db.close();
    }

    public void update(Company company){
        SQLiteDatabase db=getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(column_id,company.getId());
        cv.put(column_name,company.getName());
        cv.put(column_mail,company.getEmail());
        cv.put(column_phoneNumber, company.getPhoneNumber());
        cv.put(column_location, company.getLocation());
        cv.put(column_link, company.getLink());
        cv.put(column_isChosen, company.getIsChosen());
        db.update(TABLE,cv,column_id+"=?", new String[]{String.valueOf(company.getId())});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE+"("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+column_name+" TEXT,"+
                column_mail+" TEXT,"+column_phoneNumber+" TEXT,"+column_location+" TEXT,"+column_link+" TEXT,"+column_isChosen+" INTEGER);");
        sqLiteDatabase.execSQL("insert into "+TABLE+"(Name, Mail, PhoneNumber, Location, Link, IsChosen) values ('"+ "company1" + "', '" + "somemail" + "', '" + "375333710095" + "', '" + "Minsj" +"', '" +"asd"+"'," +0+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    private static final String DB_NAME="lab7.db";
//    private static final int SCHEMA=1;
//    static final String TABLE="companies";
//
//    public static final String column_id="Id";
//    public static final String column_name="Name";
//    public static final String column_mail="Mail";
//    public static final String column_phoneNumber="PhoneNumber";
//    public static final String column_location="Location";
//    public static final String column_link="Link";
//    public static final String column_isChosen="IsChosen";
//
//
//    public DatabaseHelper(Context context){
//        super(context, DB_NAME, null, SCHEMA);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE users ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+column_name+" TEXT,"+
//                column_mail+" TEXT,"+column_phoneNumber+" TEXT,"+column_location+" TEXT,"+column_link+" TEXT,"+column_isChosen+" INTEGER);");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE);
//        onCreate(sqLiteDatabase);
//    }
//
//}
