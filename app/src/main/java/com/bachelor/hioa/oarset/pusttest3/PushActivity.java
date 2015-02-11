package com.bachelor.hioa.oarset.pusttest3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.*;

import java.util.HashMap;
import java.util.Map;


public class PushActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "tCisItwKQDkyC8AFJYhTS63TmppUpNBjpNn1ojJV", "r8tGWIHNmkyPAlh3AxL7sLjnABmMffvSFypFBJvV");

        // Parse push notification test
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    final TextView msgText = (TextView) findViewById(R.id.msgoutput);
                    msgText.setText("successfully subscribed to the broadcast channel.");
                } else {
                    final TextView msgText = (TextView) findViewById(R.id.msgoutput);
                    msgText.setText("failed to subscribe for push");
                }
            }
        });
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);

        // Parse cloud code method test
        ParseCloud.callFunctionInBackground("hello", new HashMap<String, Object>(), new FunctionCallback<String>() {
            public void done(String result, ParseException e) {
                if (e == null) {
                    // result is "Hello world!"
                    final TextView msgText = (TextView) findViewById(R.id.msg2output);
                    msgText.setText(result);
                }
            }
        });

        // Parse Data storage test
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_push, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
