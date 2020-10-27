package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class AsyncGetFav extends AsyncTask<Context,Void, List<Company>> {
    @Override
    protected List<Company> doInBackground(Context... contexts) {
        DatabaseHelper db=new DatabaseHelper(contexts[0]);
        return db.getData("select * from "+db.TABLE+" where "+db.column_isChosen+"=1");
    }
}
