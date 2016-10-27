package appforblind.best.apptech.androidpushnotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by toukir on 10/23/16.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";
    String recent_token;
    @Override
    public void onTokenRefresh() {

        recent_token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PRF), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(getString(R.string.FCM_TOKEN),recent_token);
        edit.commit();
        Log.d(REG_TOKEN,recent_token);

    }

    public String getRegId(){
        return recent_token;
    }
}
