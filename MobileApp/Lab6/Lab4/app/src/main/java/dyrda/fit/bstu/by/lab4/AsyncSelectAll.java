package dyrda.fit.bstu.by.lab4;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class AsyncSelectAll extends AsyncTask<Context,Void, List<Company>> {
    @Override
    protected List<Company> doInBackground(Context... contexts) {
        DatabaseHelper db=new DatabaseHelper(contexts[0]);
        List<Company> list=db.getData("select * from "+db.TABLE);
        return list;
    }
}
