package asynctasks;

import android.os.AsyncTask;

import mundo.Tienda;

/**
 * Created by Choringa on 4/06/15.
 */
public class AddShopAsyncTask extends AsyncTask<Tienda,Void,Boolean> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Tienda... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
