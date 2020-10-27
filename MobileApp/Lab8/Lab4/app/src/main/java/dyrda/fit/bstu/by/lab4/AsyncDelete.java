package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncDelete extends AsyncTask<Integer,Void,Void> {
    DatabaseHelper db;
    Context context;

    public AsyncDelete(Context context){
        this.context=context;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        db=new DatabaseHelper(context);
        db.delete(integers[0]);
        return null;
    }
}
