package com.example.rexita_pc.admin.Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.rexita_pc.admin.Activity.DetailPencurianActivity;
import com.example.rexita_pc.admin.Activity.MainActivity;
import com.example.rexita_pc.admin.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        showNotification(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }
    }

    private void showNotification(RemoteMessage remoteMessage) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        sendBroadcast(notificationIntent);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);

        //You should use an actual ID instead
        int notificationId = new Random().nextInt(60000);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels(remoteMessage);
        }
        else {

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, "1")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(remoteMessage.getNotification().getTitle())
                            .setContentText(remoteMessage.getNotification().getBody())
                            .setSound(defaultSoundUri)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);

            notificationManager.notify(notificationId, notificationBuilder.build());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(RemoteMessage remoteMessage){

        Intent notificationIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setSound(defaultSoundUri)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        if (notificationManager != null) {
            //You should use an actual ID instead
            int notificationId = new Random().nextInt(60000);
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
    }
}
