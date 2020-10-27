package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncEdit extends AsyncTask<Company,Void,Void> {
    DatabaseHelper db;
    Context context;

    public AsyncEdit(Context context){
        this.context=context;
    }

    @Override
    protected Void doInBackground(Company... companies) {
        db=new DatabaseHelper(context);
        db.update(companies[0]);
        return null;
    }
}
