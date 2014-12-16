package edu.cmu.sv.privacystudy;

/**
 * Created by tushardadlani on 12/9/14.
 */
public class Features {

    String sensorType;
    double values[];
    public  Features(double values[])
    {
        sensorType = "accelerometer";
        this.values = values;
    }


}
