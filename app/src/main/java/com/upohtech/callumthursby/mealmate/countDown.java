package com.upohtech.callumthursby.mealmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;


public class countDown extends Activity {

    public int sec ;
    public String countName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        setTitle("Countdown");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count_down, menu);
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

    //Time Intent
    public void countDownStart (View view){

        // Collects the name of the countdown from the user input field
        EditText countNameinput = (EditText)findViewById(R.id.timerNameInput);

        // Checks to see if a user has entered data, if not a default title will be used
        if (countNameinput.length() < 1){
            countName = "Timer Started";
        }else {
            countName = String.valueOf(countNameinput.getText().toString());
        }

        // Collects data from the minutes field and puts it in the variable min
        EditText min = (EditText)findViewById(R.id.mininput);

        // Checks to make sure the user has put an input in the minutes field, in not and timer will default to 1 min
        if (min.length() < 1){
            sec = 1;
        }
        else {
            // Converts the user input to a readable int
            sec = Integer.valueOf(min.getText().toString());
        }
            // Maths calculation to convert the users input into seconds for the time to run correctly
            sec = sec * 60;

      Intent countDown = new Intent(AlarmClock.ACTION_SET_TIMER)
              .putExtra(AlarmClock.EXTRA_MESSAGE, countName)
              .putExtra(AlarmClock.EXTRA_LENGTH, sec)
              .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
      if (countDown.resolveActivity(getPackageManager()) != null){
          startActivity(countDown);
      }
  }
}

