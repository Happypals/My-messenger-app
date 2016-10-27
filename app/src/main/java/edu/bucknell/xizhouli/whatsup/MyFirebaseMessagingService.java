package edu.bucknell.xizhouli.whatsup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by xizhouli on 10/26/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    Intent intent;
    private static final String TAG = "MyFirebaseMsgService" ;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Display data in log
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification message Body: " + remoteMessage.getNotification().getBody());


        //Calling method to generate notification
        sendNotification(remoteMessage.getNotification().getBody());

        intent.putExtra("Xizhou Li",remoteMessage.getNotification().getBody());
    }

    // generate push notifications
    private void sendNotification(String messageBody) {
        intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("You have a new message !")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }



}
