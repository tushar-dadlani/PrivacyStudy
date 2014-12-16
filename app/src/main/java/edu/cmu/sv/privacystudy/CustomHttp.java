package edu.cmu.sv.privacystudy;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by tushardadlani on 12/10/14.
 */
public class CustomHttp implements Runnable{

    private final OkHttpClient client = new OkHttpClient();
    FormEncodingBuilder fe = new FormEncodingBuilder();
    final String url = "http://198.199.98.12:5000";
    private String jsonString;


    public CustomHttp(String json)
    {
        this.jsonString = json;

    }



    public void run()   {

        fe.add("sensor", this.jsonString);
        Request request = new Request.Builder()
                .url(url)
                .post(fe.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String response_string = response.body().string();
            System.out.println("response string \t" + response_string);



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
