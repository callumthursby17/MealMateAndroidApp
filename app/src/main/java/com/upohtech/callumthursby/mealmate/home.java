package com.upohtech.callumthursby.mealmate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;

public class home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }


    // Intent for opening the recipe page
    public void searchIngBut(View view){

        //Create the intent
        Intent searchIntent = new Intent(this, searchingIng.class);
        startActivity(searchIntent);

    }

    // Intent for opening the camera page
    public void captureImage(View view){

        //Create the intent
        Intent captureIntent = new Intent(this, captureImage.class);
        startActivity(captureIntent);

    }

    // Intent for opening the recipe page
    public void cookingTools(View view){

        //Create the intent
        Intent toolsIntent = new Intent(this, cookingTools.class);
        startActivity(toolsIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
