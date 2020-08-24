package com.example.shtrih;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.opengl.Visibility;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

final public class Notifications {

    public static void MakeNotification(Context context,String message)
    {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("ПриборКасса")

                        .setContentText(message);

        Notification notification = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }



}
