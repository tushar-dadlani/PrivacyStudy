package edu.cmu.sv.privacystudy;

import java.math.BigDecimal;

/**
 * Created by tushardadlani on 11/17/14.
 */
public class PrivacyHelper {

    // Function to convert frequency number to something meaningful
    public static double frequency(double samplingFrequency)
    {

        // this numeber represents every how many seconds one must sample the data
        return samplingFrequency/10.0;
    }

    // Function to convert granularity number to something meaningful
    public static double granularity(double samplingGranularity)
    {

        // this number represents up to how many decimal places to capture.
        return samplingGranularity/10.0;

    }

    public static double round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }


}
