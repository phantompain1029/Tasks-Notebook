package com.example.testproject;

import android.app.Activity;
import android.os.Bundle;
import static butterknife.ButterKnife.bind;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends Activity {
    TextView username, Password;
    String name, password;
    Button submit;






    private static final String SEARCH_URL = "https://api.myjson.com/bins/hoh4j/";
    OkHttpClient client = new OkHttpClient();


    public String postUrl = "http://ibrtest.napconoc2.com:8087/api/iBridgeGemini/iBridgeGeminiAuthenticate";
    //public String postBody = String.format("{'UserName':'%s','Password':'%s'}", "6123192", "1234");
    RequestBody postBody = new FormBody.Builder()
            .add("UserName", "6123192")
            .add("Password", "1234")
            .build();


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            postRequest(postUrl, postBody);
        } catch (IOException e) {
            e.printStackTrace();
        }


        username = findViewById(R.id.textView2);
        Password = findViewById(R.id.textView3);

        submit = findViewById(R.id.button4);
    }



    void postRequest(String postUrl, RequestBody postBody) throws IOException {

        OkHttpClient client = new OkHttpClient();

        //RequestBody body = RequestBody.create(JSON, postBody);


        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity", "onFailure: " + e.getMessage());
                call.cancel();
            }



            @Override
            public void onResponse(Call call, Response response) throws IOException {
                /*submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String responseData = response.body().toString();
                            JSONObject json = new JSONObject(responseData);
                            String user = json.getString("userName");
                            String pass = json.getString("Password");
                            username.setText("userName: " + user);
                            Password.setText("Password: " + pass);
                        } catch (JSONException e) {
                            e.printStackTrace();


                        }
                    }
                });*/
                String result = response.body().string();
                Log.d("test", "onResponse: " + result);
                try {

                    JSONObject json = new JSONObject(result);
                    String user = json.getString("IsLogin");
                    String pass = json.getString("Account");
                    username.setText("Login: " + user);
                    Password.setText("Account: " + pass);
                } catch (JSONException e) {
                    e.printStackTrace();


                }

        }

        });
    }
}

