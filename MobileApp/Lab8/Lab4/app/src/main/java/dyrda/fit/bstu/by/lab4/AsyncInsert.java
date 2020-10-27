package dyrda.fit.bstu.by.lab4;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncInsert extends AsyncTask<Company,Void,Void> {
    DatabaseHelper db;
    Context context;

    public AsyncInsert(Context context){
        this.context=context;
    }

    @Override
    protected Void doInBackground(Company... companies) {
        db=new DatabaseHelper(context);
        db.insert(companies[0]);
        return null;
    }
}
