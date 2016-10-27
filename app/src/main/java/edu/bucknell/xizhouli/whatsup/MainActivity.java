package edu.bucknell.xizhouli.whatsup;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.key;
import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {
    public static String[] email_arr;
    private static SharedPreferences prefs;
    public static final int REQUEST_ACCOUNTS = 0;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        List<String> emailList = getEmailList();
        email_arr = emailList.toArray(new String[emailList.size()]);

    }
    private List<String> getEmailList() {
        List<String> list = new ArrayList<String>();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            request_getAccounts_permission();
            Account[] accounts = AccountManager.get(this).getAccounts();
            for (Account account : accounts) {
                if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                    list.add(account.name);
                }
            }
        }else {
            Account[] accounts = AccountManager.get(this).getAccounts();
        }
        return list;

    }
    private void request_getAccounts_permission(){
        Log.i(TAG, "GET_ACCOUNTS permission has NOT been granted. Requesting permission.");

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS},
                REQUEST_ACCOUNTS);
    }

}
