package appforblind.best.apptech.androidpushnotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String server_url_insert_token = "http://10.42.0.1/push/insert_token.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnClick(View view) {

//        Toast.makeText(getApplicationContext(),FirebaseInstanceId.getInstance().getToken(),Toast.LENGTH_LONG).show();
//        MyFirebaseInstanceIdService service = new MyFirebaseInstanceIdService();
//        service.onTokenRefresh();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PRF), Context.MODE_PRIVATE);
        final String token = FirebaseInstanceId.getInstance().getToken();

        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url_insert_token, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                // this "token" name should same as php post method name
                params.put("token",FirebaseInstanceId.getInstance().getToken());

                return params;
            }
        };

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }
}
