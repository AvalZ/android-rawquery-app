package edu.ksu.cs.rawquery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "rawQueryApp";
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButtonClick(View v) {
        Log.i("RawQuery", "Called Login Button click");
        startActivity(new Intent("edu.ks.cs.rawquery.LOGIN_ACTIVITY"));
    }

    public void uselessButtonClick(View v) {
        Log.i("RawQuery", "Called Useless Button click");

        // startActivity(new Intent(null, UselessActivity.class));
        startActivity(new Intent("edu.ks.cs.rawquery.USELESS_ACTIVITY"));
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}
