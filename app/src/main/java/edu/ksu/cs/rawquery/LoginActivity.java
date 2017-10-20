package edu.ksu.cs.rawquery;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by avalz on 20/10/17.
 */

public class LoginActivity extends AppCompatActivity {

    private static String TAG = "rawQueryApp";
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority("edu.ksu.cs.rawquery.AUTH_CP");
        uriBuilder.scheme("content");

        prefs = getSharedPreferences("edu.ksu.cs.rawquery", MODE_PRIVATE);

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }

        getContentResolver().insert(uriBuilder.build(),null);
    }

    public void buttonOnClick(View v) {
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();


        String[] projection = {MyDatabase.Table1.COLUMN_NAME_USER,MyDatabase.Table1.COLUMN_NAME_PASS};
        String selection = MyDatabase.Table1.COLUMN_NAME_USER + " = '" + username + "'" +
                " AND " + MyDatabase.Table1.COLUMN_NAME_PASS + " = '" + password + "'";
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority("edu.ksu.cs.rawquery.AUTH_CP");
        uriBuilder.scheme("content");
        Cursor cursor = getContentResolver().query(uriBuilder.build(),projection,selection,null,null);
        if (cursor != null) {
            Log.d(TAG,"cursor count = " + cursor.getCount());
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                // Login successful
                ((TextView)findViewById(R.id.login_status)).setText("Logged in as " +
                        cursor.getString(0));
                cursor.moveToPrevious();
            } else {
                ((TextView)findViewById(R.id.login_status)).setText("Wrong username or password.");
            }
            while(cursor.moveToNext()){
                String user = cursor.getString(cursor.getColumnIndex(MyDatabase.Table1.COLUMN_NAME_USER));
                String pass = cursor.getString(cursor.getColumnIndex(MyDatabase.Table1.COLUMN_NAME_PASS));
                Log.d(TAG,"username = " + user);
                Log.d(TAG, "password = " + pass);
            }
            cursor.close();
        }
        else Log.d(TAG, "cursor is null");
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
}
