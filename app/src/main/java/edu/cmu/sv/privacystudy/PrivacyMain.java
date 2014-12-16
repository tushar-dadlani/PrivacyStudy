package edu.cmu.sv.privacystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class PrivacyMain extends Activity {

    TextView granularity, frequency;
    SeekBar granularitySeek, frequencySeek;
    Button startServiceButton, stopServiceButton;
    int granularityVal, frequencyVal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_main);


        granularity = (TextView)findViewById(R.id.granularityText);
        frequency = (TextView)findViewById(R.id.freqText);
        startServiceButton = (Button)findViewById(R.id.startService);
        stopServiceButton = (Button)findViewById(R.id.stopService);


        final CharSequence freqText = frequency.getText();
        final CharSequence granText = granularity.getText();

        granularitySeek = (SeekBar) findViewById(R.id.granularity);
        frequencySeek = (SeekBar) findViewById(R.id.samplingFreq);

        frequencyVal = frequencySeek.getProgress();
        granularityVal = granularitySeek.getProgress();

        granularity.setText(granText + " " + Integer.toString(granularityVal));
        frequency.setText(freqText + " " + Integer.toString(frequencyVal));




        granularitySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                granularityVal = i;
                granularity.setText(granText + " " + Integer.toString(granularityVal));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        frequencySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                frequencyVal = i;
                frequency.setText(freqText + " " + Integer.toString(frequencyVal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start activity with the frequency/granularity values.

                Intent serviceIntent = new Intent(getApplicationContext(), SensorDataService.class);
                serviceIntent.putExtra("frequency", Integer.toString(frequencyVal));
                serviceIntent.putExtra("granularity", Integer.toString(granularityVal));
                getApplicationContext().startService(serviceIntent);


        }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.measure_power, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
