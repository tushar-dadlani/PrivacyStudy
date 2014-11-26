package edu.cmu.sv.privacystudy;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class SensorDataService extends Service implements SensorEventListener {

    final String LOG_TEXT = "SensorDataService";


    private Sensor accelerometer, gyroscope;

    private double samplingFrequency, samplingGranularity;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {


        final int type = sensorEvent.sensor.getType();
        float values[] = sensorEvent.values;
        double vals[] = new double[values.length];

        int rounding = (int)samplingGranularity;

        for (int i=0; i < values.length; i++)
        {
             vals[i] = PrivacyHelper.round(values[i], rounding);

        }

        switch (type) {
            case Sensor.TYPE_ACCELEROMETER: {
                Log.d(LOG_TEXT, "Accelerometer values " + Double.toString(vals[0]) + " " + Double.toString(vals[1]) + " " + Double.toString(vals[2]));
                Log.d(LOG_TEXT, "Accelerator Power " + Double.toString(accelerometer.getPower()));
            }

            case Sensor.TYPE_GYROSCOPE: {
                Log.d(LOG_TEXT, "Gyropscope values " + Double.toString(vals[0]) + " " + Double.toString(vals[1]) + " " + Double.toString(vals[2]));
                Log.d(LOG_TEXT, "Gyroscope Power " + Double.toString(gyroscope.getPower()));

            }
        }

        try {
            Thread.sleep((int)samplingFrequency * 100);
        }
        catch(InterruptedException e)
        {
            Log.d(LOG_TEXT, "Interrupted Exception in Thread.sleep()");
        }
        catch(NullPointerException e)
        {
            Log.d(LOG_TEXT, "Accelerometer/Gyroscope object is null");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public SensorDataService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        samplingFrequency = Double.parseDouble(intent.getStringExtra("frequency"));
        samplingGranularity = Double.parseDouble(intent.getStringExtra("granularity"));

        samplingFrequency = PrivacyHelper.frequency(samplingFrequency);
        samplingGranularity = PrivacyHelper.granularity(samplingGranularity);

        Log.d(LOG_TEXT, Double.toString(samplingFrequency));
        Log.d(LOG_TEXT, Double.toString(samplingGranularity));

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);



        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
