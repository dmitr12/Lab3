package dyrda.fit.bstu.by.testdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="lab7.db";
    private static final int SCHEMA=1;
    static final String TABLE="users";

    public static final String column_id="Id";
    public static final String column_name="Name";
    public static final String column_secondName="SecondName";
    public static final String column_mail="Mail";
    public static final String column_isChosen="IsChosen";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+column_name+" TEXT,"+
                column_secondName+" TEXT,"+column_mail+" TEXT,"+column_isChosen+" INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(sqLiteDatabase);
    }
}
