package com.example.omarf.firstprojectdemo;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by omarf on 1/16/2017.
 */

public class SyncService extends IntentService {

    private static final String TAG = "syncServiceTag";

    public static Intent startIntent(Context context){
         return new Intent(context,SyncService.class);
    }

    public SyncService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"receive intent");

        if (isNetworkAvailableAndConnected()){

            FirebaseToSql firebaseToSql =new FirebaseToSql(this);
            firebaseToSql.syncSqlite();
        }
    }

    public static void setSyncAlarm(Context context, boolean isOn){
        Intent intent=startIntent(context);
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent=PendingIntent.getService(context,0,intent,0);
        if (isOn)
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),1000*60,pendingIntent);
        else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
        return isNetworkConnected;
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent intent = SyncService.startIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

}
